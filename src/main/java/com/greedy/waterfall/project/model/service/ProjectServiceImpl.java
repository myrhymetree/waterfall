package com.greedy.waterfall.project.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.board.model.dto.FileDTO;
import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.common.paging.PagingDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.BoardCategoryDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectMainBoardDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;
import com.greedy.waterfall.project.model.mapper.ProjectMapper;

/**
 * <pre>
 * Class : ProjectServiceImpl
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 * 
 * Review
 * 2022. 2. 23 (차화응)
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectMapper mapper;
	private final Paging paging;

	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper, Paging paging) {
		this.mapper = mapper;
		this.paging = paging;
	}
	
	@Override
	public MyProjectDTO findMyProject(Map<String, String> searchMap, MemberDTO member) {
		
		List<ProjectDTO> manageProject = new ArrayList<ProjectDTO>();
		List<ProjectDTO> joinProject = new ArrayList<ProjectDTO>();
		List<ProjectDTO> removedProject = new ArrayList<ProjectDTO>();
		SelectCriteria selectCriteria = null;
		if(member != null) {
			if("1".equals(member.getRole())) {
				searchMap.put("totalCount", Integer.toString(mapper.findAllManageProjectCount(searchMap)));

				PagingDTO pagingSetting = new PagingDTO().builder().limit(5).buttonAmount(5).memberNo(member.getNo()).build();
				
				selectCriteria = paging.setPagingCondition(searchMap, pagingSetting);
				manageProject = mapper.findAllManageProject(selectCriteria);
				removedProject = mapper.findAllRemovedProject();
			} else {
				manageProject = mapper.findManagaProject(member.getNo());
				joinProject = mapper.findJoinProject(member.getNo());
				
			}
		}
		MyProjectDTO projectList = new MyProjectDTO().builder()
													.manageProject(manageProject)
													.joinProject(joinProject)
													.removedProject(removedProject)
													.selectCriteria(selectCriteria)
													.build();
		
		return projectList;
	}

	@Override
	public Map<String, Object> findRegistForm() {

		List<ProjectStatusDTO> statusList = mapper.findAllProjectStatus();

		List<DeptDTO> deptList = mapper.findAllDept();
		
		Map<String, Object> projectForm = new HashedMap();
		
		projectForm.put("statusList", statusList);
		projectForm.put("deptList", deptList);
		
		return projectForm;
	}
	
	@Override
	public List<TeamDTO> findTeam(String deptCode){
		
		return mapper.findTeam(deptCode);
	}

	@Override
	public List<MemberDTO> findTeamMember(String teamCode) {
		
		return mapper.findTeamMember(teamCode);
	}

	@Override
	public boolean registProject(RegistProjectDTO newProject) {

		int registProjectdResult = mapper.registProject(newProject);
		int pmRegistResult = mapper.registPm(newProject);
		int memberRegistResult = mapper.registMemberProject(newProject);
		int registProjectHistory = mapper.registProjectHistory(newProject.getProjectNo());
		if(registProjectdResult > 0 && pmRegistResult > 0 && memberRegistResult > 0) {

			return true;
		}
		
		return false;
	}

	/**
	 * findPmNumber : 프로젝트 목록페이지에서 프로젝트 메인페이지로 이동할 떄 해당 프로젝트의 pm번호를 조회한다.
	 * @param 프로젝트번호를 전달받는다.
	 * @return 프로젝트 pm의 회원번호를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public int findPmNumber(int projectNo) {

		return mapper.findPmNumber(projectNo);
	}

	/**
	 * findOneProjectInfo : 프로젝트 수정하기위해 하나의 프로젝트 상세정보를 조회한다.
	 * @param projectNo : 프로젝트의 번호를 전달받는다.
	 * @return : 프로젝트의 상세정보를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public RegistProjectDTO findOneProjectInfo(int projectNo) {

		return mapper.findOneProjectInfo(projectNo);
	}

	/**
	 * modifyProject : 프로젝트의 정보를 수정한다.
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean modifyProject(RegistProjectDTO newProject) {
		
		RegistProjectDTO oldProject = mapper.findOneProjectInfo(newProject.getProjectNo());
		int resultPmChange = 1;
		int resultModifyProject = mapper.modifyProject(newProject);
		if(oldProject.getPmNumber() != newProject.getPmNumber()) {
			
			/* 새로운pm번호가 기존 pm번호랑 다를시 회원 배정내역, 회원 역할배정내역에 추가해준다. */
			/* 새로운 pm이 프로젝트에 배정이 안돼있을 시 프로젝트에 배정한다. */
			if(mapper.findMemberInProject(newProject) != null) {
				int pmChangeResult = mapper.joinPmInProject(newProject);
				
				if(pmChangeResult <= 0) {
					resultPmChange = 0;
				}
			} else {
				/* 이미 배정되어있는 멤버라면 역할을 pm으로 등록한다 */
				int pmChangeResult = mapper.assignPmRole(newProject);
				
				if(pmChangeResult <= 0) {
					resultPmChange = 0;
				}
			}
			
			/* 기존의 pm은 프로젝트에서 내보낸다. */
			int pmChangeResult = mapper.kickOldPm(newProject);
			
			if(pmChangeResult <= 0) {
				resultPmChange = 0;
			}
		}
		
		if(resultPmChange > 0 && resultModifyProject > 0) {
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public boolean removeProject(int projectNo) {

		return mapper.removeProject(projectNo);
	}

	@Override
	public boolean restoreProject(int projectNo) {
		
		return mapper.restoreProject(projectNo);
	}
	
	@Override
	public boolean deleteProject(int projectNo) {
		
		return mapper.deleteProject(projectNo);
	}
	
	@Override
	public Map<String, Object> findProjectMainInfo(int projectNo) {
		Map<String, Object> projectMainInfo = new HashMap<String, Object>();

		ProjectAuthorityDTO projectAutority = new ProjectAuthorityDTO().builder()
				.pmNo(mapper.findPmNumber(projectNo))
				.projectNo(projectNo).build();
		BoardCategoryDTO SearchCondition = new BoardCategoryDTO(projectNo);
		
		ProjectMainBoardDTO projectBoard = new ProjectMainBoardDTO().builder()
				.guideBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(3)))
				.meetingBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(4)))
				.todoBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(5)))
				.noticeBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(1)))
				.eduBoard(mapper.findMainBoardList(SearchCondition.setBoardCategory(2)))
				.build();

		projectMainInfo.put("projectAutority", projectAutority);
		projectMainInfo.put("projectBoard", projectBoard);
		

		return projectMainInfo;
	}

	@Override
	public BoardDTO findBoardInfo(int boardNo) {
		BoardDTO boardInfo = mapper.findBoardInfo(boardNo);
		int categoryNo = boardInfo.getBoardCategoryNo();
		String boardCategoryName = "";
		switch(categoryNo) {
			case 1: boardCategoryName = "공지사항"; break;
			case 2: boardCategoryName = "교육 게시판"; break;
			case 3: boardCategoryName = "프로젝트 가이드"; break;
			case 4: boardCategoryName = "회의록 게시판"; break;
			case 5: boardCategoryName = "TO DO"; break;
		}
		
		boardInfo.setBoardCategoryName(boardCategoryName);
		
		return boardInfo;
	}
}

































