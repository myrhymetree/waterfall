package com.greedy.waterfall.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.WebSession;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.menu.model.dto.ProjectInfoDTO;
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
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
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
	public ModelAndView registProject(ModelAndView mv, @ModelAttribute RegistProjectDTO newProject) {
		
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
	public ModelAndView findManageProjectList(ModelAndView mv, HttpServletRequest request) {
		/* 회원의 정보로 프로젝트를 조회 */
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");
		Map<String, String> searchMap = new HashMap<>();

		String currentPage = request.getParameter("currentPage");
		String searchCondition = request.getParameter("searchCondition");	
		String searchValue = request.getParameter("searchValue");			
		String subcurrentPage = request.getParameter("subcurrentPage");
		String subsearchCondition = request.getParameter("subsearchCondition");	
		String subsearchValue = request.getParameter("subsearchValue");			
		
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("currentPage", currentPage);
		searchMap.put("subsearchCondition", subsearchCondition);
		searchMap.put("subsearchValue", subsearchValue);
		searchMap.put("subcurrentPage", subcurrentPage);

		
		MyProjectDTO project = projectService.findMyProject(searchMap, member);
		List<ProjectDTO> manageProject = project.getManageProject();
		List<ProjectDTO> removedProject = project.getRemovedProject();

		
		mv.addObject("subselectCriteria", project.getSubselectCriteria());
		mv.addObject("selectCriteria", project.getSelectCriteria());
		mv.addObject("manageProject", manageProject);
		mv.addObject("removedProject", removedProject);
		mv.addObject("intent", "/project/managelist");
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
	public ModelAndView findProjectList(HttpServletRequest request, ModelAndView mv) {
		/* 회원번호로 조회 */
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");
		
		
		System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);System.out.println("loginMember : " +  member);
		
		
		
		Map<String, String> searchMap = new HashMap<>();

		String currentPage = request.getParameter("currentPage");
		String searchCondition = request.getParameter("searchCondition");	
		String searchValue = request.getParameter("searchValue");			
		String subcurrentPage = request.getParameter("subcurrentPage");
		String subsearchCondition = request.getParameter("subsearchCondition");	
		String subsearchValue = request.getParameter("subsearchValue");			
		
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		searchMap.put("currentPage", currentPage);
		searchMap.put("subsearchCondition", subsearchCondition);
		searchMap.put("subsearchValue", subsearchValue);
		searchMap.put("subcurrentPage", subcurrentPage);

		MyProjectDTO project = projectService.findMyProject(searchMap, member);
		List<ProjectDTO> manageProject = project.getManageProject();
		mv.addObject("manageProject", manageProject);
		mv.addObject("selectCriteria", project.getSelectCriteria());
		mv.addObject("subselectCriteria", project.getSubselectCriteria());
		mv.addObject("projectList", project);
		mv.addObject("intent", "/project/list");
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
	
	@GetMapping("/board/{boardNo}")
	public ModelAndView findProjectMainBoard(ModelAndView mv, @PathVariable("boardNo") int boardNo, HttpServletResponse response) throws IOException {
		
		BoardDTO boardInfo = projectService.findBoardInfo(boardNo);

		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		mv.addObject("board", mapper.writeValueAsString(boardInfo));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	
	@GetMapping("/regist/member/{teamCode}")
	public ModelAndView findTeamMember(ModelAndView mv, @PathVariable("teamCode") String teamCode, HttpServletResponse response) throws IOException {
		
		List<MemberDTO> memberList = projectService.findTeamMember(teamCode);
		
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
	public ModelAndView modifyProject(ModelAndView mv, @ModelAttribute RegistProjectDTO project) {
		
		
		System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);System.out.println("project : " + project);
		
		
		
		if(projectService.modifyProject(project)) {
			System.out.println("프로젝트 수정 성공!");
		} else {
			System.out.println("프로젝트 수정 실패!");
		}

		mv.setViewName("redirect:managelist");
		
		return mv;
	}
	
	/**
	 * sendProjectDetail : 프로젝트 번호와 해당 프로젝트의 PM번호를 세션에 저장하고, 프로젝트의 메인페이지에 필요한 정보를 조회한다.
	 * @param 프로젝트 번호와, 정보와 요청주소를 저장할 ModelAndView변수를 전달받는다.
	 * @return 프로젝트 메인페이지에 필요한 정보와, 요청주소를 담은 ModelAndView 변수를 반환한다.
	 * 
	 * @author 홍성원
	 * @throws IOException 
	 */
	@GetMapping("main/{projectNo}")
	public ModelAndView sendProjectDetail(@PathVariable("projectNo") int projectNo, ModelAndView mv) throws IOException {
		Map<String, Object> projectMainInfo = projectService.findProjectMainInfo(projectNo);
		
		ProjectMainBoardDTO projectBoard = (ProjectMainBoardDTO) projectMainInfo.get("projectBoard");
		ProjectAuthorityDTO projectAutority = (ProjectAuthorityDTO) projectMainInfo.get("projectAutority");
		ProjectInfoDTO projectInfo = (ProjectInfoDTO) projectMainInfo.get("projectInfo");
		
		ObjectMapper mapper = new ObjectMapper();
		mv.addObject("projectInfo", mapper.writeValueAsString(projectInfo));
		
//		mv.addObject("projectInfo", projectInfo);
		mv.addObject("projectBoard", projectBoard);
		mv.addObject("projectAutority", projectAutority);
		mv.setViewName("/project/projectMain");
		
		return mv;
	}
									
	@GetMapping("/remove/{projectNo}")
	public ModelAndView removeProject(ModelAndView mv, @PathVariable int projectNo, WebSession session) {
		
		Map<String, Integer> 
		int memberNo = ((MemberDTO) session.getAttribute("loginMember")).getNo();
		
		
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




























