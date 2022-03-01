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
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;

/**
 * <pre>
 * Class : TaskServiceImpl
 * Comment : 클래스 설명 작성부분
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

	@Override
	public List<TaskDTO> findTaskTimeline(TaskDTO taskDTO) {
		
		/* 상위업무 List */
		List<TaskDTO> parentTaskList = mapper.selectParentTaskList(taskDTO);
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
		for(int i = 0; i < parentTaskList.size(); i++) {
			childTaskList = mapper.selectChildTaskList(parentTaskList.get(i).getTaskNo());
			parentTaskList.get(i).setChildList(childTaskList);
			
		}
			
		System.out.println("parentTaskList : " +parentTaskList);
		
		
		return parentTaskList;
	}

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

	@Override
	public List<ProjectMemberDTO> findProjectMember(int projectNo) {
		
		List<ProjectMemberDTO> memberList = mapper.selectAllProjectMember(projectNo);
		
		return memberList;
	}

	@Override
	public void registTask(TaskRegistDTO taskRegistDTO) {
		
		int parentTaskNo = mapper.selectParentTaskNo(taskRegistDTO);
		
		taskRegistDTO.setParentTaskNo(parentTaskNo);
		
		int result = mapper.registTask(taskRegistDTO);
		
		if(result > 0) {
			mapper.registHistory(taskRegistDTO);
		}
		
		
	}

	@Override
	public List<TaskCategoryDTO> findAllCategoryCode() {
		
		List<TaskCategoryDTO> taskCategoryList = mapper.selectAllCategoryCode();
		
		return taskCategoryList;
	}
	
	
}
