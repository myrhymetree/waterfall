package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.dto.EduFileDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface EduMapper {

	int selectTotalCount(Map<Object, Object> searchMap);
	
	List<EduDTO> selectEduList(SelectCriteria selectCriteria);

	int insertEduBoard(EduDTO eduBoard);

	int incrementEduCount(int no);

	EduDTO selectEduDetail(int no);

	int deleteEduBoard(int no);

	int updateEduBoard(EduDTO edu);

	EduDTO selectEduDetailFile(int no);

	EduFileDTO findFile(int no);

	int deleteEduFile(int fileNumber);

	void insertEduFile(EduFileDTO eduFileDTO);


	

}
