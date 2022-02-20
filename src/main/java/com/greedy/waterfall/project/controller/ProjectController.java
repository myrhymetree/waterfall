package com.greedy.waterfall.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping("/list")
	public ModelAndView findProjectList(ModelAndView mv) {
		System.out.println("project/list servlet");
		
		mv.setViewName("/project/projectList");
		return mv;
	}
	
	
	
	
	
	
	
}
