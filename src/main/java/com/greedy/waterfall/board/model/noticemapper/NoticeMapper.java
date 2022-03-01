package com.greedy.waterfall.board.model.noticemapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.NoticeDTO;
import com.greedy.waterfall.board.model.noticedto.NoticeAttachmentDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : NoticeMapper
 * Comment : mapper 내 메소드 명
 * 
 * History
 * 2022. 3. 2.  (김서영)
 * @version 1
 * @author 김서영
 */
public interface NoticeMapper {

	int selectTotalCount(Map<String, String> searchMap);		//공지사항 게시물 개수

	List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);		//페이징 정보

	NoticeDTO selectNoticeDetail(int no);									//공지사항 상세 정보 조회

	int incrementNoticeCount(int no);										//게시물 클릭시 조회수 증가

	int insertNotice(NoticeDTO notice);										//공지사항 등록

	void updateNotice(NoticeDTO notice);									//공지사항 수정

	void deleteNotice(int no);												//공지사항 삭제

	int insertAttachment(NoticeAttachmentDTO attachmentDTO);				//첨부파일 등록


}
