package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * interface : MeetingService
 * Comment : MeetingServiceImpl에서 사용할 Method들을 선언해놓은 인터페이서
 * 
 * History
 * 2022. 2. 23.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
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
