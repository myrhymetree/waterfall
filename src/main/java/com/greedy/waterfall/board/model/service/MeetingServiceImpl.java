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

}
