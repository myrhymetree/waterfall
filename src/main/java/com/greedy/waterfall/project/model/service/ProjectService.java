package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;

/**
 * <pre>
 * Class : ProjectService
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
public interface ProjectService {

	MyProjectDTO findMyProject(Map<String, String> searchMap, MemberDTO member);

	Map<String, Object> findRegistForm();

	List<TeamDTO> findTeam(String deptCode);

	List<MemberDTO> findTeamMember(String teamCode);

	boolean registProject(RegistProjectDTO newProject);

	/* 프로젝트 번호로 pm번호를 반환한다. 프로젝트 목록에서 프로젝트 메인으로 이동할 때 해당 프로젝트의 pm번호를 세션에 추가한다. */
	int findPmNumber(int projectNo);

	RegistProjectDTO findOneProjectInfo(int projectNo);

	boolean modifyProject(RegistProjectDTO project);
	
	boolean removeProject(Map<String, Integer> removeInfo);

	boolean restoreProject(int projectNo);
	
	boolean deleteProject(int projectNo);
	
	Map<String, Object> findProjectMainInfo(int projectNo);

	BoardDTO findBoardInfo(int boardNo);
	
	
}