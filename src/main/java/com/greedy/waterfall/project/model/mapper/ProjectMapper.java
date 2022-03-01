package com.greedy.waterfall.project.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.BoardCategoryDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;

/**
 * <pre>
 * Class : ProjectMapper
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Repository
public interface ProjectMapper {

	List<ProjectDTO> findManagaProject(int no);

	List<ProjectDTO> findJoinProject(int no);
	
	List<ProjectStatusDTO> findAllProjectStatus();

	List<DeptDTO> findAllDept();
	
	List<TeamDTO> findTeam(String deptCode);

	List<MemberDTO> findTeamMember(String teamCode);

	//프로젝트 생성관련 
	int registProject(RegistProjectDTO newProject);

	int registPm(RegistProjectDTO newProject);

	int registMemberProject(RegistProjectDTO newProject);

	//어드민 프로젝트 전체 조회
//	List<ProjectDTO> findAllProject();
	
	List<ProjectDTO> findAllManageProject(SelectCriteria selectCriteria);

	List<ProjectDTO> findAllRemovedProject();

	int findAllManageProjectCount(Map<String, String> searchMap);
	
	
	
	
	
	
	
	
	
	
	/**
	 * findPmNumber : 프로젝트메인페이지 이동 시 , 해당 프로젝트의 pm 회원번호를 조회한다.
	 * @param 이동하는 프로젝트의 번호를 전달받는다.
	 * @return 프로젝트의 pm번호를 반환한다.
	 * 
	 * @author 홍성원
	 */
	int findPmNumber(int projectNo);

	/**
	 * findOneProjectInfo : 프로젝트 수정을 위해 프로젝트 상세정보를 조회한다.
	 * @param 프로젝트의 번호를 전달받는다.
	 * @return 프로젝트의 정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	RegistProjectDTO findOneProjectInfo(int projectNo);

	/**
	 * registProjectHistory : 프로젝트 생성시 프로젝트 생성 이력을 기록한다.
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	int registProjectHistory(int projectNo);

	/**
	 * modifyProject : 프로젝트 정보를 수정한다.
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	int modifyProject(RegistProjectDTO newProject);

	Integer findMemberInProject(RegistProjectDTO newProject);

	int joinPmInProject(RegistProjectDTO newProject);

	int assignPmRole(RegistProjectDTO newProject);

	int kickOldPm(RegistProjectDTO newProject);
	
	boolean removeProject(int projectNo);

	boolean restoreProject(int projectNo);

	boolean deleteProject(int projectNo);

	List<BoardDTO> findMainBoardList(BoardCategoryDTO searchCondition);

	BoardDTO findBoardInfo(int boardNo);


}
