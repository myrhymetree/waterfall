package com.greedy.waterfall.member.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * Class : MemberDTO
 * Comment : 회원의 정보가 담을 클래스
 * 관련 테이블 : TBL_MEMBER 
 * 			
 * History
 * 2022. 3. 12.  (김영광)
 * </pre>
 * @version 0.0.1
 * @author 김영광
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MemberDTO {

	private int no; 					//회원번호
	private String id; 					//아이디
	private String pwd;					//비밀번호
	private String phone;   			//핸드폰번호
	private String email;   			//이메일
	private java.sql.Date createDate; 	//등록일
	private java.sql.Date retiredDate;  //탈퇴일
	private String status; 				//탈퇴여부
	private String name; 				//이름
	private String initStatus; 			//비밀번호 초기화
	private String role; 				//권한

	
}
