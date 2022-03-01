package com.greedy.waterfall.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.task.model.dto.AllTaskCodeDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;
import com.greedy.waterfall.task.model.service.TaskService;

@Controller
@RequestMapping("/task")
@SessionAttributes("projectAutority")
public class TaskController {
	
	private final TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	
	/**
	 * taskFindTimeline : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 * @throws JsonProcessingException 
	 */
	@GetMapping("/timeline")
	public ModelAndView taskFindTimeline(HttpServletRequest request, ModelAndView mv, HttpSession session ) throws JsonProcessingException {
		
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		
		System.out.println("projectNo : " +projectNo);
		
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setProjectNo(projectNo);
		List<TaskDTO> parentTaskList = new ArrayList<TaskDTO>();
		List<ChildTaskDTO> childTaskList = new ArrayList<ChildTaskDTO>();
		
		/* 상위업무에 해당하는 하위업무들 조회해오기 */
		parentTaskList = taskService.findTaskTimeline(taskDTO);
		
		childTaskList = taskService.findChildTaskList(projectNo);
		System.out.println("childTaskList : " + childTaskList);
		
		
		/* 상위업무,하위업무 categoryCode */
		List<TaskCategoryDTO> taskCategoryList = taskService.findAllCategoryCode();
		AllTaskCodeDTO allTaskCode = taskService.findAllTaskCode();
		
		
		/* 프로젝트에 참여중인 인원 조회 */
		List<ProjectMemberDTO> projectMemberList= taskService.findProjectMember(projectNo);
		System.out.println("taskDetail projectMember :" + projectMemberList);
		
		/*
		 * Gson gson = new GsonBuilder() .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
		 * .setPrettyPrinting() .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		 * .serializeNulls() .disableHtmlEscaping() .create();
		 */
		
		
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.addObject("childTaskList", childTaskList);
		mv.addObject("taskCategoryList", taskCategoryList);
		mv.addObject("allTaskCode", allTaskCode);
		mv.addObject("projectMemberList", projectMemberList );
		mv.setViewName("task/taskDetail");
		
//		System.out.println(gson.toJson(parentTaskList));
		
		return mv;
	}
	
	@PostMapping("/regist")
	public String taskRegist(@ModelAttribute TaskRegistDTO taskRegistDTO, HttpServletRequest request, HttpSession session) {
		
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();

		System.out.println("projectNo : " + projectNo);
		
		taskRegistDTO.setProjectNo(projectNo);
		
		System.out.println("TaskRegistDTO : " + taskRegistDTO);
		
		taskService.registTask(taskRegistDTO);
		
		
		
		return "redirect:/task/timeline";
		
		
		
		
	}
	
	
}
