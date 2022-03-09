package com.greedy.waterfall.issue.controller;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.dto.GuideFileDTO;
import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueNotificationDTO;
import com.greedy.waterfall.issue.model.dto.IssueRegisterDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.issue.model.service.IssueService;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;


@RestController
@RequestMapping("/issue")
@SessionAttributes({"adminProjectNo", "taskNo"})		//이것때문에 세션에 못넣어준 현상 빈번했었음
public class IssueController {
	
	private final IssueService issueService;
	
	@Autowired
	public IssueController(IssueService IssueService) {
		this.issueService = IssueService;
	}
	
	@GetMapping("/project")
	public ModelAndView projectList(HttpServletRequest request, ModelAndView mv) {
		
		int memberNo = (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		Map<String, Integer> managerNo = new HashMap<>();
		managerNo.put("managerNo", memberNo);

		List<ProjectIssueCountDTO> allProject = issueService.selectAllProjectList(managerNo);
		System.out.println("allProject" + allProject);
		
		mv.addObject("allProject", allProject);
		mv.addObject("intent", "/issue/project");
		mv.setViewName("/issue/projectList");
		
		return mv;
	}
	
	@GetMapping("/admin/task")
	public ModelAndView adminTaskList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		
//		Map<String, Integer> projectNoMap = new HashMap<>();
//		projectNoMap.put("projectNo", projectNo);
		
		System.out.println("projectNo :" + projectNo);
		
		List<IssueDTO> taskIssueList = issueService.selectIssuesOfTask(projectNo);
		
		mv.addObject("adminProjectNo", projectNo);
		mv.addObject("taskIssueList", taskIssueList);
		mv.addObject("intent", "/issue/task");
		mv.setViewName("/issue/adminTaskList");
		
		return mv;
	}
	
	@GetMapping("/admin/list/{taskNo}")
	public ModelAndView adminIssueList(@PathVariable("taskNo") int taskNo, HttpServletRequest request, ModelAndView mv) {
		
		Integer projectNo = (int) request.getSession().getAttribute("adminProjectNo");
		System.out.println("list에 들어오는 프로젝트 no : " + projectNo);
		
		System.out.println(taskNo);
		
		List<IssueDTO> issueList = issueService.selectIssueList(taskNo);
		
		mv.addObject("projectNo", projectNo);
		mv.addObject("taskNo", taskNo);
		mv.addObject("issueList", issueList);
		mv.addObject("intent", "/issue/list");
		mv.setViewName("/issue/adminIssueList");
		
		return mv;
	}
	
	@GetMapping("/admin/delete")
	public ModelAndView adminRemoveGuide(ModelAndView mv, HttpServletRequest request, 
			RedirectAttributes rttr) throws GuideRemoveException {
		
		int issueNo = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("삭제하기 위해 no 받기 " + issueNo );
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		/* 반환해줄 경로를 찾기 위해서 뽑아낸 업무 번호 */
		int taskNo = issueService.removeIssue(issueNo, loginMemberNo);
		
		rttr.addFlashAttribute("message", "이슈 삭제에 성공하셨습니다.");
		
		mv.addObject("intent", "/issue/admin/delete");
		mv.setViewName("redirect:/issue/admin/list/" + taskNo);
		
		return mv;
		
	}
	
