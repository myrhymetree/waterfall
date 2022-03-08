package com.greedy.waterfall.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

/**
 * <pre>
 * Class : TaskController
 * Comment : 업무(task) 타임라인 조회, 상세조회, 등록, 삭제 컨트롤러
 * 
 * History
 * 2022. 3. 2.  (김서영)
 * @version 1
 * @author 김서영
 */
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
	 * taskFindTimeline : Gantt Chart 및 상위업무 내 하위업무 확인
	 * @param first : ModelAndView mv return에 필요한 객체
	 * @param second : HttpSession session projectNo를 가져올 때 필요한 session정보
	 * @return : mv : parentTaskList(view페이지 왼쪽에 보이는 토글에 사용되는 data, 업무내 속하는 하위업무 List를 담은 taskList)
	 *                childTaskList,(Gantt Chart 구현시 필요한 하위업무의 정보)
	 *                taskCategoryList,(업무 생성시 필요한 업무이름 및 코드 정보)
	 *                allTaskCode,( 종속관계 선택 시 상위업무 코드 정보를 위한 data) 
	 *                projectMemberList (업무 생성 시 필요한 프로젝트 참여하고 있는 인원 리스트)
	 * 
	 * @author 김서영
	 * @throws JsonProcessingException 
	 */
	@GetMapping("/timeline")
	public ModelAndView taskFindTimeline(ModelAndView mv, HttpSession session )  {
		
		/* 현재 진행중인 프로젝트의 번호를 세션에서 가져온다 */
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();
		
		System.out.println("projectNo : " +projectNo);
		
		/* taskList를 찾을 때 필요한 정보   setting
		 * projectNo만 보내는 것이 아닌 TaskDTO 보내는 이유 : Service에서 TaskDTO에 하위업무List를 넣어줘야하기 때문 */
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setProjectNo(projectNo);
		
		/* 상위업무에 해당하는 하위업무들 조회해오기 */
		List<TaskDTO> parentTaskList = taskService.findTaskTimeline(taskDTO);
		/* gantt-chart 생성 시 필요한 하위업무 List */
		List<ChildTaskDTO> childTaskList = taskService.findChildTaskList(projectNo);
		System.out.println("childTaskList : " + childTaskList);
		
		
		/* 상위업무,하위업무 categoryCode */
		List<TaskCategoryDTO> taskCategoryList = taskService.findAllCategoryCode();
		AllTaskCodeDTO allTaskCode = taskService.findAllTaskCode();
		
		
		/* 프로젝트에 참여중인 인원 조회 */
		List<ProjectMemberDTO> projectMemberList= taskService.findProjectMember(projectNo);
		System.out.println("taskDetail projectMember :" + projectMemberList);
		
		/* PM여부 확인 */
		int pmNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getPmNo();
		System.out.println("pmNo : " + pmNo);
		
		
		mv.addObject("parentTaskList", parentTaskList);
		mv.addObject("childTaskList", childTaskList);
		mv.addObject("taskCategoryList", taskCategoryList);
		mv.addObject("allTaskCode", allTaskCode);
		mv.addObject("projectMemberList", projectMemberList );
		mv.addObject("pmNo", pmNo);
		mv.setViewName("task/taskTimeLine");
		
		return mv;
	}
	
	/**
	 * taskRegist : 업무생성 및 히스토리 등록
	 * @param first : @ModelAttribute TaskRegistDTO 업무 생성시 받아오는 data
	 * @param second : HttpSession session 현재 진행중인 projeNo를 가져오기 위한 session 
	 * @return 업무 생성 후 조회 페이지로 redirect
	 * 
	 * @author 김서영
	 */
	@PostMapping("/regist")
	public String taskRegist(@ModelAttribute TaskRegistDTO taskRegistDTO, HttpSession session) {
		
		/* session 내 projectNo 저장 */
		int projectNo = ((ProjectAuthorityDTO) session.getAttribute("projectAutority")).getProjectNo();

		System.out.println("projectNo : " + projectNo);
		
		taskRegistDTO.setProjectNo(projectNo);
		System.out.println("TaskRegistDTO : " + taskRegistDTO);
		
		/* projectNo가 담긴 taskRegistDTO 매개변수로 service method 호출 */
		taskService.registTask(taskRegistDTO);
		
		
		
		return "redirect:/task/timeline";
		
	}
	
	@GetMapping("/detail")
	public ModelAndView findTaskDetail(ModelAndView mv, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		response.setContentType("aplication/json; charset=UTF-8");
		
		/* jsp에서 받은 클릭한 하위업무 번호*/
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		
		System.out.println("taskTimeline Detail taskNo : " + taskNo);
		
		ChildTaskDTO taskDetail = taskService.findTaskDetail(taskNo);
		
		System.out.println("taskDetail : " + taskDetail);
		
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("taskDetail", mapper.writeValueAsString(taskDetail));
		mv.setViewName("jsonView");
		return mv;
	}
	
	
}
