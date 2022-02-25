package com.greedy.waterfall.issue.controller;


import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;
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
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueRegisterDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.service.IssueService;
import com.greedy.waterfall.member.model.dto.MemberDTO;


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
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
//		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		

//		
		System.out.println("컨트롤러에서 검색 조건 확인하기 : " + searchValue);
		
		int totalCount = issueService.selectTotalCount(searchMap);
		
		System.out.println("totalCount : " + totalCount);
		
		int limit = 10;
		
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		System.out.println(selectCriteria);
		
//		List<IssueDTO> projectList = issueService.selectAllProjectList(selectCriteria);
//		int projectNo = (int) (request.getAttribute("projectNo"));
//	
//		Map<String, Integer> projectNoMap = new HashMap<>();
//		projectNoMap.put("projectNo", projectNo);
		
//		System.out.println("projectNo" + projectNo);

		List<ProjectIssueCountDTO> allProject = issueService.selectAllProjectList();
		System.out.println("allProject" + allProject);
		
		mv.addObject("allProject", allProject);
		mv.addObject("intent", "/issue/project");
		mv.setViewName("/issue/projectList");
		
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView issueList(HttpServletRequest request, ModelAndView mv) {
		
//		String currentPage = request.getParameter("currentPage");
//		int pageNo = 1;
//		
//		if(currentPage != null && !"".equals(currentPage)) {
//			pageNo = Integer.parseInt(currentPage);
//		}
//		
//		String searchCondition = request.getParameter("searchCondition");
//		String searchValue = request.getParameter("searchValue");
////		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
//		
//		Map<String, String> searchMap = new HashMap<>();
//		searchMap.put("searchCondition", searchCondition);
//		searchMap.put("searchValue", searchValue);
		
//		Map<String, Integer> projectNoMap = new HashMap<>();
//		projectNoMap.put("projectNo", projectNo);
//		
//		System.out.println("컨트롤러에서 검색 조건 확인하기 : " + searchValue);
//		
//		int totalCount = issueService.selectTotalCount(searchMap);
//		
//		System.out.println("totalCount : " + totalCount);
//		
//		int limit = 100;
//		
//		int buttonAmount = 5;
//		
//		SelectCriteria selectCriteria = null;
//		
//		if(searchCondition != null && !"".equals(searchCondition)) {
//			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
//		} else {
//			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
//		}
		
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		
		System.out.println(taskNo);
		
		List<IssueDTO> issueList = issueService.selectIssueList(taskNo);
		
		mv.addObject("issueList", issueList);
//		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/issue/list");
		mv.setViewName("/issue/issueList");
		
		return mv;
	}
	
	@GetMapping("/task*")
	public ModelAndView taskList(HttpServletRequest request, ModelAndView mv) {
		
//		int projectNo = (int) (request.getAttribute("projectNo"));
		
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
//	
//		Map<String, Integer> projectNoMap = new HashMap<>();
//		projectNoMap.put("projectNo", projectNo);
		
		System.out.println("projectNo :" + projectNo);

		List<IssueDTO> taskIssueList = issueService.selectIssuesOfTask(projectNo);

		
		mv.addObject("taskIssueList", taskIssueList);
		mv.addObject("intent", "/issue/task");
		mv.setViewName("/issue/taskList");
//		mv.setViewName("/issue/taskIssues");
		
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
	
	@PostMapping("/regist")
	public String registIssue(@ModelAttribute IssueDTO issue, HttpServletRequest request,
			RedirectAttributes rttr, @RequestParam List<IssueFileDTO> multiFiles, HttpSession session) {
		
		int register = (int) session.getAttribute("loginMember");
		issue.setRegisterNo(register);
		
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		issue.setTaskNo(taskNo);
		
		System.out.println("MultiFiles : " + multiFiles);
		
		String root = request.getSession().getServletContext().getRealPath("resource");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/resources/issueUploadFiles";
		
		File mkdir = new File(filePath);
		
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		if(((MultipartFile) multiFiles).getSize() > 0) {
			
			String originFileName = ((MultipartFile) multiFiles).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			System.out.println("originFileName" + originFileName);
			System.out.println("savedName : " + savedName);
			
			IssueFileDTO issueFileDTO = new IssueFileDTO();
			issueFileDTO.setSavedPath(filePath);
			issueFileDTO.setOriginalName(originFileName);
			issueFileDTO.setRandomName(savedName);
			issue.setFile(issueFileDTO);
			
			try {
				((MultipartFile) multiFiles).transferTo(new File(filePath + "\\" + savedName));
				
				System.out.println("이슈 등록 확인 " + issue);
				
				issueService.registIssue(issue);
				
				rttr.addFlashAttribute("message", "파일 업로드 성공");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				
				new File(filePath + "/" + savedName).delete();
				rttr.addFlashAttribute("message", "파일 업로드 실패");
			}
			
		} else {
			issueService.registIssue(issue);
			
			rttr.addFlashAttribute("message", "이슈 등록에 성공하셨습니다.");
		}
		
		return "redirect:/issue/list";
	}
	
	@GetMapping(("/regist/task/{taskNo}"))
	public ModelAndView selectTask(ModelAndView mv, @PathVariable("taskNo") int taskNo, HttpServletResponse response) throws IOException {

		List<IssueDTO> taskList = issueService.selectTask(taskNo);
		
		System.out.println(taskList);
		
		System.out.println("taskNo : " + taskNo);
		
		for(int i = 0; i < taskList.size(); i++) {
			System.out.println("taskList[i] : " + taskList.get(i));
		}
		
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("taskList", mapper.writeValueAsString(taskList));
		mv.setViewName("jsonView");
		return mv;
	}

}
