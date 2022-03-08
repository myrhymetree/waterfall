package com.greedy.waterfall.output.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.output.model.dto.OutputAttachmentDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.output.model.service.OutputService;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

@Controller
@RequestMapping("/output")
@SessionAttributes("projectAutority")
public class OutputController {
	
	private final OutputService outputService;
	
	@Autowired
	public OutputController(OutputService outputService) {
		this.outputService = outputService;
	}
	
	@GetMapping("/list")
	public ModelAndView outputFindList( HttpServletRequest request, ModelAndView mv, HttpSession session) {
		
		TaskDTO taskDTO = new TaskDTO();
		
		int projectNo = 0;
		int memberNo =  (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		/* 현재 진행중인 프로젝트의 번호를 세션에 있으면 세션에서 가져오고 없으면 parameter로 가져온다 */
		if((session.getAttribute("projectAutority")) != null) {
			projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		} else {
			projectNo = Integer.parseInt(request.getParameter("projectNo"));
		}
		
		
		taskDTO.setProjectNo(projectNo);
		List<TaskDTO> parentTaskList = new ArrayList<>();
		
		parentTaskList = outputService.findOutputTask(taskDTO);
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.setViewName("output/outputDetail");
		
		return mv; 
		
	}
	
	@GetMapping("/admin/detail")
	public ModelAndView outputAdminFindList( HttpServletRequest request, ModelAndView mv, HttpSession session) {
		
		TaskDTO taskDTO = new TaskDTO();
		
		int projectNo = 0;
		int memberNo =  (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		/* 현재 진행중인 프로젝트의 번호를 세션에 있으면 세션에서 가져오고 없으면 parameter로 가져온다 */
		if((session.getAttribute("projectAutority")) != null) {
			projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		} else {
			projectNo = Integer.parseInt(request.getParameter("projectNo"));
		}
		
		
		taskDTO.setProjectNo(projectNo);
		List<TaskDTO> parentTaskList = new ArrayList<>();
		
		parentTaskList = outputService.findOutputTask(taskDTO);
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.setViewName("admin/outputAdminDetail");
		
		return mv; 
		
	}
	
	/**
	 * findAdminOutputList : 관리자 프로젝트 별 산출물 개수 조회
	 * @param 
	 * @return 산출물 정보를 담은 전체 프로젝트 리스트
	 * 
	 * @author 김서영
	 */
	@GetMapping("/admin/list")
	public ModelAndView findAdminOutputList(ModelAndView mv, HttpSession session) {
		
		/* 프로젝트 당 존재하는 산출물 개수를 return 하자 */
		
		List<OutputProjectDTO> projectList = outputService.findOutputList();
		
		mv.addObject("projectList", projectList);
		mv.setViewName("admin/outputList");
		
		return mv;
		
	}
	
	
	/**
	 * findOutputDetail : 하위 업무 선택시 해당하는 업무의 산출물 상세 정보
	 * @param 클릭한 task의 번호
	 * @return 클릭한 업무에 해당하는 산출물 정보
	 * 
	 * @author 김서영
	 * @throws JsonProcessingException 
	 */
	@GetMapping("/detail")
	public ModelAndView findOutputDetail(ModelAndView mv, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws JsonProcessingException {
		
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		
		System.out.println("outputDatail no 넘어오나? : " + taskNo);
		
		Map<String, Object> map = (Map<String, Object>) outputService.findOutputDetail(taskNo);
		
		TaskDTO parentTask = (TaskDTO) map.get("parentTask");
		ChildTaskDTO childTask = (ChildTaskDTO) map.get("childTask");
		OutputDTO outputDetail = (OutputDTO) map.get("outputDetail");
		OutputAttachmentDTO outputFile = (OutputAttachmentDTO) map.get("outputFile");
		
		response.setContentType("application/json; charset=UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    mapper.setDateFormat(dateFormat);
		
		mv.addObject("parentTask", mapper.writeValueAsString(parentTask));
		mv.addObject("childTask", mapper.writeValueAsString(childTask));
		mv.addObject("outputDetail", mapper.writeValueAsString(outputDetail));
		mv.addObject("outputFile", mapper.writeValueAsString(outputFile));
		mv.setViewName("jsonView");
		
		
		return mv;
	}
	
	/**
	 * removeOutput : 클릭한 산출물 삭제
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@GetMapping("/delete")
	public String removeOutput(HttpServletRequest request, RedirectAttributes rttr) {
		
		int outputNo = Integer.parseInt(request.getParameter("outputNo"));
		int memberNo =  (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		
		OutputDTO output = new OutputDTO();
		
		output.setMemberNo(memberNo);
		output.setOutputNo(outputNo);
		
		outputService.removeOutput(output);
		
		rttr.addFlashAttribute("message", "산출물 삭제에 성공하셨습니다.");
		
		return "redirect:/output/list";
	}
	
	/**
	 * registOutput : 업무에서 산출물 등록
	 * @param 
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@PostMapping("/regist")
	public String registOutput(HttpServletRequest request, RedirectAttributes rttr, HttpSession session
	                           ,@RequestParam("outputFile") MultipartFile outputFile) {
		
		/* 등록할 정보들 */
		int memberNo =  (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		System.out.println("taskNo 넘어오냐? : " + taskNo);
		
		String content = request.getParameter("content");
		System.out.println("content 확인 : " + content);
		
		/* 등록할 산출물 정보 dto에 담음*/
		OutputDTO output = new OutputDTO();
		output.setMemberNo(memberNo);
		output.setProjectNo(projectNo);
		output.setContent(content);
		output.setTaskNo(taskNo);
		System.out.println("mapping된 outputDTO 확인 : " + output );
		
		/* 파일 저장될 root 설정 */
		String root = request.getServletContext().getRealPath("resources");
		System.out.println("file root : " +root);
		String fileUploadDirectory = root + "/outputUpload/outputOriginal";
		
		/* filepath 경로인 폴더가 존재하는지 확인  */
		File directory = new File(fileUploadDirectory);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		if(outputFile.getSize() > 0) {
			/* request로 들어온 파일 확인 */
			System.out.println("singleFile : " + outputFile);
			
			/* savedName 생성 */
			String originFileName = outputFile.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			System.out.println("originFileName : " + originFileName);
			System.out.println("savedName : " + savedName);
			
			/* attachmentDTO에 file 정보 저장해서 outputDTO에 set */
			OutputAttachmentDTO attachmentDTO = new OutputAttachmentDTO();
			attachmentDTO.setFilePath(fileUploadDirectory);
			attachmentDTO.setOriginName(originFileName);
			attachmentDTO.setRandomName(savedName);
			output.setOutputFile(attachmentDTO);
			
			try {
				outputFile.transferTo(new File(fileUploadDirectory + "/" + savedName));
				
				int result = outputService.registOutput(output);
				
				if(result == 0) {
					rttr.addFlashAttribute("message", "산출물이 이미 존재합니다.");
				}else {
					rttr.addFlashAttribute("message", "산출물이 등록되었습니다.");
				}
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				new File(fileUploadDirectory + "\\" + savedName).delete();
			}
			
			
		} 
		
		
		return "redirect:/task/timeline";
		
	}
	
	@GetMapping("/download/{refOutputNo}")
	public ModelAndView downloadFile(ModelAndView mv, @PathVariable("refOutputNo") String refOutputNo) throws IOException {
		int outputNo = Integer.parseInt(URLDecoder.decode(refOutputNo, "UTF-8"));
		
		System.out.println("outputNo 넘어오니???????????? : " + outputNo);
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		
		OutputAttachmentDTO file = outputService.findOutputFile(outputNo);
		fileInfo.put("filePath", file.getFilePath());
		fileInfo.put("fileOriginName", file.getOriginName());
		fileInfo.put("fileRandomName", file.getRandomName());
		mv.addObject("downloadFile", fileInfo);
		mv.setViewName("fileDownloadView");
		
		return mv;
	}
	
	@PostMapping("/update")
	public String modifyOutput(HttpServletRequest request, RedirectAttributes rttr, HttpSession session,
			                   @RequestParam("outputFile") MultipartFile outputFile) {
		
		/* 업데이트 할 정보들 */
		int memberNo =  (((MemberDTO) request.getSession().getAttribute("loginMember")).getNo());
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		int outputNo = Integer.parseInt(request.getParameter("outputNo"));
		String content = request.getParameter("content");
		System.out.println("content 확인 : " + content);
		System.out.println("outputFile 확인 : " + outputFile);
		
		/* 수정할 산출물 정보 dto에 담음*/
		OutputDTO output = new OutputDTO();
		output.setMemberNo(memberNo);
		output.setProjectNo(projectNo);
		output.setOutputNo(outputNo);
		output.setContent(content);
		
		/* 파일 저장될 root 설정 */
		String root = request.getServletContext().getRealPath("resources");
		System.out.println("file root : " +root);
		String fileUploadDirectory = root + "/outputUpload/outputOriginal";
		
		/* filepath 경로인 폴더가 존재하는지 확인  */
		File directory = new File(fileUploadDirectory);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		/*outputFile이 있을 경우*/
		if(outputFile.getSize() > 0) {
			/* request로 들어온 파일 확인 */
			System.out.println("singleFile : " + outputFile);
			
			/* savedName 생성 */
			String originFileName = outputFile.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			System.out.println("originFileName : " + originFileName);
			System.out.println("savedName : " + savedName);
			
			/* attachmentDTO에 file 정보 저장해서 outputDTO에 set */
			OutputAttachmentDTO attachmentDTO = new OutputAttachmentDTO();
			attachmentDTO.setFilePath(fileUploadDirectory);
			attachmentDTO.setOriginName(originFileName);
			attachmentDTO.setRandomName(savedName);
			output.setOutputFile(attachmentDTO);
			
			try {
				outputFile.transferTo(new File(fileUploadDirectory + "/" + savedName));
				
				outputService.modifyOutput(output);
				rttr.addFlashAttribute("message", "산출물이 수정되었습니다.");
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				new File(fileUploadDirectory + "\\" + savedName).delete();
			}
		
		/*file 이 없이 본문 내용만 수정할 경우*/
		} else {
			outputService.modifyOutput(output);
			rttr.addFlashAttribute("message", "본문이 수정되었습니다.");
		}
		
		
		
		
		return "redirect:/output/list";
	}
	
	@GetMapping("/count")
	public ModelAndView countOutput(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException  {
		
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		
		int result = outputService.selectOutputCount(taskNo);
		
		response.setContentType("application/json; charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		
		/* output이 없는 경우 1 있는 경우 0 */
		if(result == 1) {
			mv.addObject("result",mapper.writeValueAsString(result));
			mv.setViewName("jsonView");
		}else {
			result = 0;
			mv.addObject("result", mapper.writeValueAsString(result));
			mv.setViewName("jsonView");
		}
		
		
		return mv;
		
	}
	
	

}
