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

	Map<String, Object> findMeetingBoardList(Map<String, String> searchMap);

	MeetingDTO findMeetingBoardDetail(int meetingNo);

	int findMeetingTotalCount(Map<String, String> searchMap);

	boolean registMeetingBoard(MeetingDTO parameter);
	
	boolean removeMeetingBoard(int meetingNo);
	
	boolean modifyMeetingBoard(Map<String, String> meeting);

	MeetingDTO findOneMeetingBoard(int meetingNo);

	FileDTO findFile(int no);

	/**
	 * findMainList : 프로젝트 컨트롤러에서 프로젝트 메인화면에 회의록 게시글 6개를 조회한다.
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 * @param projectNo 
	 */
	List<MeetingDTO> findMainList(int projectNo);


}
