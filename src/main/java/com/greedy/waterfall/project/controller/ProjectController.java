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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.board.model.service.EduService;
import com.greedy.waterfall.board.model.service.GuideService;
import com.greedy.waterfall.board.model.service.MeetingService;
import com.greedy.waterfall.board.model.service.NoticeService;
import com.greedy.waterfall.board.model.service.TodoService;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectMainBoardDTO;
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
@SessionAttributes("projectAutority")
public class ProjectController {
	private final ProjectService projectService;
	private final MeetingService meetingService;
	private final TodoService todoService;
	private final EduService eduService;
	private final GuideService guideService;
	private final NoticeService noticeService;
	
	@Autowired
	public ProjectController(ProjectService projectService
			, MeetingService meetingService
			, TodoService todoService
			, EduService eduService
			, GuideService guideService
			, NoticeService noticeService) {
		this.projectService = projectService;
		this.meetingService = meetingService;
		this.todoService = todoService;
		this.eduService = eduService;
		this.guideService = guideService;
		this.noticeService = noticeService;
	}

	@GetMapping("/regist")
	public ModelAndView sendRegistPage(ModelAndView mv) {
		
		Map<String, Object> projectForm = projectService.findRegistForm();

		List<ProjectStatusDTO> statusList = (List<ProjectStatusDTO>) projectForm.get("statusList");
		List<DeptDTO> deptList = (List<DeptDTO>) projectForm.get("deptList");
		
		
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
		String projectStatusCode = parameter.get("projectStatusCode");
		
		RegistProjectDTO newProject = new RegistProjectDTO().builder()
										.projectName(projectName)
										.startDate(java.sql.Date.valueOf(startDate))
										.deadLine(java.sql.Date.valueOf(deadLine))
										.pmNumber(Integer.parseInt(pmNumber))
										.projectStatusCode(projectStatusCode)
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
		/* 회원의 정보로 프로젝트를 조회 */
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		MyProjectDTO project = projectService.findMyProject(member);
		List<ProjectDTO> manageProject = project.getManageProject();
		List<ProjectDTO> removedProject = project.getRemovedProject();
				
		mv.addObject("manageProject", manageProject);
		mv.addObject("removedProject", removedProject);
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
	
	/**
	 * findProjectDetail : 프로젝트 관리페이지에서 프로젝트의 상세정보를 조회한다.
	 * @param mv : 요청주소를 담을 변수를 전달받는다. 
	 * @return mv : 요청주소를 담아 반환한다.
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/modify/{projectNo}")
	public ModelAndView findProjectDetail(ModelAndView mv, @PathVariable("projectNo") int projectNo) {
		
		RegistProjectDTO projectInfo = projectService.findOneProjectInfo(projectNo);
		
		Map<String, Object> projectForm = projectService.findRegistForm();

		List<ProjectStatusDTO> statusList = (List<ProjectStatusDTO>) projectForm.get("statusList");
		List<DeptDTO> deptList = (List<DeptDTO>) projectForm.get("deptList");

		mv.addObject("statusList", statusList);
		mv.addObject("deptList", deptList);
		mv.addObject("projectInfo", projectInfo);
		mv.setViewName("/project/projectModify");
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modifyProject(ModelAndView mv, @RequestParam Map<String, String> parameter) {
		
		String projectName = parameter.get("projectName");
		String projectNo = parameter.get("projectNo");
		String startDate = parameter.get("startDate");
		String deadLine = parameter.get("deadLine");
		String pm = parameter.get("pm");
		String dept = parameter.get("dept");
		String team = parameter.get("team");
		String pmNumber = parameter.get("pmNumber");
		String projectStatusCode = parameter.get("projectStatus");
		String progression = parameter.get("progression");
		String adminNo = parameter.get("adminNo");
		RegistProjectDTO project = new RegistProjectDTO().builder()
										.projectName(projectName)
										.projectNo(Integer.parseInt(projectNo))
										.startDate(java.sql.Date.valueOf(startDate))
										.deadLine(java.sql.Date.valueOf(deadLine))
										.pmNumber(Integer.parseInt(pmNumber))
										.projectStatusCode(projectStatusCode)
										.dept(dept)
										.team(team)
										.progression(Integer.parseInt(progression))
										.adminNo(Integer.parseInt(adminNo))
										.build();
		
		if(projectService.modifyProject(project)) {
			System.out.println("프로젝트 수정 성공!");
		} else {
			System.out.println("프로젝트 수정 실패!");
		}

		mv.setViewName("redirect:managelist");
		
		return mv;
	}
	
	
	@GetMapping("main/{projectNo}")
	public ModelAndView sendProjectDetail(@PathVariable("projectNo") int projectNo, ModelAndView mv) {

		List<MeetingDTO> ml= meetingService.findMainList(projectNo);
		ProjectAuthorityDTO projectAutority = new ProjectAuthorityDTO()
												.builder().pmNo(projectService.findPmNumber(projectNo))
												.projectNo(projectNo).build();
		System.out.println("projectAutority : " + projectAutority);
		ProjectMainBoardDTO projectBoard = new ProjectMainBoardDTO().builder()
											.meetingBoard(ml)
											.build();
		System.out.println("1");
		for(int i = 0; i < ml.size(); i++) {
			System.out.println(ml.get(i));
			
		}
		System.out.println("2");
		mv.addObject("projectBoard", projectBoard);
		mv.addObject("projectAutority", projectAutority);
		mv.addObject("projectNo", projectNo);
		mv.setViewName("/project/projectMain");
		
		return mv;
	}
									
	@GetMapping("/remove/{projectNo}")
	public ModelAndView removeProject(ModelAndView mv, @PathVariable int projectNo) {
		
		String message = "삭제에 실패했습니다.";
		
		if(projectService.removeProject(projectNo)) {
			message = "삭제에 성공했습니다.";
			
		}
		mv.setViewName("redirect:/project/managelist");

		return mv;
	}
	
	@GetMapping("/restore/{projectNo}")
	public ModelAndView restoreProject(ModelAndView mv, @PathVariable int projectNo) {
		
		String message = "프로젝트 복구 실패.";
		
		if(projectService.restoreProject(projectNo)) {
			message = "프로젝트 복구 성공.";
			
		}
		mv.setViewName("redirect:/project/managelist");

		return mv;
	}
	
	@GetMapping("/delete/{projectNo}")
	public ModelAndView deleteProject(ModelAndView mv, @PathVariable int projectNo) {
		
		String message = "프로젝트 삭제 실패.";
		
		if(projectService.deleteProject(projectNo)) {
			message = "프로젝트 삭제 성공.";
			
		}
		mv.setViewName("redirect:/project/managelist");
		
		return mv;
	}
	
	
	
	
	
	
}




























