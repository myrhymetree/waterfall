package com.greedy.waterfall.output.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.output.model.dao.OutputMapper;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

@Service
public class OutputServiceImpl implements OutputService{
	
	private final OutputMapper mapper;
	
	@Autowired
	public OutputServiceImpl(OutputMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * findOutputTask : 해당 프로젝트 내 업무 조회
	 * @param taskDTO : projectNo, memberNo
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 김서영
	 */
	@Override
	public List<TaskDTO> findOutputTask(TaskDTO taskDTO) {
		
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
		for(int i = 0; i < childTaskList.size(); i++) {
			childTaskList = mapper.selectChildTaskList(parentTaskList.get(i).getTaskNo());;
			parentTaskList.get(i).setChildList(childTaskList);
		}
			
		System.out.println("parentTaskList : " +parentTaskList);
		
		
		return parentTaskList;
	}


}
