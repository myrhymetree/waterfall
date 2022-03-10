package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.dto.EduFileDTO;
import com.greedy.waterfall.board.model.mapper.EduMapper;
import com.greedy.waterfall.common.exception.board.BoardModifyException;
import com.greedy.waterfall.common.exception.board.BoardRegistException;
import com.greedy.waterfall.common.exception.board.BoardRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

@Service
public class EduServiceImpl implements EduService {

	  private final EduMapper mapper;
	  
	 /* 매퍼와 의존관계를 위한*/
	  @Autowired 
	  public EduServiceImpl(EduMapper mapper) { 
		  this.mapper = mapper; 
	}
	  
	 /* 게시글 전체 갯수조회 */
	  
	  @Override 
	  public int selectTotalCount(Map<Object, Object> searchMap) { 
		  int result = mapper.selectTotalCount(searchMap);
	 
	  return result; 
	  }
	 

	/* 게시글 전체 조회 */
	@Override
	public List<EduDTO> selectEduList(SelectCriteria selectCriteria) {
		List<EduDTO> eduList = mapper.selectEduList(selectCriteria);
		
		return eduList;
	}

	@Override
	public void registEdu(EduDTO eduBoard) throws BoardRegistException {
		int result = mapper.insertEduBoard(eduBoard);
		
		EduFileDTO eduFileDTO = eduBoard.getFile();
		
		if(eduFileDTO != null) {
			eduFileDTO.setRefBoardNo(eduBoard.getNo());
			mapper.insertEduFile(eduFileDTO);
		}
		
		if(!(result >0 )) {
			throw new BoardRegistException("게시글 등록이 실패");
		}
		
	}

    @Override
    public EduDTO findEduDetail(int no) {
    	
        int result = mapper.incrementEduCount(no);
       
        EduDTO eduDetail = new EduDTO();
       
       	if(result > 0) {
       		eduDetail = mapper.selectEduDetail(no);
       	}   
       
        return eduDetail;
   }

	
	@Override
	public void removeEduBoard(int no) throws BoardRemoveException {
		
		int result = mapper.deleteEduBoard(no);
		
		if(!(result > 0)) {
			throw new BoardRemoveException("게시판 삭제에 실패하셨습니다.");
		}
		
	}

	
	@Override
	public void modifyEduBoard(EduDTO edu) throws BoardModifyException {
		int result = mapper.updateEduBoard(edu);
		
		if(!(result > 0)) {
			throw new BoardModifyException("공지사항 수정에 실패하셨습니다.");
		}
		
	}

	
	@Override
	public EduDTO findEduFileDetail(int no) {

		EduDTO eduFileDetail = new EduDTO();

		eduFileDetail = mapper.selectEduDetailFile(no);

		return eduFileDetail;
	}

	@Override
	public EduFileDTO findFile(int no) {

		return mapper.findFile(no);
	}

	@Override
	public EduFileDTO removeEduFile(int fileNumber) {
		
		EduFileDTO eduFileDTO = mapper.findFile(fileNumber);
		
		int result = mapper.deleteEduFile(fileNumber);
		
		return eduFileDTO;
	}

	
	
	

}
