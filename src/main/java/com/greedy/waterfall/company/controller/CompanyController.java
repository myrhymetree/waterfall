package com.greedy.waterfall.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;
import com.greedy.waterfall.company.model.service.CompanyService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.common.exception.company.JobModifyException;
import com.greedy.waterfall.common.exception.company.JobRegistException;
import com.greedy.waterfall.common.exception.company.JobRemoveException;

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
		mv.addObject("intent", "/company/job/list");
		mv.setViewName("/company/job/jobList");

		return mv;
	}

	@PostMapping("/job/regist")
	public String registJob(@ModelAttribute JobDTO job, HttpServletRequest request, RedirectAttributes rttr)
			throws JobRegistException {

		int rank = Integer.parseInt(request.getParameter("rank"));
		String name = request.getParameter("name");
		String code = request.getParameter("code");

		job.setRank(rank);
		job.setName(name);
		job.setCode(code);

		companyService.registJob(job);

		rttr.addFlashAttribute("message", "직급 등록에 성공하셨습니다!");

		return "redirect:/company/job/list";
	}
	
	@GetMapping(value = "jobDetail")
	@ResponseBody
	public ModelAndView detailJob(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		
		String code = request.getParameter("code");
		
		JobDTO jobDetail = companyService.detailJob(code);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
		        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		        .serializeNulls()
		        .disableHtmlEscaping()
		        .create();
		
		mv.addObject("jobDetail", gson.toJson(jobDetail));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	/* 직급 수정 */
	@PostMapping("/job/update")
	public String modifyJob(@ModelAttribute JobDTO job,
			HttpServletRequest request, RedirectAttributes rttr) throws JobModifyException {
		
		companyService.modifyJob(job);
		
		rttr.addFlashAttribute("message", "직급 수정에 성공하셨습니다.");
		
		return "redirect:/company/job/list";
	}
	
	/* 직급 삭제 */
	@GetMapping("/job/delete")
	public String removeJob(HttpServletRequest request, RedirectAttributes rttr) throws JobRemoveException {
		
		String code = request.getParameter("code");
		
		companyService.removeJob(code);
		
		rttr.addFlashAttribute("message", "직급 삭제에 성공하셨습니다!");
		
		return "redirect:/company/job/list";
	}
	 
}
