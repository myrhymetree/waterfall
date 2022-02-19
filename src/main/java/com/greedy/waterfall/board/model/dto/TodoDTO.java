package com.greedy.waterfall.board.model.dto;

import java.sql.Date;

import com.greedy.waterfall.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor	//기본 생성자
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TodoDTO {

	private int no;
	private Date postingDate;
	private String title;
	private String content;
	private String status;
	private int categoryNo;
	private int count;
	private MemberDTO writer;
	
//	public TodoDTO() {}	//기본 생성자
	
}
