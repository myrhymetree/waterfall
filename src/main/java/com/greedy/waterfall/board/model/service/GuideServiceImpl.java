package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.GuideDTO;
import com.greedy.waterfall.board.model.guidemapper.GuideMapper;
import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRegistException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : GuideServiceImpl
 * Comment : GuideService를 인터페이스를 구현하는 클래스
 * 
 * History
 * 2022. 2. 19.  (박성준)
 * </pre>
 * @version 1
 * @author 박성준
 */
@Service
public class GuideServiceImpl implements GuideService {

	private final GuideMapper mapper;
	
	@Autowired
	public GuideServiceImpl(GuideMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<GuideDTO> selectAllGuideList(SelectCriteria selectCriteria) {
		List<GuideDTO> guideList = mapper.selectAllGuideList(selectCriteria);
		
		return guideList;
	}

	@Override
	public int selectTotalCount(Map<String, String> searchMap) {
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}

	@Override
	public void registGuide(GuideDTO guide) throws GuideRegistException {
		
		int result = mapper.insertGuide(guide);
		
		if(!(result > 0)) {
			throw new GuideRegistException("가이드 게시글 등록에 실패하셨습니다.");
		}
	}

	@Override
	public void removeGuide(int no) throws GuideRemoveException {

		int result = mapper.deleteGuide(no);
		
		if(!(result > 0)) {
			throw new GuideRemoveException("가이드 게시글 삭제에 실패하셨습니다.");
		}
	}

	/**
	 * modifyGuide : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 박성준
	 */
	@Override
	public void modifyGuide(GuideDTO guide) throws GuideModifyException {
		
		int result = mapper.updateGuide(guide);
		
		if(!(result > 0)) {
			throw new GuideModifyException("가이드 게시글 수정에 실패하셨습니다.");
		}
		
	}

	@Override
	public GuideDTO findGuideDetail(int no) {
		 int result = mapper.incrementGuideCount(no);
	      
	      GuideDTO guideDetail = new GuideDTO();
	      
	      if(result > 0) {
	         guideDetail = mapper.selectGuideDetail(no);
	      }
	      
	      System.out.println("guideDetail : " + guideDetail);
	      
	      return guideDetail;
	}

}
