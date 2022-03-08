package com.greedy.waterfall.history.model.service;

import java.util.List;

import com.greedy.waterfall.history.model.dto.HistoryDTO;
import com.greedy.waterfall.issue.model.dto.IssueDTO;

public interface HistoryService {

	List<HistoryDTO> selectIssueHistoryList(int projectNo);

	List<HistoryDTO> selectEntireHistoryList();

}