	@PostMapping("/admin/regist/{taskNo}")
	public ModelAndView adminRegistIssue(@ModelAttribute IssueDTO issue, HttpServletRequest request, ModelAndView mv,
			@PathVariable("taskNo") int taskNo, RedirectAttributes rttr,  @RequestParam List<MultipartFile> multiFiles) {
		System.out.println("업무 넘버는 : " + taskNo);
		issue.setTaskNo(taskNo);
		
		System.out.println("프로젝트 넘버는 :" + issue.getProjectNo());
		
		int writerMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		System.out.println("작성자 넘버는 : " +  writerMemberNo);
		issue.setRegisterNo(writerMemberNo);
		
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());

		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("이슈 등록 확인 " + issue);
					
				}
				
				rttr.addFlashAttribute("message", "파일 업로드 성공");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
				}
				rttr.addFlashAttribute("message", "파일 업로드 실패");
			  }
		
		}
		
		String message = "";
		
		if(issueService.registIssue(issue)) {
			message = "게시글을 등록했습니다.";
			System.out.println(message);
		} else {
			message = "게시글등록에 실패했습니다.";
			System.out.println(message);
		}

		mv.setViewName("redirect:/issue/admin/list/"+taskNo);
		
		return mv;
	}
	
	@GetMapping(value="admin/list/adminIssueDetail", produces="application/json; charset=UTF-8")
	@ResponseBody
	public ModelAndView adminfindIssueDetail(ModelAndView mv, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));		//이슈번호
		System.out.println("detail에 들어오는 이슈 no : " + no);
		
		int projectNo = (int) request.getSession().getAttribute("adminProjectNo");
		System.out.println("detail에 들어오는 프로젝트 no : " + projectNo);
		
		IssueDTO issueDetail = issueService.selectIssueDetail(no);
		
		List<ProjectMemberDTO> projectMember = issueService.selectProjectMember(projectNo);
		
		System.out.println("상세조회 issueDetail : " + issueDetail);
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		mv.addObject("projectMember", projectMember);
		mv.addObject("issueDetail", gson.toJson(issueDetail));
		mv.addObject("projectMember", gson.toJson(projectMember));
		mv.setViewName("jsonView");
		return mv;
	}
	
	@PostMapping("/admin/update")
	public ModelAndView adminModifyIssue(@ModelAttribute @Nullable IssueDTO issue, HttpServletRequest request,
			RedirectAttributes rttr, ModelAndView mv, @RequestParam List<MultipartFile> multiFiles) throws GuideModifyException {
		
		/* 파일 업로드 */
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());
		
		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("이슈 등록 확인 " + issue);
					
				}
				
				rttr.addFlashAttribute("subMessage", "파일 업로드 성공");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
				}
				rttr.addFlashAttribute("subMessage", "파일 업로드 실패");
			  }
		}
		
		int taskNo = (int) request.getSession().getAttribute("taskNo");
		System.out.println("update에 들어오는 업무번호 : " + taskNo);
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMember =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		issueService.modifyIssue(issue, loginMember);
		
		System.out.println("modifyIssue : " + issue);
		
		rttr.addFlashAttribute("message", "이슈 수정에 성공하셨습니다");
		
		mv.addObject("intent", "/issue/admin/update");
		mv.setViewName("redirect:/issue/admin/list/" + taskNo);
		
		return mv;
	}
	
	@GetMapping("/list/{taskNo}")
	public ModelAndView issueList(@PathVariable("taskNo") int taskNo, HttpServletRequest request, ModelAndView mv) {
		
//		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		
		System.out.println(taskNo);
		
		List<IssueDTO> issueList = issueService.selectIssueList(taskNo);
		
		mv.addObject("taskNo", taskNo);
		mv.addObject("issueList", issueList);
		mv.addObject("intent", "/issue/list");
		mv.setViewName("/issue/issueList");
		
		return mv;
	}
	
	@GetMapping("/task")
	public ModelAndView taskList(HttpServletRequest request, ModelAndView mv) {
		
//		int projectNo = Integer.parseInt(request.getParameter("projectNo"));

		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
//		Map<String, Integer> projectNoMap = new HashMap<>();
//		projectNoMap.put("projectNo", projectNo);
		
		System.out.println("projectNo :" + projectNo);

		List<IssueDTO> taskIssueList = issueService.selectIssuesOfTask(projectNo);

		
		mv.addObject("taskIssueList", taskIssueList);
		mv.addObject("intent", "/issue/task");
		mv.setViewName("/issue/taskList");
		
		return mv;
	}
	
	
	@GetMapping("/all")
	public ModelAndView allIssueList(HttpServletRequest request, ModelAndView mv) {
		
		List<IssueDTO> allIssueList = issueService.selectAllIssue();
		
		mv.addObject("allIssueList", allIssueList);
		mv.addObject("intent", "/issue/all");
		mv.setViewName("/issue/allIssueList");
		
		return mv;
	}
	

