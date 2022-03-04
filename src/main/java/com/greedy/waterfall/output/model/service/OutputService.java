package com.greedy.waterfall.output.model.service;

import java.util.List;

import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

public interface OutputService {

	List<TaskDTO> findOutputTask(TaskDTO taskDTO);

	Object findOutputDetail(int no);

	void removeOutput(int no);

	List<OutputProjectDTO> findOutputList();

	void registOutput(OutputDTO output);


}
