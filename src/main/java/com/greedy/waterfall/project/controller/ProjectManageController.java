package com.greedy.waterfall.project.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;
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
		Map<String, Object> manageProjectMemberInfo = pms.findProjectMember(projectNo);
		List<ProjectManageMemberDTO> projectMemberList = (List<ProjectManageMemberDTO>) manageProjectMemberInfo.get("memberList");
		List<ProjectRoleDTO> allRole = (List<ProjectRoleDTO>) manageProjectMemberInfo.get("allRole");
		List<DeptDTO> allDept = (List<DeptDTO>) manageProjectMemberInfo.get("allDept");
		mv.addObject("projectMemberList", projectMemberList);
		mv.addObject("allRole", allRole);
		mv.addObject("allDept", allDept);
		mv.setViewName("/manage/memberList");
		
		for(int i = 0; i < allRole.size(); i++) {
			System.out.println(allRole.get(i));
			
		}
				
		return mv;
	}
	
	@PostMapping("/member/regist")
	public ModelAndView registProjectMember(ModelAndView mv, @RequestParam Map<String, Object> parameter) {
		
		System.out.println("member/regist test 1");
		Iterator<String> keys = parameter.keySet().iterator();
		
		while(keys.hasNext()) {
			String strKey = keys.next();
			Object strValue = parameter.get(strKey);
			System.out.println(strKey + " : " + strValue);
		}
		System.out.println("member/regist test 2");
		for(String strKey : parameter.keySet()) {
			Object strValue = parameter.get(strKey);
			System.out.println(strKey + " : " + strValue);
		}
		System.out.println("member/regist test 3");
		for(Map.Entry<String, Object> entry : parameter.entrySet() ) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + " : " + value);
		}
		parameter.forEach((key, value)-> {
			System.out.println(key + " : " + value);
		});
		
		
		
		mv.setViewName("redirect:/member/list");
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}




























