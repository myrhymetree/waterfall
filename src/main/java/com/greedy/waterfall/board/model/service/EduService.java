package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.dto.EduFileDTO;
import com.greedy.waterfall.common.exception.board.BoardModifyException;
import com.greedy.waterfall.common.exception.board.BoardRegistException;
import com.greedy.waterfall.common.exception.board.BoardRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface EduService {

	int selectTotalCount(Map<Object, Object> searchMap); 

	List<EduDTO> selectEduList(SelectCriteria selectCriteria);

	void registEdu(EduDTO eduBoard) throws BoardRegistException;

	EduDTO findEduDetail(int no);

	void removeEduBoard(int no) throws BoardRemoveException;

	void modifyEduBoard(EduDTO edu) throws BoardModifyException;

	EduDTO findEduFileDetail(int no);

	EduFileDTO findFile(int no);

	EduFileDTO removeEduFile(int fileNumber);

}
