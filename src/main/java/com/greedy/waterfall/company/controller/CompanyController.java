package com.greedy.waterfall.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView deptManage(HttpServletRequest request, ModelAndView mv) {
		
		mv.addObject("intent", "/company/dept/deptList");
		mv.setViewName("/company/dept/deptList");
		
		return mv;
	}
}
