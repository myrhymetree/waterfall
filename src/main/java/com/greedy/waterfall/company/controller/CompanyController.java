package com.greedy.waterfall.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;
import com.greedy.waterfall.company.model.service.CompanyService;
import com.greedy.waterfall.common.exception.company.JobRegistException;

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
		List<DeptDTO> deptList = companyService.findDept(selectCriteria);
		mv.addObject("deptList", deptList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/company/dept/deptList");
		mv.setViewName("/company/dept/deptList");
		
		return mv;
	}
	
	@GetMapping("/job/list")
	public ModelAndView jobSelectList(HttpServletRequest request, ModelAndView mv) {
		
		SelectCriteria selectCriteria = null;
		List<JobDTO> jobList = companyService.findJob(selectCriteria);
		mv.addObject("jobList", jobList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/company/job/jobList");
		mv.setViewName("/company/job/jobList");
		
		return mv;
	}
	
	@PostMapping("/job/regist")
	public String registJob(@ModelAttribute JobDTO job, HttpServletRequest request,
			RedirectAttributes rttr) throws JobRegistException {
		
		int rank = Integer.parseInt(request.getParameter("rank"));
		String name = request.getParameter("name");
		
		job.setRank(rank);
		job.setName(name);
		
		companyService.registJob(job);
		
		rttr.addFlashAttribute("message", "직급 등록에 성공하셨습니다!");
		
		return "redirect:/job/list";
	}
}
