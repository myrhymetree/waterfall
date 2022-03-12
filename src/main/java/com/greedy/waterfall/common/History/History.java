package com.greedy.waterfall.common.History;

import java.util.List;

import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;

public interface History {
	
	final static int PROJECT_HISTORY = 1;
	final static int TASK_HISTORY = 2;
	final static int ISSUE_HISTORY = 3;
	final static int OUTPUT_HISTORY = 4;
	final static int MEMBER_HISTORY = 5;
	final static int BOARD_HISTORY = 6;
	
	List<ProjectHistoryDTO> registHistory(Object info);

	List<ProjectHistoryDTO> modifyHistory(Object info);
	
	List<ProjectHistoryDTO> removeHistory(Object info);
	
	public List<ProjectHistoryDTO> recoveryHistory(Object info);
}
