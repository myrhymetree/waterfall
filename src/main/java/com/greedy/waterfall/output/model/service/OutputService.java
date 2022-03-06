package com.greedy.waterfall.output.model.service;

import java.util.List;

import com.greedy.waterfall.output.model.dto.OutputAttachmentDTO;
import com.greedy.waterfall.output.model.dto.OutputDTO;
import com.greedy.waterfall.output.model.dto.OutputProjectDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

public interface OutputService {

	List<TaskDTO> findOutputTask(TaskDTO taskDTO);

	Object findOutputDetail(int no);

	void removeOutput(OutputDTO output);

	List<OutputProjectDTO> findOutputList();

	int registOutput(OutputDTO output);

	OutputAttachmentDTO findOutputFile(int outputNo);

	void modifyOutput(OutputDTO output);

	int selectOutputCount(int outputNo);


}
