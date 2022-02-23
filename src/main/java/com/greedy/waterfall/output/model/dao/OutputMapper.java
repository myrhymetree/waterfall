package com.greedy.waterfall.output.model.dao;

import java.util.List;

import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

public interface OutputMapper {

	List<TaskDTO> selectParentTaskList(TaskDTO taskDTO);

	List<ChildTaskDTO> selectChildTaskList(int i);

	OutputDTO selectOutputDetail(int taskNo);

	ChildTaskDTO selectChildTask(int taskNo);

	TaskCategoryDTO selectParentTaskName(int parentTaskNo);

	TaskDTO selectParentTask(int parentTaskNo);

	void deleteOutput(int no);
	
	
}
