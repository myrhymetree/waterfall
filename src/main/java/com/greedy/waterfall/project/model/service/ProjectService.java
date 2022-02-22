package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

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

	MyProjectDTO findMyProject(int no);

	Map<String, Object> findRegistForm();

	List<TeamDTO> findTeam(String deptCode);

	List<MemberDTO> findTeamMember(String teamCode);

	boolean registProject(RegistProjectDTO newProject);
}