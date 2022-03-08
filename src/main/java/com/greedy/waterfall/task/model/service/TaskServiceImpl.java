package com.greedy.waterfall.task.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.greedy.waterfall.task.model.dao.TaskMapper;
import com.greedy.waterfall.task.model.dto.AllTaskCodeDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.ParentTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskHistoryDTO;
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;

/**
 * <pre>
 * Class : TaskServiceImpl
 * Comment : task 로직 구현 부분
 * 
 * History
 * 2022. 2. 26.  (김서영)
 * @version 1
 * @author 김서영
 */
@Service
public class TaskServiceImpl implements TaskService{
	
	private final TaskMapper mapper;
	
	public TaskServiceImpl(TaskMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * findTaskTimeline : GanttChart및 초기 페이지 조회시 필요한 정보들 조회
	 * @param TaskDTO taskDTO : projectNo이 담긴 TaskDTO
	 * @return parentTaskList 상위업무 내 하위업무를 포함한 data
	 * 
	 * @author 김서영
	 */
	@Override
	public List<TaskDTO> findTaskTimeline(TaskDTO taskDTO) {
		
		/* 상위업무 List */
		List<TaskDTO> parentTaskList = mapper.selectParentTaskList(taskDTO);
		List<ChildTaskDTO> defaultChildTaskList =  new ArrayList<ChildTaskDTO>();
		for(int i = 0; i < parentTaskList.size(); i++) {
			parentTaskList.get(i).setChildList(defaultChildTaskList);
		}
		System.out.println("parentTaskList 확인 : " + parentTaskList);
		
		List<ChildTaskDTO> childTaskList = new ArrayList<ChildTaskDTO>();
		/* parentTaskList안의 parentDTO내의 childTaskList 불러오기 */
		for(int i = 0; i < parentTaskList.size(); i++) {
			childTaskList = parentTaskList.get(i).getChildList();
			
			//parentTaskList DTO의 parentTaskNo만 저장하는 과정, i=0일 때 parentTaskNo = 5...
			int parentTaskNo = parentTaskList.get(i).getTaskNo();
			
			/* parentTaskList안의 childTaskList안의 childDTO안의 parentTaskNo을 set해주는 과정 */
			for(int j = 0; j < childTaskList.size(); j++) {
				childTaskList.get(j).setParentTaskNo(parentTaskNo);
				childTaskList.get(j).setProjectNo(taskDTO.getProjectNo());
			}
		}
		/*위에서 조회한 상위업무의 번호를 통해 하위업무 list 조회*/
		for(int i = 0; i < parentTaskList.size(); i++) {
			childTaskList = mapper.selectChildTaskList(parentTaskList.get(i).getTaskNo());
			parentTaskList.get(i).setChildList(childTaskList);
			
		}
			
		System.out.println("parentTaskList : " +parentTaskList);
		
		
		return parentTaskList;
	}

	/**
	 * findAllTaskCode : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@Override
	public AllTaskCodeDTO findAllTaskCode() {
		
		/*현재 존재하는 모든 업무 카테고리 코드 저장*/
		
		List<ParentTaskCategoryDTO> parentTaskCategory = mapper.selectParentCategory();
		List<ChildTaskCategoryDTO> childTaskCategory = mapper.selectChildCategory();
		
		AllTaskCodeDTO allTaskCodeDTO = new AllTaskCodeDTO();
		allTaskCodeDTO.setParentCategory(parentTaskCategory);
		allTaskCodeDTO.setChildCategory(childTaskCategory);
		
		return allTaskCodeDTO;
		
		
		 
	}

	/**
	 * findProjectMember : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@Override
	public List<ProjectMemberDTO> findProjectMember(int projectNo) {
		
		List<ProjectMemberDTO> memberList = mapper.selectAllProjectMember(projectNo);
		
		return memberList;
	}

	/**
	 * registTask : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@Override
	public void registTask(TaskRegistDTO taskRegistDTO) {
		System.out.println(taskRegistDTO.getParentTaskCode());
			
			if(taskRegistDTO.getParentTaskCode().equals("NULL")) {
				
				int result = mapper.registTask(taskRegistDTO);
				if(result > 0) {
					mapper.registHistory(taskRegistDTO);
				}
				
			/* 상위업무 등록 시 */	
			} else {
				int parentTaskNo = mapper.selectParentTaskNo(taskRegistDTO);
				taskRegistDTO.setParentTaskNo(parentTaskNo);
				int result = mapper.registTask(taskRegistDTO);
				if(result > 0) {
					mapper.registHistory(taskRegistDTO);
				}
				
			}
			
	}

	/**
	 * findAllCategoryCode : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@Override
	public List<TaskCategoryDTO> findAllCategoryCode() {
		
		List<TaskCategoryDTO> taskCategoryList = mapper.selectAllCategoryCode();
		
		return taskCategoryList;
	}

	/**
	 * findChildTaskList : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@Override
	public List<ChildTaskDTO> findChildTaskList(int projectNo) {
		
		List<ChildTaskDTO> childTaskList = mapper.selectAllchildTask(projectNo);
		
		return childTaskList;
	}

	@Override
	public ChildTaskDTO findTaskDetail(int taskNo) {
		
		ChildTaskDTO childTask = new ChildTaskDTO();
		TaskDTO parentTask = childTask.getParentTask();
		
		childTask = mapper.selectChildTask(taskNo);
		int parentNo = childTask.getParentTaskNo();
		parentTask = mapper.selectParentTask(parentNo);
		
		childTask.setParentTask(parentTask);
		
		return childTask;
	}

	@Override
	public void updateTask(TaskRegistDTO taskUpdateDTO,  TaskHistoryDTO history) {
		
		/* 수정할 내용 업데이트 */
		int result = mapper.updateTask(taskUpdateDTO);
		
		/* 업데이트 성공시 히스토리 등록 */
		int historyResult = 0;
		
		if(result > 0) {
			historyResult = mapper.insertUpdateHistory(taskUpdateDTO);
		}
		
		/* Entire History 등록 */
		if(historyResult > 0) {
			String memberName = mapper.selectMemberName(history);
			String taskName = mapper.selectTaskName(history);
			
			taskUpdateDTO.setMemberName(memberName);
			taskUpdateDTO.setTaskName(taskName);
			
			
			mapper.insertEntireUpdateHistory(taskUpdateDTO);
		}
		
		
		
		
	}

	@Override
	public void removeTask(TaskDTO task, TaskHistoryDTO history) {
		
		/* 지우기 전에 히스토리에 등록할 정보 조회*/
		TaskHistoryDTO historyInfo = mapper.selectHistoryInfo(history);
		
		history.setMemberName(historyInfo.getMemberName());
		history.setTaskName(historyInfo.getTaskName());
		
		String taskCodeResult = mapper.selectRefTaskCode(task);
		
		System.out.println("taskCodeResult : " + taskCodeResult);
		
		int deleteResult = 0;
		/* 매개변수로 넘어온 taskNo가 상위업무 일 때 */
		if(taskCodeResult == null) {
			deleteResult = mapper.deleteTask(task);
		/* 하위업무일 때*/	
		} else {
			deleteResult = mapper.deleteChildTask(task);
		}
		
		int historyResult = 0;
		if(deleteResult > 0) {
			historyResult = mapper.insertDeleteHistory(task);
		}
		
		int entireHistoryResult = 0;
		if(historyResult > 0) {
			
			System.out.println("deleteHistoryInfo : " + history);
			
			entireHistoryResult = mapper.insertEntireDeleteHistory(history);
		}
		
		
	}
	
	
}
