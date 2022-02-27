package com.greedy.waterfall.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.member.model.dto.MemberDTO;
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
	public ModelAndView registProjectMember(ModelAndView mv,@RequestParam("projectRole") List<String> projectRole
										, @RequestParam Map<String, String> registInfo) {

    	sendToResultView(mv, pms.registProjectMember(parsingMemberInfoForProjectRegist(projectRole, registInfo)));

		return mv;
	}
	
	private void sendToResultView(ModelAndView mv, boolean result) {
		if(result) {
			mv.setViewName("redirect:/manage/member/list");
		} 
	}
	
	private ProjectManageMemberDTO parsingMemberInfoForProjectRegist(List<String> projectRole,Map<String, String> registInfo ) {
		
		Iterator<String> key = registInfo.keySet().iterator();
		while(key.hasNext()) {
			String k = key.next();
			String value = registInfo.get(k);
			System.out.println(k + " : " + value);
		}
		
		
		ProjectManageMemberDTO memberInfo = new ProjectManageMemberDTO().builder()
												.projectNo(parseInt(registInfo, "projectNo"))
												.memberNo(parseInt(registInfo, "memberNo"))
												.managerNo(parseInt(registInfo, "managerNo"))
												.role(parseProjectRoleToList(projectRole))
												.build();

		return memberInfo;
	}
	
	private int parseInt(Map<String, String> parameter, String key) {
		
		return Integer.parseInt(parameter.get(key));
	}
	
	private int parseInt(List<String> parameter, int index) {
		
		return Integer.parseInt(parameter.get(index));
	}
	
	private List<ProjectRoleDTO> parseProjectRoleToList(List<String> projectRole) {
		List<ProjectRoleDTO> memberRoleList = new ArrayList<ProjectRoleDTO>();
		
		for(int i = 0; i < projectRole.size(); i++) {
			System.out.println(projectRole.get(i));
			ProjectRoleDTO role = new ProjectRoleDTO().builder().roleNo(parseInt(projectRole, i)).build();
			memberRoleList.add(role);
		}
		
		return memberRoleList;
	}
	
	
	@GetMapping("/member/find")
	public ModelAndView findTeamMember(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<MemberDTO> memberList = pms.findTeamMember(parsingMemberInfoByTeam(request));
		
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("memberList", mapper.writeValueAsString(memberList));
		mv.setViewName("jsonView");
		return mv;
	}
	
	private Map<String, String> parsingMemberInfoByTeam(HttpServletRequest request) {
		Map<String, String> memberInfo = new HashMap<String, String>();
		memberInfo.put("teamCode", request.getParameter("teamCode"));
		memberInfo.put("projectNo", request.getParameter("projectNo"));
		
		return memberInfo;
	}
	
	@GetMapping("/member/detail")
	public ModelAndView findMemberDetail(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Integer> memberInfo = new HashMap<String, Integer>();
		memberInfo.put("memberNo", Integer.parseInt(request.getParameter("memberNo")));
		memberInfo.put("projectNo", ((ProjectAuthorityDTO)request.getSession().getAttribute("projectAutority")).getProjectNo());
		
		List<ProjectRoleDTO> memberRoleList = pms.findMemberRole(memberInfo);
		
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("memberRoleList", mapper.writeValueAsString(memberRoleList));
		mv.setViewName("jsonView");
		
		return mv;
	}

	@PostMapping("/member/modify")
	public ModelAndView modifyMemberInProject(ModelAndView mv, @RequestParam("projectRole") List<String> projectRole
			, @RequestParam Map<String, String> modifyInfo) {
	
		sendToResultView(mv, pms.modifyProjectMember(parsingMemberInfoForProjectRegist(projectRole, modifyInfo)));
		
		return mv;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	




























