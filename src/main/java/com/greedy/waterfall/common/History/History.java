package com.greedy.waterfall.common.History;

import java.util.List;

import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;

public interface History {
	
	List<ProjectHistoryDTO> registHistory(Object info);

	List<ProjectHistoryDTO> modifyHistory(Object info);
	
	List<ProjectHistoryDTO> removeHistory(Object info);
	
	public List<ProjectHistoryDTO> recoveryHistory(Object info);
}
