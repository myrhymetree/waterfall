package com.greedy.waterfall.project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;
import com.greedy.waterfall.project.model.service.ProjectService;

/**
 * <pre>
 * Class : ProjectController
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
/**
 * <pre>
 * Class : ProjectController
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Controller
@RequestMapping("/project/*")
public class ProjectController {
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		System.out.println("ProjectService created...");
		this.projectService = projectService;
	}

	@GetMapping("/regist")
	public ModelAndView sendRegistPage(ModelAndView mv) {
		
		Map<String, Object> projectForm = projectService.findRegistForm();

		List<ProjectStatusDTO> statusList = (List<ProjectStatusDTO>) projectForm.get("statusList");
		List<DeptDTO> deptList = (List<DeptDTO>) projectForm.get("deptList");
		
		
		for(int i = 0; i < statusList.size(); i++) {
			System.out.println("statusList.get(i) : " + statusList.get(i));
		}
		for(int i = 0; i < deptList.size(); i++) {
			System.out.println("deptList.get(i) : " + deptList.get(i));
		}
		
		mv.addObject("statusList", statusList);
		mv.addObject("deptList", deptList);
		mv.setViewName("/project/projectRegist");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public ModelAndView registProject(ModelAndView mv, @RequestParam Map<String, String> parameter) {
		
		String projectName = parameter.get("projectName");
		String startDate = parameter.get("startDate");
		String deadLine = parameter.get("deadLine");
		String pm = parameter.get("pm");
		String dept = parameter.get("dept");
		String team = parameter.get("team");
		String pmNumber = parameter.get("pmNumber");
		String projectStatus = parameter.get("projectStatus");
		
		RegistProjectDTO newProject = new RegistProjectDTO().builder()
										.projectName(projectName)
										.startDate(java.sql.Date.valueOf(startDate))
										.deadLine(java.sql.Date.valueOf(deadLine))
										.pmNumber(Integer.parseInt(pmNumber))
										.projectStatus(projectStatus)
										.dept(dept)
										.team(team)
										.build();
		
		if(projectService.registProject(newProject)) {
			System.out.println("프로젝트 생성 성공!");
		} else {
			System.out.println("프로젝트 생성 실패!");
		}
		
		mv.setViewName("redirect:managelist");
		
		return mv;
	}
	
	
	
	@GetMapping("/manage")
	public ModelAndView sendManageProjectList(ModelAndView mv) {

		mv.setViewName("redirect:/project/managelist");
		
		return mv;
	}
	
	/* 회원의 정보를 세션에서 받아서 권한이 있는 프로젝트를 조회한다. */
	@RequestMapping("/managelist")
	public ModelAndView findManageProjectList(ModelAndView mv, HttpSession session) {
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트");
		/* 회원의 정보로 프로젝트를 조회 */
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		MyProjectDTO project = projectService.findMyProject(member);
		List<ProjectDTO> manageProject = project.getManageProject();
		System.out.println("member : " + member);
		System.out.println("project : " + manageProject);
				
		mv.addObject("manageProject", manageProject);
		
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		System.out.println("managelist 컨트롤러에서 manageProject 출력 테스트종료");
		mv.setViewName("/project/projectManage");
		return mv;
	}
	
	/**
	 * findProjectList : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/list")
	public ModelAndView findProjectList(HttpSession session, ModelAndView mv) {
		/* 회원번호로 조회 */
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		MyProjectDTO project = projectService.findMyProject(member);
		System.out.println("member : " + member);
		System.out.println("project : " + project);
		mv.addObject("projectList", project);
		mv.setViewName("/project/projectList");

		return mv;
	}
	
	@GetMapping("/regist/team/{deptCode}")
	public ModelAndView findTeam(ModelAndView mv, @PathVariable("deptCode") String deptCode, HttpServletResponse response) throws IOException {
	
		List<TeamDTO> teamList = projectService.findTeam(deptCode);
		
		
		System.out.println("deptCode : " + deptCode);
		System.out.println();
		for(int i = 0; i < teamList.size(); i++) {
			System.out.println("     teamList[i] : " + teamList.get(i));
		}

		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("teamList", mapper.writeValueAsString(teamList));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("/regist/member/{teamCode}")
	public ModelAndView findTeamMember(ModelAndView mv, @PathVariable("teamCode") String teamCode, HttpServletResponse response) throws IOException {
		
		List<MemberDTO> memberList = projectService.findTeamMember(teamCode);
		
		System.out.println("teamCode : " + teamCode);
		System.out.println();
		for(int i = 0; i < memberList.size(); i++) {
			System.out.println("     memberList[i] : " + memberList.get(i));
		}
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("memberList", mapper.writeValueAsString(memberList));
		mv.setViewName("jsonView");
		return mv;
	}
	
	@GetMapping("main/{projectNo}")
	public ModelAndView sendProjectDetail(@PathVariable int projectNo, ModelAndView mv) {
		ProjectAuthorityDTO projectAutority = new ProjectAuthorityDTO()
												.builder().pmNo(projectService.findPmNumber(projectNo))
												.projectNo(projectNo).build();
		System.out.println("projectAutority : " + projectAutority);
		
		mv.addObject("projectAutority", projectAutority);
		mv.addObject("projectNo", projectNo);
		mv.setViewName("/project/projectMain");
		
		return mv;
	}
									
	
	
	
	
	
	
	
}




























