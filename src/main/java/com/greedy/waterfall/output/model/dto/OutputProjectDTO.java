package com.greedy.waterfall.output.model.dto;

import java.util.List;

import com.greedy.waterfall.member.model.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class OutputProjectDTO {

	private int no; // 프젝번호
	private String name; // 프젝명
	private java.sql.Date startDate; // 시작일
	private java.sql.Date deadLine; // 마감일
	private int progression; // 진행율
	private java.sql.Date completed; // 종료일
	private String statusCode; // 상태코드

	private MemberDTO manager;
	private OutputProjectStatusDTO status;
	private List<OutputDTO> output;
	private int totalOutputCount;
	private int totalDeletedOutputCount;

}
