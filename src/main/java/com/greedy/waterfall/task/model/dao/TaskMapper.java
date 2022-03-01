package com.greedy.waterfall.task.model.dao;

import java.util.List;

import com.greedy.waterfall.task.model.dto.ChildTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.ParentTaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
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

}
