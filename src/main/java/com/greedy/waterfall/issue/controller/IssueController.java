package com.greedy.waterfall.issue.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.service.IssueService;


@Controller
@RequestMapping("/issue")
public class IssueController {
	
	private final IssueService issueService;
	
	@Autowired
	private IssueController(IssueService IssueService) {
		this.issueService = IssueService;
	}
	
	@GetMapping("/list")
	public ModelAndView issueList(HttpServletRequest request, ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
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
		
		List<IssueDTO> issueList = issueService.selectAllIssueList(selectCriteria);
		
		mv.addObject("issueList", issueList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/issue/list");
		mv.setViewName("/issue/issueList");
		
		return mv;
	}
	
}
