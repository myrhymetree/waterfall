package com.greedy.waterfall.task.model.dao;

import java.util.List;

import com.greedy.waterfall.task.model.dto.ChildTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.ParentTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskHistoryDTO;
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;

public interface TaskMapper {

	List<TaskDTO> selectParentTaskList(TaskDTO taskDTO);

	List<ChildTaskDTO> selectChildTaskList(int taskNo);

	List<ProjectMemberDTO> selectAllProjectMember(int projectNo);

	int registTask(TaskRegistDTO taskRegistDTO);

	List<ParentTaskCategoryDTO> selectParentCategory();

	List<ChildTaskCategoryDTO> selectChildCategory();

	int selectParentTaskNo(TaskRegistDTO taskRegistDTO);

	void registHistory(TaskRegistDTO taskRegistDTO);

	List<TaskCategoryDTO> selectAllCategoryCode();

	List<ChildTaskDTO> selectAllchildTask(int projectNo);

	ChildTaskDTO selectChildTask(int taskNo);

	TaskDTO selectParentTask(int parentNo);

	int updateTask(TaskRegistDTO taskUpdateDTO);

	int insertUpdateHistory(TaskRegistDTO taskUpdateDTO);

	String selectMemberName(TaskHistoryDTO historyDTO);

	String selectTaskName(TaskHistoryDTO history);

	void insertEntireUpdateHistory(TaskRegistDTO taskUpdateDTO);

	String selectRefTaskCode(TaskDTO task);

	int deleteTask(TaskDTO task);

	int insertDeleteHistory(TaskDTO task);

	TaskHistoryDTO selectHistoryInfo(TaskHistoryDTO history);

	int insertEntireDeleteHistory(TaskHistoryDTO history);

	int deleteChildTask(TaskDTO task);

}
