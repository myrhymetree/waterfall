package com.greedy.waterfall.board.model.noticemapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.NoticeDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface NoticeMapper {

	int selectTotalCount(Map<String, String> searchMap);

	List<NoticeDTO> selectNoticeList(SelectCriteria selectCriteria);

	NoticeDTO selectNoticeDetail(int no);

	int incrementNoticeCount(int no);

	int insertNotice(NoticeDTO notice);

	void updateNotice(NoticeDTO notice);

	void deleteNotice(int no);

}
