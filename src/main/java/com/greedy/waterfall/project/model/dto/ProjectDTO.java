package com.greedy.waterfall.project.model.dto;

import java.sql.Date;

public class ProjectDTO {

	private int no;					   //프젝번호
	private String name;			   //프젝명
	private java.sql.Date startDate;   //시작일
	private java.sql.Date deadLine;    //마감일
	private int progression;		   //진행율
	private String status;  		   //프로젝트상태여부 
	private java.sql.Date completed;   //종료일
	private String statusCode; 		   //상태코드
	
	public ProjectDTO() {}

	public ProjectDTO(int no, String name, Date startDate, Date deadLine, int progression, String status,
			Date completed, String statusCode) {
		super();
		this.no = no;
		this.name = name;
		this.startDate = startDate;
		this.deadLine = deadLine;
		this.progression = progression;
		this.status = status;
		this.completed = completed;
		this.statusCode = statusCode;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(java.sql.Date deadLine) {
		this.deadLine = deadLine;
	}

	public int getProgression() {
		return progression;
	}

	public void setProgression(int progression) {
		this.progression = progression;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getCompleted() {
		return completed;
	}

	public void setCompleted(java.sql.Date completed) {
		this.completed = completed;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "ProjectDTO [no=" + no + ", name=" + name + ", startDate=" + startDate + ", deadLine=" + deadLine
				+ ", progression=" + progression + ", status=" + status + ", completed=" + completed + ", statusCode="
				+ statusCode + "]";
	}
	
	
}
