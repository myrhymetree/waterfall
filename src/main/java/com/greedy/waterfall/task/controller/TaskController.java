package com.greedy.waterfall.task.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
		
		int projectNo = 3;
		
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setProjectNo(projectNo);
		List<TaskDTO> parentTaskList = new ArrayList<TaskDTO>();
		
		parentTaskList = taskService.findTaskList(taskDTO);
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.setViewName("task/taskDetail");
		
		return mv;
	}

}
