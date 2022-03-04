package com.greedy.waterfall.output.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.output.model.dto.OutputAttachmentDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
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

	List<OutputDTO> selectAdminOutputList(int projectNo);

	List<OutputProjectDTO> selectAllProjectList();

	int selectAlloutputCount(int projectNo);

	int insertOutput(OutputDTO output);

	int insertOutputAttachment(OutputAttachmentDTO attachmentDTO);

	void insertOutputHistory(OutputDTO output);

	OutputAttachmentDTO selectOutputFile(int outputNo);
	
	
}
