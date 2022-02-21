package com.greedy.waterfall.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
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
	
	
	/**
	 * findProjectList : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	@GetMapping("/list")
	public ModelAndView findProjectList(ModelAndView mv) {
		/* 유저 회원번호 받는 부분 */
		int no = 777;

		/* 회원번호로 조회 */
		MyProjectDTO project = projectService.findMyProject(no);

		List<ProjectDTO> manageProject = project.getManageProject();
		List<ProjectDTO> joinProject = project.getJoinProject();
				
		for(ProjectDTO p : manageProject) {
			System.out.println(p);
			System.out.println(p);
		}
		for(ProjectDTO p : joinProject) {
			System.out.println(p);
			System.out.println(p);
		}
				
		mv.addObject("projectList", project);
		mv.setViewName("/project/projectList");

		return mv;
	}
	
//	@GetMapping("/manage")
//	public ModelAnd
	
	
	
	
	
	
}
