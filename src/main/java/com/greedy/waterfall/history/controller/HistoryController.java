package com.greedy.waterfall.history.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.history.model.dto.HistoryDTO;
import com.greedy.waterfall.history.model.service.HistoryService;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;

@RestController
@RequestMapping("/history")
public class HistoryController {

	private final HistoryService historyService;
	
	@Autowired
	private HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	@GetMapping("/list")
	public ModelAndView projectHitoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		List<HistoryDTO> projectHistoryList = historyService.selectProjectHistoryList(projectNo);
		System.out.println("업무 히스토리 목록은 : " + projectHistoryList);
		
		mv.addObject("projectHistoryList", projectHistoryList);
		mv.addObject("intent", "/history/list");
		mv.setViewName("/history/projectHistory");
		return mv;
	}
	
	@GetMapping("/task")
	public ModelAndView taskHistoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		List<HistoryDTO> taskHistoryList = historyService.selectTaskHistoryList(projectNo);
		System.out.println("업무 히스토리 목록은 : " + taskHistoryList);
		
		mv.addObject("taskHistoryList", taskHistoryList);
		mv.addObject("intent", "/history/task");
		mv.setViewName("/history/taskHistory");
		return mv;
	}
	
	@GetMapping("/issue")
	public ModelAndView issueHistoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		System.out.println("projectNo :" + projectNo);
		
		List<HistoryDTO> issueHistoryList = historyService.selectIssueHistoryList(projectNo);
		System.out.println("이슈 히스토리 목록은 : " + issueHistoryList);
		
		mv.addObject("issueHistoryList", issueHistoryList);
		mv.addObject("intent", "/history/issue");
		mv.setViewName("/history/issuehistory");
		return mv;
	}
	
	@GetMapping("/output")
	public ModelAndView outputHistoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		System.out.println("projectNo :" + projectNo);
		
		List<HistoryDTO> outputHistoryList = historyService.selectOutputHistoryList(projectNo);
		System.out.println("산출물 히스토리 목록은 : " + outputHistoryList);
		
		mv.addObject("outputHistoryList", outputHistoryList);
		mv.addObject("intent", "/history/output");
		mv.setViewName("/history/outputHistory");
		return mv;
	}
	
	@GetMapping("/member")
	public ModelAndView memberHistoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		System.out.println("projectNo :" + projectNo);
		
		List<HistoryDTO> memberHistoryList = historyService.selectMemberHistoryList(projectNo);
		System.out.println("회원 히스토리 목록은 : " + memberHistoryList);
		
		mv.addObject("memberHistoryList", memberHistoryList);
		mv.addObject("intent", "/history/member");
		mv.setViewName("/history/memberHistory");
		return mv;
	}
	
	@GetMapping("/board")
	public ModelAndView boardHistoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		System.out.println("projectNo :" + projectNo);
		
		List<HistoryDTO> boardHistoryList = historyService.selectBoardHistoryList(projectNo);
		System.out.println("게시판 히스토리 목록은 : " + boardHistoryList);
		
		mv.addObject("boardHistoryList", boardHistoryList);
		mv.addObject("intent", "/history/board");
		mv.setViewName("/history/boardHistory");
		return mv;
	}
}
