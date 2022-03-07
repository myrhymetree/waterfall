package com.greedy.waterfall.menu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.menu.model.dto.MainInfoDTO;
import com.greedy.waterfall.menu.model.service.MenuService;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

/**
 * <pre>
 * Class : MenuController
 * Comment : 로그인 후 계정의 종류별로 출력되는 메인화면에 필요한 정보들을 조회 한다. 
 * 
 * History
 * 2022. 3. 3.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Controller
@RequestMapping("/menu/*")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("/main")
	public ModelAndView sendToMainpage(ModelAndView mv, HttpServletRequest request) {
		String currentPage = request.getParameter("currentPage");
		String subcurrentPage = request.getParameter("subcurrentPage");
		MemberDTO loginMember = (MemberDTO) request.getSession().getAttribute("loginMember");
		MainInfoDTO mainInfo = new MainInfoDTO().builder()
								.currentPage(currentPage)
								.subcurrentPage(subcurrentPage)
								.loginMember(loginMember).build();
		
		Map<String, Object> findProjectResult = menuService.findMainProjectList(mainInfo);
		
		List<ProjectDTO> projectList = (List<ProjectDTO>) findProjectResult.get("projectList");
		List<ProjectDTO> joinProjectList = (List<ProjectDTO>) findProjectResult.get("joinProjectList");
		SelectCriteria selectCriteria = (SelectCriteria) findProjectResult.get("selectCriteria");
		SelectCriteria subselectCriteria = (SelectCriteria) findProjectResult.get("subselectCriteria");
		
		
		mv.addObject("projectList", projectList);
		mv.addObject("joinProjectList", joinProjectList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("subselectCriteria", subselectCriteria);
		mv.addObject("intent", "/menu/main");
		mv.setViewName("/main/mainPage");
		
		return mv;
	}

	/**
	 * findAdminMainProjectList : 메인화면 관리자 ajax부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 * @throws IOException 
	 */
	@GetMapping("/main/admin/project/{projectNo}")
	public ModelAndView findAdminMainProjectList(ModelAndView mv, HttpServletResponse response, @PathVariable("projectNo") int projectNo) throws IOException {
		Map<String, Object> adminPageInfo = menuService.findAdminPageInfo(projectNo);
		
		ProjectDTO project = (ProjectDTO) adminPageInfo.get("projectInfo");
		
		response.setContentType("application/json; charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(dateFormat);
		
		mv.addObject("project", mapper.writeValueAsString(project));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	@GetMapping("main/join/project/{projectNo}")
	public ModelAndView findJoinProjectInfo(ModelAndView mv, HttpServletRequest request, HttpServletResponse response
															, @PathVariable("projectNo") int projectNo) throws IOException {
		
		Map<String, Integer> searchMap = new HashMap<String, Integer>();
		Integer memberNo = ((MemberDTO) request.getSession().getAttribute("loginMember")).getNo();
		
		searchMap.put("memberNo", memberNo);
		searchMap.put("projectNo", projectNo);
		
		ProjectDTO joinProjectInfo = menuService.findJoinProjectInfo(searchMap);
		
		
		response.setContentType("application/json; charset=UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(dateFormat);
		
		mv.addObject("joinProjectInfo", mapper.writeValueAsString(joinProjectInfo));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	
	
	
	
}





























































