package com.greedy.waterfall.project.model.dto;

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
public class RegistProjectDTO {

	private int projectNo;
	private String projectName;
	private java.sql.Date startDate;
	private java.sql.Date deadLine;
	private int pmNumber;
	private String projectStatus;
	private String dept;
	private String team;
	private int adminNo;
}
