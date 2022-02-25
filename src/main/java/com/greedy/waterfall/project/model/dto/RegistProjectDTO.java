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
@Builder
public class RegistProjectDTO {

	private int projectNo;
	private String projectName;
	private java.sql.Date startDate;
	private java.sql.Date deadLine;
	private int pmNumber;
	private String pmName;
	private String projectStatusCode;
	private int progression;
	private String dept;
	private String team;
	private int adminNo;
	@Override
	public String toString() {
		return "RegistProjectDTO [projectNo=" + projectNo + "\n"  + ", projectName=" + projectName + ", startDate=" + startDate
				+ "\n" + ", deadLine=" + deadLine + ", pmNumber=" + pmNumber + "\n" + ", pmName=" + pmName + ", projectStatusCode="
				+ projectStatusCode +  "\n" +", progression=" + progression + ", dept=" + dept + ", team=" + team + "\n" 
				+ ", adminNo=" + adminNo + "]";
	}
	
}
