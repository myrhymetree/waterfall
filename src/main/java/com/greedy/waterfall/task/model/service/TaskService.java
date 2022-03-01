package com.greedy.waterfall.task.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.task.model.dto.AllTaskCodeDTO;
import com.greedy.waterfall.task.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.task.model.dto.TaskCategoryDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;
import com.greedy.waterfall.task.model.dto.TaskRegistDTO;

public interface TaskService {

	List<TaskDTO> findTaskTimeline(TaskDTO taskDTO);

	AllTaskCodeDTO findAllTaskCode();

	List<ProjectMemberDTO> findProjectMember(int projectNo);

	void registTask(TaskRegistDTO taskRegistDTO);

	List<TaskCategoryDTO> findAllCategoryCode();


}
