package com.greedy.waterfall.member.model.dto;

import java.sql.Date;

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
public class AdminMemberDTO {

	private int no;
	private String id;
	private String name;
	private int phone;
	private String email;
	private java.sql.Date createDate;
	private DeptDTO dept; 
	private TeamDTO team;
	private JobDTO job;

}
