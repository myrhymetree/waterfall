package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.board.model.mapper.MeetingMapper;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : MeetingServiceImpl
 * Comment : 회의록 게시판의 게시물 전체목록 조회, 게시글 상세조회, 수정, 삭제, 등록을 담당하는 서비스
 * 
 * History
 * 2022. 2. 18.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Service
public class MeetingServiceImpl implements MeetingService {
	private final MeetingMapper mapper;

	public MeetingServiceImpl(MeetingMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * findMeetingBoardList : 회의록게시판 검색과, 페이지정보를 담은 변수를 전달해, 해당 게시물목록을 반환받아 전달해주는 서비스 메소드
	 * @param selectCriteria : 검색조건과, 페이지정보를 담고있는 변수
	 * @return List<MeetingDTO> : 검색조건에 해당하는 한 페이지의 게시물의 정보를 저장한 변수를 반환
	 * 
	 * @author 홍성원
	 */
	@Override
	public List<MeetingDTO> findMeetingBoardList(SelectCriteria selectCriteria) { 
		/* 검색조건과 현재 페이지에 대한 정보를 담은 변수를 전달한 후 돌려받은 값을 반환한다. */
		return mapper.findMeetingList(selectCriteria);
	}

	/**
	 * findMeetingBoardDetail : 회의록 게시판 게시글의 번호를 입력받아 상세정보 반환해주는 서비스 메소드
	 * @param meetingNo : 상세정보를 조회할 게시물의 번호를 저장하는 변수
	 * @return MeetingDTO : 게시물 번호로 조회한 해당 게시물의 상세정보를 담은 변수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public MeetingDTO findMeetingBoardDetail(int meetingNo) {
		
		/* 게시물 번호를 전달해 반환받은  게시물의 상세정보를 반환한다.*/
		return mapper.findMeetingBoardDetail(meetingNo);
	}

	/**
	 * findMeetingTotalCount : 검색조건을 전달받아 맞는 게시물의 갯수를 반환하는 서비스 메소드 
	 * @param searchMap : 검색조건을 Map형태로 저장한 변수
	 * @return: 검색조건에 해당하는 게시물의 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public int findMeetingTotalCount(Map<String, String> searchMap) {
		/* Map형태로 전달받은 검색 조건을 전달 후 조회된 게시물의 갯수를 반환한다. */
		return mapper.findMeetingTotalCount(searchMap);
	}

	/**
	 * registMeetingBoard : 입력한 정보로 회의록 게시물을 작성하는 서비스 메소드
	 * @param parameter : 작성한 회의록게시물의 내용을 Map형태로 저장한 변수
	 * @return 새로운 게시물의 등록 여부를 boolean형태로 반환한다
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean registMeetingBoard(Map<String, String> parameter) {
		
		/* Map형태로 저장된 게시판의 내용을  전달한 후 등록의 성공여부를 boolean형태로 반환한다.*/
		return mapper.registMeetingBoard(parameter) > 0 ? true: false;
	}

	/**
	 * removeMeetingBoard : 게시물을 삭제하는 프로세스의 서비스 메소드
	 * @param meetingNo : 
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
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
