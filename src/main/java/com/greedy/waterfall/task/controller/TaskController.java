package com.greedy.waterfall.task.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
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
	
	@GetMapping("/list")
	public ModelAndView taskFindList(HttpServletRequest request, ModelAndView mv, HttpSession session ) {
		
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		
		System.out.println("projectNo : " +projectNo);
		
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setProjectNo(projectNo);
		List<TaskDTO> parentTaskList = new ArrayList<TaskDTO>();
		
		parentTaskList = taskService.findTaskList(taskDTO);
		
		System.out.println("parentTaskList : " + parentTaskList);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls().disableHtmlEscaping()
				.create();
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.setViewName("task/taskDetail");
		
		System.out.println(gson.toJson(parentTaskList));
		
		return mv;
	}

}
