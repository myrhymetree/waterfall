package com.greedy.waterfall.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.exception.member.MemberRegistException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dao.MemberMapper;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	/* 회원 로그인용 메소드 */
	@Override
	public MemberDTO findMember(MemberDTO member) throws LoginFailedException {
//		if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
//			  throw new LoginFailedException("로그인 실패");
//		}
		
		return mapper.selectMember(member);
	}


	@Override
	public List<AdminMemberDTO> findAdminMember(SelectCriteria selectCriteria) {
		
		List<AdminMemberDTO> adminMemberList = mapper.findAdminMemberList(selectCriteria);
		
		return adminMemberList;
	}


	@Override
	public int selectTotalCount(Map<String, String> searchMap) {
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}


	@Override
	public Map<String, Object> findDeptJobService() {

		Map<String, Object> allList = new HashMap<>();
		List<DeptDTO> deptDTO = mapper.findDept(); 
		List<JobDTO> jobDTO = mapper.findJob();
		
		allList.put("deptDTO", deptDTO);
		allList.put("jobDTO", jobDTO);
		
	    return allList;
	}

	@Override
	public List<TeamDTO> findTeamList(String deptCode) {

		return mapper.findTeamList(deptCode);
	}


	@Override
	public void adminMemberRegist(AdminMemberDTO adminMember) throws MemberRegistException {
		
		int adminResult = mapper.adminMemberRegist(adminMember);
		int deptResult = mapper.deptMemberRegist(adminMember);
		int teamResult = mapper.teamMemberRegist(adminMember);
		int jobResult = mapper.jobMemberRegist(adminMember);	
		int memberResult = mapper.memberRegist(adminMember);
		
		if(adminResult < 0 && deptResult < 0 && teamResult < 0 && jobResult < 0 && memberResult < 0) {
			throw new MemberRegistException("게시글 등록에 실패하셨습니다.");
		}
	}


	@Override
	public AdminMemberDTO findMemberModify(String id) {

		AdminMemberDTO findModify = mapper.adminMemberModify(id); 
		
		
		
		return findModify;
	}


	

}
