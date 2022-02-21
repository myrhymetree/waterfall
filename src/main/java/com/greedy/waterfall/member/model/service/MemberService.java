package com.greedy.waterfall.member.model.service;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.member.model.dto.MemberDTO;

public interface MemberService {

	MemberDTO findMember(MemberDTO member) throws LoginFailedException;

}
