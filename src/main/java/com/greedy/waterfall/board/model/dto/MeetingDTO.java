package com.greedy.waterfall.board.model.dto;

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
public class MeetingDTO {

	private int no;
	private java.sql.Date updatedDate;
	private java.sql.Date registedDate;
	private String title;
	private String content;
	private String status;
	private int boardCategoryNo;
	private int projectNo;
	private int memberNo;
	private int boardCount;
	
	private MeetingMemberDTO member;
	
}
