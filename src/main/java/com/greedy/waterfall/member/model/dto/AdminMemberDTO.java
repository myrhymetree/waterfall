package com.greedy.waterfall.member.model.dto;

import java.sql.Date;

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
	
	public AdminMemberDTO() {}

	public AdminMemberDTO(int no, String id, String name, int phone, String email, Date createDate, DeptDTO dept,
			TeamDTO team, JobDTO job) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.createDate = createDate;
		this.dept = dept;
		this.team = team;
		this.job = job;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.sql.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}

	public DeptDTO getDept() {
		return dept;
	}

	public void setDept(DeptDTO dept) {
		this.dept = dept;
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	public JobDTO getJob() {
		return job;
	}

	public void setJob(JobDTO job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "AdminMemberDTO [no=" + no + ", id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", createDate=" + createDate + ", dept=" + dept + ", team=" + team + ", job=" + job + "]";
	}

	
	
	
}
