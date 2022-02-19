package com.greedy.waterfall.board.model.dto;

import java.sql.Date;

import com.greedy.waterfall.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class EduDTO {

	private int no; 					//글번호
	private java.sql.Date registedDate; //동록일
	private String title; 				//제목
	private String content; 			//내용
	private String status; 				//삭제여부 
	private int projectNo; 				//프로젝트 번호
	private int boardCategoryNo;		//카테고리 번호
	private java.sql.Date updatedDate;  //수정일
	private int memberNo;				//회원번호
	private int count;					//조회수
	private MemberDTO writer;
	
}