//	@RequestMapping(value = "regist/{taskNo}", method = RequestMethod.POST)
	@PostMapping("regist/{taskNo}")
	public ModelAndView registIssue(@ModelAttribute IssueDTO issue, HttpServletRequest request, ModelAndView mv,
			@PathVariable("taskNo") int taskNo, RedirectAttributes rttr,  @RequestParam List<MultipartFile> multiFiles) {
//		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		System.out.println("업무 넘버는 : " + taskNo);
		issue.setTaskNo(taskNo);
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		issue.setProjectNo(projectNo);
		
		int writerMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		System.out.println("작성자 넘버는 : " +  writerMemberNo);
		issue.setRegisterNo(writerMemberNo);
		
//		rttr.addFlashAttribute("taskNo", taskNo);		//없어도 문제없음
		/* redirect:/issue/list로 보낼 때 taskNo를 못 가져오는 문제가 발생해서 이렇게 작성함  */
		
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());

		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
//				issue.setFile(issueFileDTO);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("이슈 등록 확인 " + issue);
					
				}
				
				rttr.addFlashAttribute("message", "파일 업로드 성공");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
				}
				rttr.addFlashAttribute("message", "파일 업로드 실패");
			  }
		
		}
		
		String message = "";
		if(issueService.registIssue(issue)) {
			message = "게시글을 등록했습니다.";
			System.out.println(message);
		} else {
			message = "게시글등록에 실패했습니다.";
			System.out.println(message);
		}
		
		mv.setViewName("redirect:/issue/list/"+taskNo);
		return mv;
	}
	
	@GetMapping(value="list/issueDetail", produces="application/json; charset=UTF-8")
	@ResponseBody
	public ModelAndView findIssueDetail(ModelAndView mv, HttpServletRequest request) {
		
		int no = Integer.parseInt(request.getParameter("no"));		//이슈번호
		System.out.println("detail에 들어오는 이슈 no : " + no);
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		System.out.println("detail에 들어오는 프로젝트 no : " + projectNo);
		
		IssueDTO issueDetail = issueService.selectIssueDetail(no);
		System.out.println("issueDetail : " + issueDetail);
		
		List<ProjectMemberDTO> projectMember = issueService.selectProjectMember(projectNo);
		
		System.out.println("상세조회 issueDetail : " + issueDetail);
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		mv.addObject("projectMember", projectMember);
		mv.addObject("issueDetail", gson.toJson(issueDetail));
		mv.addObject("projectMember", gson.toJson(projectMember));
		mv.setViewName("jsonView");
		return mv;
	}
	
	@GetMapping("/download/{fileNo}")
	public ModelAndView downloadFile(@PathVariable("fileNo") String fileNo) throws IOException {
		int no = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		
		IssueFileDTO file = issueService.findFile(no);
		fileInfo.put("filePath", file.getSavedPath());
		System.out.println(file.getSavedPath());
		System.out.println(file.getRandomName());
		fileInfo.put("fileOriginName", file.getOriginalName());
		fileInfo.put("fileRandomName", file.getRandomName());
		return new ModelAndView("fileDownloadView", "downloadFile", fileInfo);
	}
	
	@GetMapping("/deleteFile/{fileNo}")
	public ModelAndView deleteFile(@PathVariable("fileNo") String fileNo, HttpSession session, 
			RedirectAttributes rttr, ModelAndView mv) throws NumberFormatException, UnsupportedEncodingException {
		
		int taskNo = (int) session.getAttribute("taskNo");
		System.out.println("delete에 들어오는 업무 no : " + taskNo);
		
		int fileNumber = Integer.parseInt(URLDecoder.decode(fileNo, "UTF-8"));
		
		IssueFileDTO issueFileDTO = issueService.removeGuideFile(fileNumber); 
		
		String root = session.getServletContext().getRealPath("resources");	
		
		String filePath = root + "/guideUploadFiles";
		
		File file = new File(filePath + "\\" + issueFileDTO.getRandomName());
		
		if(file.exists()) {
			file.delete();
		}
		
		mv.addObject("intent", "/issue/deleteFile");
		mv.setViewName("redirect:/issue/list/" + taskNo);
		
		rttr.addFlashAttribute("message", "가이드 게시판 첨부파일 삭제에 성공하셨습니다.");
		
		return mv; 
	}
	
	@PostMapping("/update")
	public ModelAndView modifyIssue(@ModelAttribute @Nullable IssueDTO issue, HttpServletRequest request,
			RedirectAttributes rttr, ModelAndView mv, @RequestParam List<MultipartFile> multiFiles) throws GuideModifyException {
		
		/* 파일 업로드 */
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		/* 폴더 생성 완료 */
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		/* 사진 개수 출력 완료 */
		System.out.println("multiFiles.size() : " + multiFiles.size());
		
		List<IssueFileDTO> fileList = new ArrayList<IssueFileDTO>();
		if(multiFiles.get(0).getSize() != 0) {
			for(int i = 0; i < multiFiles.size(); i++) {
				String originFileName = multiFiles.get(i).getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				System.out.println("originFileName" + originFileName);
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("savedName : " + savedName);
				
				IssueFileDTO issueFileDTO = new IssueFileDTO();
				issueFileDTO.setSavedPath(filePath);
				issueFileDTO.setOriginalName(originFileName);
				issueFileDTO.setRandomName(savedName);
//				issue.setFile(issueFileDTO);
				fileList.add(issueFileDTO);
				issue.setFile(fileList);
				
				System.out.println("issue : " + issue);
			}
			
			try {
				for(int i = 0; i < multiFiles.size(); i++) {
					
					multiFiles.get(i).transferTo(new File(filePath + "\\" + issue.getFile().get(i).getRandomName()));
					
					System.out.println("이슈 등록 확인 " + issue);
					
				}
				
				rttr.addFlashAttribute("subMessage", "파일 업로드 성공");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				for(int i = 0; i < multiFiles.size(); i++) {
					new File(filePath + "/" + issue.getFile().get(i).getRandomName()).delete();
				}
				rttr.addFlashAttribute("subMessage", "파일 업로드 실패");
			  }
		}
		
		
		/* 이슈 수정 */
		int taskNo = issue.getTaskNo();
		System.out.println("update에 들어오는 업무 no : " + taskNo);
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMember =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		issueService.modifyIssue(issue, loginMember);
		
		System.out.println("modifyIssue : " + issue);
		
		rttr.addFlashAttribute("message", "가이드 게시판 수정에 성공하셨습니다");
		
		mv.addObject("intent", "/issue/update");
		mv.setViewName("redirect:/issue/list/" + taskNo);
		
		return mv;
	}
	
	@GetMapping("/delete")
	public ModelAndView removeGuide(ModelAndView mv, HttpServletRequest request, 
			RedirectAttributes rttr) throws GuideRemoveException {
		
		int issueNo = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("삭제하기 위해 no 받기 " + issueNo );
		
		/* 히스토리에 반영하기 위해서 현재 이슈를 수정한 사람의 정보를 넣어줘야 됨 */
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		/* 반환해줄 경로를 찾기 위해서 뽑아낸 업무 번호 */
		int taskNo = issueService.removeIssue(issueNo, loginMemberNo);
		
		rttr.addFlashAttribute("message", "가이드 게시판 삭제에 성공하셨습니다.");
		
		mv.addObject("intent", "/issue/delete");
		mv.setViewName("redirect:/issue/list/" + taskNo);
		
		return mv;
		
	}
	
	@GetMapping("/notification")
	public ModelAndView notifyIssueList(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		
		int loginMemberNo =   (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		int issueHistoryNo = 0;
		
		if(request.getParameter("issueHistoryNo") != null) {
			issueHistoryNo = Integer.parseInt(request.getParameter("issueHistoryNo"));
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
			System.out.println("issueHistoryNo " + issueHistoryNo);
		}
		
		Map<String, Object> notification = issueService.notifyIssueList(loginMemberNo,issueHistoryNo);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		List<IssueNotificationDTO> notificationList = (List<IssueNotificationDTO>) notification.get("notificationList");
		
		int count = (int) notification.get("count");
		
		
		
		mv.addObject("count", gson.toJson(count));
		mv.addObject("notificationList", gson.toJson(notificationList));
		mv.setViewName("jsonView");
		return mv;
	}
	
}
