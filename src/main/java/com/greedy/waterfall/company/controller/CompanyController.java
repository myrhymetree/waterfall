package com.greedy.waterfall.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.CompanyDTO;
import com.greedy.waterfall.company.model.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	private final CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("/dept/list")
	public ModelAndView deptSelectList(HttpServletRequest request, ModelAndView mv) {
		
		SelectCriteria selectCriteria = null;
		List<CompanyDTO> companyList = companyService.findCompany(selectCriteria);
		mv.addObject("companyList", companyList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/company/dept/deptList");
		mv.setViewName("/company/dept/deptList");
		
		return mv;
	}
	
	@GetMapping("/job/list")
	public ModelAndView jobSelectList(HttpServletRequest request, ModelAndView mv) {
		
		SelectCriteria selectCriteria = null;
		List<CompanyDTO> companyList = companyService.findCompany(selectCriteria);
		mv.addObject("companyList", companyList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/company/job/jobList");
		mv.setViewName("/company/job/jobList");
		
		return mv;
	}
	
}
