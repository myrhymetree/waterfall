package com.greedy.waterfall.member.model.dao;

import com.greedy.waterfall.member.model.dto.MemberDTO;

public interface MemberMapper {

	String selectEncryptedPwd(MemberDTO member);

	MemberDTO selectMember(MemberDTO member);

}
