package com.greedy.waterfall.issue.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueRegisterDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.service.IssueService;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;


@RestController
@RequestMapping("/issue")
public class IssueController {
	
	private final IssueService issueService;
	
	@Autowired
	private IssueController(IssueService IssueService) {
		this.issueService = IssueService;
	}
	
	@GetMapping("/project")
	public ModelAndView projectList(HttpServletRequest request, ModelAndView mv) {
		
		int No = (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		Map<String, Integer> managerNo = new HashMap<>();
		managerNo.put("managerNo", No);

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
		
		
		mv.addObject("taskIssueList", taskIssueList);
		mv.addObject("intent", "/issue/task");
		mv.setViewName("/issue/adminTaskList");
		
		return mv;
	}
	
	@GetMapping("/admin/list/{taskNo}")
	public ModelAndView adminIssueList(@PathVariable("taskNo") int taskNo, HttpServletRequest request, ModelAndView mv) {
		
//		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		
		System.out.println(taskNo);
		
		List<IssueDTO> issueList = issueService.selectIssueList(taskNo);
		
		mv.addObject("projectNo", issueList.get(0).getProjectNo());
		mv.addObject("taskNo", taskNo);
		mv.addObject("issueList", issueList);
		mv.addObject("intent", "/issue/list");
		mv.setViewName("/issue/adminIssueList");
		
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

//		mv.setViewName("redirect:/issue/list/"+taskNo);
		return mv;
	}
	
// 뭐할려고 했던건지를 모르겠다...	
//	@GetMapping("/regist/task/{taskNo}")
//	public ModelAndView selectTask(ModelAndView mv, @PathVariable("taskNo") int taskNo, HttpServletResponse response) throws IOException {
//
//		List<IssueDTO> taskList = issueService.selectTask(taskNo);
//		
//		System.out.println(taskList);
//		
//		System.out.println("taskNo : " + taskNo);
//		
//		for(int i = 0; i < taskList.size(); i++) {
//			System.out.println("taskList[i] : " + taskList.get(i));
//		}
//		
//		response.setContentType("application/json; charset=UTF-8");
//		ObjectMapper mapper = new ObjectMapper();
//		
//		mv.addObject("taskList", mapper.writeValueAsString(taskList));
//		mv.setViewName("jsonView");
//		return mv;
//	}
	
}
