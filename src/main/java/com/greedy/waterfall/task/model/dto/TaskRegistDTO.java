package com.greedy.waterfall.task.model.dto;

import java.sql.Date;
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
public class TaskRegistDTO {
	
	private String taskCode;
	private String parentTaskCode;
	private String taskMember;
	private Date startDate;
	private Date deadline;
	private String importance;
	private String progressStatus;
	private int progressRatio;
	private int typeNo;
	private int projectNo;
	private int parentTaskNo;
	
	
}
