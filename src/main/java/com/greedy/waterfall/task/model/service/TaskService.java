package com.greedy.waterfall.task.model.service;

import java.util.List;

import com.greedy.waterfall.task.model.dto.TaskDTO;

public interface TaskService {

	List<TaskDTO> findTaskList(TaskDTO taskDTO);

}
