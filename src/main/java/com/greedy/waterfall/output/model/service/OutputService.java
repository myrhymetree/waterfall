package com.greedy.waterfall.output.model.service;

import java.util.List;

import com.greedy.waterfall.task.model.dto.TaskDTO;

public interface OutputService {

	List<TaskDTO> findOutputTask(TaskDTO taskDTO);

}
