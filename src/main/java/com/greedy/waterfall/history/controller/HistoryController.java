package com.greedy.waterfall.history.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
	
//	public static Object getBean(String beanName) {
//        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
//        return context.getBean(beanName);
//   }

	/* 클래스에서 HttpServletRequest 객체를 직접 얻음 */
//	public static HttpServletRequest getRequest() {
//        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//        return attr.getRequest();
//    }
	
//	int projectNo = (((ProjectAuthorityDTO) getRequest().getSession().getAttribute("projectAutority")).getProjectNo());

	@GetMapping("/list")
	public ModelAndView hitoryList(HttpServletRequest request, ModelAndView mv) {
		
		List<HistoryDTO> historyList = historyService.selectEntireHistoryList();
		
		mv.addObject("historyList", historyList);
		mv.addObject("intent", "/history/list");
		return mv;
	}
	
	@GetMapping("/task")
	public ModelAndView taskHistoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		List<HistoryDTO> issueHistoryList = historyService.selectIssueHistoryList(projectNo);
		System.out.println("이슈 히스토리 목록은 : " + issueHistoryList);
		
		mv.addObject("issueHistoryList", issueHistoryList);
		mv.addObject("intent", "/history/issue");
		mv.setViewName("/history/historyList");
		return mv;
	}
	
	@GetMapping("/issue")
	public ModelAndView issueHistoryList(HttpServletRequest request, ModelAndView mv) {
		
		int projectNo = (((ProjectAuthorityDTO) request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		System.out.println("projectNo :" + projectNo);
		
		List<HistoryDTO> issueHistoryList = historyService.selectIssueHistoryList(projectNo);
		System.out.println("이슈 히스토리 목록은 : " + issueHistoryList);
		
		mv.addObject("issueHistoryList", issueHistoryList);
		mv.addObject("intent", "/history/issue");
		mv.setViewName("/history/historyList");
		return mv;
	}
}
