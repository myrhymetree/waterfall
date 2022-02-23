package com.greedy.waterfall.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dao.MemberMapper;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;

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
		if(!passwordEncoder.matches(member.getPwd(), mapper.selectEncryptedPwd(member))) {
			  throw new LoginFailedException("로그인 실패");
		}
		
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

}
