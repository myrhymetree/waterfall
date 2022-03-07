package com.greedy.waterfall.common.History;

import java.util.List;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;

public interface History {
	
	List<ProjectHistoryDTO> registHistory(Object info);

	List<ProjectHistoryDTO> modifyHistory(Object info);
	
	List<ProjectHistoryDTO> deleteHistory(MemberDTO member, Object info);
	
	
}
