package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface MeetingService {

	List<MeetingDTO> findMeetingBoardList(SelectCriteria selectCriteria);

	MeetingDTO findMeetingBoardDetail(int meetingNo);

	int findMeetingTotalCount(Map<String, String> searchMap);

	boolean registMeetingBoard(MeetingDTO parameter);
	
	boolean removeMeetingBoard(int meetingNo);
	
	boolean modifyMeetingBoard(Map<String, String> meeting);

	MeetingDTO findOneMeetingBoard(int meetingNo);

	FileDTO findFile(int no);


}
