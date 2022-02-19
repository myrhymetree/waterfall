package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.board.model.mapper.MeetingMapper;
import com.greedy.waterfall.common.paging.SelectCriteria;

@Service
public class MeetingServiceImpl implements MeetingService {

	private final MeetingMapper mapper;
	public MeetingServiceImpl(MeetingMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<MeetingDTO> findMeetingBoardList(SelectCriteria selectCriteria) { 
		
		return mapper.findMeetingList(selectCriteria);
	}

	@Override
	public MeetingDTO findMeetingBoardDetail(int meetingNo) {
		
		return mapper.findMeetingBoardDetail(meetingNo);
	}

	@Override
	public int findMeetingTotalCount(Map<String, String> searchMap) {
		return mapper.findMeetingTotalCount(searchMap);
	}

	@Override
	public boolean registMeetingBoard(Map<String, String> parameter) {
		
		return mapper.registMeetingBoard(parameter) > 0 ? true: false;
	}

	@Override
	public boolean removeMeetingBoard(int meetingNo) {

		return mapper.removeMeetingBoard(meetingNo);
	}

	@Override
	public boolean modifyMeetingBoard(Map<String, String> meeting) {
		
		return mapper.modifyMeetingBoard(meeting);
	}

	@Override
	public MeetingDTO findOneMeetingBoard(int meetingNo) {

		return mapper.findOneMeetingBoard(meetingNo);
	}

}
