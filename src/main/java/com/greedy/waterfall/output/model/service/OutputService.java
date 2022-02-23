package com.greedy.waterfall.output.model.service;

import java.util.List;

import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

public interface OutputService {

	List<TaskDTO> findOutputTask(TaskDTO taskDTO);

	OutputDTO findOutputDetail(int no);

	void removeOutput(int no);

}
