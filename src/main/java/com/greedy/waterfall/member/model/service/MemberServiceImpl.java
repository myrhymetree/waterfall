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
		
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
		System.out.println("확인용 서치" + " " + selectCriteria);
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
		
		if(adminResult < 0 || deptResult < 0 || teamResult < 0 || jobResult < 0 || memberResult < 0) {
			throw new MemberRegistException("게시글 등록에 실패하셨습니다.");
		}
	}


	@Override
	public AdminMemberDTO findMemberModify(String id) {
		
		System.out.println("확인용33" + id);
		System.out.println("확인용33" + id);
		System.out.println("확인용33" + id);
		
		AdminMemberDTO findModify = new AdminMemberDTO();
		
		findModify = mapper.adminMemberModify(id);
		List<DeptDTO> deptList = mapper.adminDeptMember();
		System.out.println("확인용44" + deptList);
		List<TeamDTO> teamList = mapper.adminTeamMember();
		System.out.println("확인용55" + teamList);
		List<JobDTO> jobList = mapper.adminJobMember();
		System.out.println("확인용66" + jobList);
		
		
		findModify.setDeptList(deptList);
		findModify.setTeamList(teamList);
		findModify.setJobList(jobList);
		
		return findModify;
	}
	

	@Override
	public void memberModify(AdminMemberDTO adminMember) {
		System.out.println("실행 첫번째 성공");
		int adminMemberResult = mapper.adminMember(adminMember);
		System.out.println("실행 두번째 성공");
		System.out.println("실행 두번쨰 " + "" + adminMember.getNo());
		int deptResult = mapper.adminDept(adminMember);
		System.out.println("실행 세번째 성공");
		System.out.println("실행 세번째 " + "" + adminMember.getNo());
		int teamResult = mapper.adminTeam(adminMember);
		System.out.println("실행 네번째 " + "" + adminMember.getNo());
		int jobResult = mapper.adminJob(adminMember);
		int memberResult = mapper.oneMember(adminMember);
		
		
	}


	@Override
	public void removeMember(String id) {

		int MemberResult = mapper.removeMember(id);
		System.out.println("memberResult" + "" + MemberResult);
		int MemberInfoResult = mapper.removeMemberInfo(id);
		System.out.println("MemberInfoResult" + "" + MemberInfoResult);
	}


	@Override
	public String pwCheck(String id) {
		
//		if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
//			int result = 0;	
//			return result;
//		}
		System.out.println("확인3번째" + id);
		return mapper.selectOne(id);
	}


	@Override
	public void pwUpdate(String id, String hashedPw) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pwd", hashedPw);
		
		mapper.pwUpdate(map);
	}


	@Override
	public void memberInfo(MemberDTO member) {

		mapper.memberInfoUpdate(member);
		System.out.println("성공 " + member);
		mapper.memberInfoPhone(member);
		System.out.println("성공 2번째 "+ ":" + member);
		mapper.memberInfoEmail(member);
	}



	

}
