package com.greedy.waterfall.output.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.output.model.dao.OutputMapper;
import com.greedy.waterfall.output.model.dto.OutputAttachmentDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

/**
 * <pre>
 * Class : OutputServiceImpl
 * Comment : 산출물 로직 처리 부분
 * 
 * History
 * 2022. 2. 26.  (김서영)
 * @version 1
 * @author 김서영
 */
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
		for(int i = 0; i < parentTaskList.size(); i++) {
			childTaskList = mapper.selectChildTaskList(parentTaskList.get(i).getTaskNo());
			parentTaskList.get(i).setChildList(childTaskList);
			
		}
			
		System.out.println("parentTaskList : " +parentTaskList);
		
		
		return parentTaskList;
	}

	/**
	 * findOutputDetail : 업무 별 세부 산출물 내용 조회
	 * @param taskNo : 클릭한 업무의 번호
	 * @return 클릭한 업무번호의 산출물 정보가 담긴 DTO 반환
	 * 
	 * @author 김서영
	 */
	@Override
	public Object findOutputDetail(int taskNo) {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		OutputDTO outputDetail = new OutputDTO();
		ChildTaskDTO childTask = new ChildTaskDTO();
		OutputAttachmentDTO outputFile =  new OutputAttachmentDTO();
		TaskDTO parentTask = childTask.getParentTask();
		
		/* 화면에서 클릭한 하위업무의 번호를 통해 해당하는 하위업무의 정보를 저장한다.*/
		childTask = mapper.selectChildTask(taskNo);
		
		/* 선택한 하위업무의 상위업무 업무 번호를 저장한다.*/
		int parentTaskNo = childTask.getParentTaskNo();
		System.out.println("childTask : " + childTask);
		
		/* 상위업무 번호를 넘겨 상위업무 정보를 조회해온다*/
		parentTask = mapper.selectParentTask(parentTaskNo);
		System.out.println("parentTask : " + parentTask);
		
		/*하위업무 내 상위업무DTO에 조회해온 상위업무 정보를 저장*/
		childTask.setParentTask(parentTask);
		
		
		/*화면에서 클릭한 하위업무 번호로 번호에 해당하는 산출물 정보 저장*/
		outputDetail = mapper.selectOutputDetail(taskNo);
		
		
		if(outputDetail != null) {
			int outputNo = outputDetail.getOutputNo();
			/* 산출물 파일 정보 */
			outputFile = mapper.selectOutputFile(outputNo);
			
		}
		map.put("outputFile", outputFile);
		map.put("childTask", childTask);
		map.put("parentTask", parentTask);
		map.put("outputDetail", outputDetail);
		
		/*산출물 DTO내 하위업무DTO에 상위업무 정보를 담은 하위업무를 저장*/
//		outputDetail.setChildTask(childTask);
		
		System.out.println("outputFile : " + outputFile);
		System.out.println("childTask : " + childTask);
		System.out.println("parentTask : " + parentTask);
		System.out.println("outputDetail : " + outputDetail);
		
		return map;
	}

	/**
	 * removeOutput : 산출물 삭제
	 * @param 삭제할 업무의 번호
	 * @return x
	 * 
	 * @author 김서영
	 */
	@Override
	public void removeOutput(int no) {
		
		mapper.deleteOutput(no);
		
	}

	/**
	 * findOutputList : Admin이 모든 프로젝트별 산출물 개수 조회를 위한 로직
	 * @param x
	 * @return 전체 프로젝트 내 각각 산출물List를 담은 List
	 * 
	 * @author 김서영
	 */
	@Override
	public List<OutputProjectDTO> findOutputList() {
		
		List<OutputProjectDTO> projectList = new ArrayList<OutputProjectDTO>();
		List<OutputDTO> outputList = new ArrayList<OutputDTO>();
		
		/* 각 각 프로젝트에 해당하는 산출물 개수 */
		projectList = mapper.selectAllProjectList();
		System.out.println("output Admin projectList : " + projectList);
		
		for(int i = 0; i < projectList.size(); i++) {
			
			int projectNo = projectList.get(i).getNo();
			
			int outputCount = mapper.selectAlloutputCount(projectNo);
			
			outputList = mapper.selectAdminOutputList(projectNo);
			
			projectList.get(i).setTotalOutputCount(outputCount);
			projectList.get(i).setOutput(outputList);			
		}
		
		System.out.println(projectList);
		
		return projectList;
	}

	@Override
	public void registOutput(OutputDTO output) {
		
		int result = mapper.insertOutput(output);
		
		System.out.println("output insert 확인 : " + output);
		
		OutputAttachmentDTO attachmentDTO = output.getOutputFile();
		
		attachmentDTO.setTaskNo(output.getTaskNo());
		int outputResult = 0;
		if(result > 0) {
			outputResult = mapper.insertOutputAttachment(attachmentDTO);
			
		}
		
		if(outputResult > 0) {
			mapper.insertOutputHistory(output);
		}
		
		
		
	}

	@Override
	public OutputAttachmentDTO findOutputFile(int outputNo) {
		
		return mapper.selectOutputFile(outputNo);
	}



}
