package com.greedy.waterfall.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.project.model.service.ProjectService;

@RequestMapping("/project/*")
@Controller
public class ProjectController {
//
//	private final ProjectService projectService;
//	
//	@Autowired
//	public ProjectController(ProjectService projectService) {
//		this.projectService = projectService;
//	}
//	
	
	@GetMapping("/list")
	public ModelAndView findProjectList(ModelAndView mv) {
		
		
		mv.setViewName("/project/projectList");
		return mv;
	}
	
	
	
	
	
	
	
}
