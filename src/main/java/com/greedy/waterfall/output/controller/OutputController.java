package com.greedy.waterfall.output.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.service.OutputService;
import com.greedy.waterfall.task.model.dto.TaskDTO;

@Controller
@RequestMapping("/output")
public class OutputController {
	
	private final OutputService outputService;
	
	@Autowired
	public OutputController(OutputService outputService) {
		this.outputService = outputService;
	}
	
	@GetMapping("/list")
	public ModelAndView outputFindList( HttpServletRequest request, ModelAndView mv) {
		
		/* loginMember가 ROLE_ADMIN 일 때 */
		/* loginMember가 ROLE_MEMBER 일 때 */
		
		int projectNo = Integer.parseInt(request.getParameter("no"));//
		int memberNo = 2;		// 임시 memberNo
		
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setProjectNo(projectNo);
		taskDTO.setManagerNo(memberNo);
		
		List<TaskDTO> parentTaskList = new ArrayList<>();
		
		parentTaskList = outputService.findOutputTask(taskDTO);
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.setViewName("output/outputDetail");
		
		return mv; 
		
	}

}
