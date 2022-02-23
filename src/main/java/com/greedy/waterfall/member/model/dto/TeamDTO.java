package com.greedy.waterfall.member.model.dto;

import java.sql.Date;

public class TeamDTO {

	private String teamCode;
	private String teamName;
	
	public TeamDTO() {}

	public TeamDTO(String teamCode, String teamName) {
		super();
		this.teamCode = teamCode;
		this.teamName = teamName;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "TeamDTO [teamCode=" + teamCode + ", teamName=" + teamName + "]";
	}
	
}
