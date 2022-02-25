package com.greedy.waterfall.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.service.ProjectManageService;

@Controller
@RequestMapping("/manage/*")
public class ProjectManageController {

	private final ProjectManageService pms;
	
	public ProjectManageController(ProjectManageService pms) {
		this.pms = pms;
	}
	
	@GetMapping("/member/list")
	public ModelAndView findProjectMember(ModelAndView mv, HttpSession session) {
		
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		List<ProjectManageMemberDTO>projectMemberList = pms.findProjectMember(projectNo);
		
		mv.addObject("projectMemberList", projectMemberList);
		mv.setViewName("/manage/memberList");
				
		return mv;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}




























