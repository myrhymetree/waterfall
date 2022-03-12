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
import com.greedy.waterfall.task.model.dto.TaskRestoreOutputDTO;

public interface OutputMapper {

	List<TaskDTO> selectParentTaskList(TaskDTO taskDTO);

	List<ChildTaskDTO> selectChildTaskList(int i);

	OutputDTO selectOutputDetail(int taskNo);

	ChildTaskDTO selectChildTask(int taskNo);

	TaskCategoryDTO selectParentTaskName(int parentTaskNo);

	TaskDTO selectParentTask(int parentTaskNo);

	int deleteOutput(OutputDTO output);

	List<OutputDTO> selectAdminOutputList(int projectNo);

	List<OutputProjectDTO> selectAllProjectList();

	int selectAlloutputCount(int projectNo);

	int insertOutput(OutputDTO output);

	int insertOutputAttachment(OutputAttachmentDTO attachmentDTO);

	void insertOutputHistory(OutputDTO output);

	OutputAttachmentDTO selectOutputFile(int outputNo);

	int deleteOutputFile(OutputDTO output);

	void insertDeleteHistory(OutputDTO output);

	int updateOutput(OutputDTO output);

	Integer selectOutputVer(OutputDTO output);

	Integer selectOutputCount(int outputNo);

	int insertUpdateHistory(OutputDTO output);

	int updateFile(OutputAttachmentDTO outputFile);

	String selectOriginName(OutputDTO output);

	String selectMemberName(OutputDTO output);

	void insertEntireHistory(OutputDTO output);

	int selectAllDeletedOutputCount(int projectNo);

	List<TaskRestoreOutputDTO> findDeleteOutputList(TaskRestoreOutputDTO restoreOutput);

	List<Integer> selectDeletedOutput(int taskNo);

	TaskRestoreOutputDTO selectTaskNoOutputNo(int fileNo);

	int updateRestoreFile(int fileNo);

	int updateRestoreOutput(int outptuNo);

	int selectProjectNo(int fileNo);

	int selectProjectNoByOutputNo(int outputNo);
	
	
}
