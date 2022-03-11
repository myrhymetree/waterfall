package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.greedy.waterfall.board.model.dto.BoardDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;

@Component("stub")
@Primary
public class StubProjectServiceImplTest implements ProjectService {

	@Override
	public MyProjectDTO findMyProject(Map<String, String> searchMap, MemberDTO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findRegistForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamDTO> findTeam(String deptCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> findTeamMember(String teamCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registProject(RegistProjectDTO newProject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int findPmNumber(int projectNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RegistProjectDTO findOneProjectInfo(int projectNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyProject(RegistProjectDTO project) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProject(Map<String, Integer> removeInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean restoreProject(int projectNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProject(int projectNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> findProjectMainInfo(int projectNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO findBoardInfo(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

}