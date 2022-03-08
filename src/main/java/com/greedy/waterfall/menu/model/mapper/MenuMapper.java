package com.greedy.waterfall.menu.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;

/**
 * <pre>
 * Class : MenuMapper
 * Comment : 로그인 후 출력되는 메인메뉴에 필요한 데이터를 조회한다.
 * 
 * History
 * 2022. 3. 4.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Repository
public interface MenuMapper {


	/**
	 * findProjectCount : 프로젝트를 페이징처리를 해서 출력하기 위해 프로젝트 전체 갯수를 조회한다.
	 * @return 삭제되지 않은 프로젝트의 전체 갯수를 반환한다.
	 * 
	 * @author 홍성원
	 */
	public int findProjectCount(MemberDTO member);
	
	/**
	 * findMainProjectList : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	public List<ProjectDTO> findMainProjectList(SelectCriteria selectCriteria);

	/**
	 * findProjectInfo : 관리자페이지에 출력할 프로젝트에 대한 정보를 조회한다.
	 * @param 조회할 프로젝트의 번호를 전달받는다.
	 * @return 출력할 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	public ProjectDTO findProjectInfo(int projectNo);

	public int findJoinProjectCount(MemberDTO loginMember);

	public List<ProjectDTO> findJoinProjectList(SelectCriteria selectCriteria);

	public ProjectDTO findJoinProjectDetail(Map<String, Integer> searchMap); 
	
}
