package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

@Repository
public interface MeetingMapper {

	List<MeetingDTO> findMeetingList(SelectCriteria selectCriteria);

	MeetingDTO findMeetingBoardDetail(int meetingNo);

	int findMeetingTotalCount(Map<String, String> searchMap);

	int registMeetingBoard(Map<String, String> parameter);

	boolean removeMeetingBoard(int meetingNo);
	
	boolean modifyMeetingBoard(Map<String, String> meeting);

	MeetingDTO findOneMeetingBoard(int meetingNo);

}
