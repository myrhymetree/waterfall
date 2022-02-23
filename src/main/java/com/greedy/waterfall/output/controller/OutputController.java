package com.greedy.waterfall.output.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.service.OutputService;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
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
		
//		int projectNo = Integer.parseInt(request.getParameter("no"));//
		//임시 프로젝트 번호
		int projectNo = 3;
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
	
	@GetMapping(value = "/detail", produces = "application/json; charset= UTF-8")
	@ResponseBody
	public String findOutputDetail(HttpServletRequest request) {
		
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		
		System.out.println("outputDatail no 넘어오나? : " + taskNo);
		
		OutputDTO outputDetail = outputService.findOutputDetail(taskNo);
		
		System.out.println("outputDetail : " +outputDetail);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls().disableHtmlEscaping()
				.create();
		
		return gson.toJson(outputDetail);
	}
	
	@GetMapping("/delete")
	public String removeOutput(HttpServletRequest request, RedirectAttributes rttr) {
		
		int no = Integer.parseInt(request.getParameter("outputNo"));
		
		outputService.removeOutput(no);
		
		rttr.addFlashAttribute("message", "산출물 삭제에 성공하셨습니다.");
		
		return "redirect:/output/detail";
	}

}
