package com.greedy.waterfall.output.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.output.model.service.OutputService;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

@Controller
@RequestMapping("/output")
@SessionAttributes("projectAutority")
public class OutputController {
	
	private final OutputService outputService;
	
	@Autowired
	public OutputController(OutputService outputService) {
		this.outputService = outputService;
	}
	
	@GetMapping("/list")
	public ModelAndView outputFindList( HttpServletRequest request, ModelAndView mv, HttpSession session) {
		
		/*session에서 프로젝트 번호 얻음*/
		/*
		 * ProjectAuthorityDTO pa =
		 * (ProjectAuthorityDTO)session.getAttribute("projectAutority"); int projectNo =
		 * pa.getProjectNo();
		 */
		
		TaskDTO taskDTO = new TaskDTO();
		//taskDTO.setProjectNo(projectNo);
		
		/* 현재 진행중인 프로젝트의 번호를 세션에서 가져온다 */
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		
		taskDTO.setProjectNo(projectNo);
		List<TaskDTO> parentTaskList = new ArrayList<>();
		
		parentTaskList = outputService.findOutputTask(taskDTO);
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.setViewName("output/outputDetail");
		
		return mv; 
		
	}
	
	/**
	 * findAdminOutputList : 관리자 프로젝트 별 산출물 개수 조회
	 * @param 
	 * @return 산출물 정보를 담은 전체 프로젝트 리스트
	 * 
	 * @author 김서영
	 */
	@GetMapping("/admin/list")
	public ModelAndView findAdminOutputList(ModelAndView mv, HttpSession session) {
		
		/* 프로젝트 당 존재하는 산출물 개수를 return 하자 */
		
		List<OutputProjectDTO> projectList = outputService.findOutputList();
		
		mv.addObject("projectList", projectList);
		mv.setViewName("admin/outputList");
		
		return mv;
		
	}
	
	
	/**
	 * findOutputDetail : 하위 업무 선택시 해당하는 업무의 산출물 상세 정보
	 * @param 클릭한 task의 번호
	 * @return 클릭한 업무에 해당하는 산출물 정보
	 * 
	 * @author 김서영
	 */
	@GetMapping(value = "/detail", produces = "application/json; charset= UTF-8")
	@ResponseBody
	public String findOutputDetail(HttpServletRequest request, HttpSession session) {
		
		 
		
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
	
	/**
	 * removeOutput : 클릭한 산출물 삭제
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@GetMapping("/delete")
	public String removeOutput(HttpServletRequest request, RedirectAttributes rttr) {
		
		int no = Integer.parseInt(request.getParameter("outputNo"));
		
		outputService.removeOutput(no);
		
		rttr.addFlashAttribute("message", "산출물 삭제에 성공하셨습니다.");
		
		return "redirect:/output/detail";
	}

}
