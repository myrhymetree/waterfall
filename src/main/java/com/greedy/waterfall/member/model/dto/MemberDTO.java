package com.greedy.waterfall.member.model.dto;

import java.sql.Date;


public class MemberDTO {

	private int no; 					//회원번호
	private String id; 					//아이디
	private String pwd;					//비밀번호
	private String phone;   			//핸드폰번호
	private String email;   			//이메일
	private java.sql.Date createDate; 	//등록일
	private java.sql.Date retiredDate;  //탈퇴일
	private String status; 				//탈퇴여부
	private String name; 				//이름
	private String initStatus; 			//비밀번호 초기화
	private String role; 				//권한
	
	public MemberDTO() {}

	public MemberDTO(int no, String id, String pwd, String phone, String email, Date createDate, Date retiredDate,
			String status, String name, String initStatus, String role) {
		super();
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.email = email;
		this.createDate = createDate;
		this.retiredDate = retiredDate;
		this.status = status;
		this.name = name;
		this.initStatus = initStatus;
		this.role = role;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
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

	public java.sql.Date getRetiredDate() {
		return retiredDate;
	}

	public void setRetiredDate(java.sql.Date retiredDate) {
		this.retiredDate = retiredDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitStatus() {
		return initStatus;
	}

	public void setInitStatus(String initStatus) {
		this.initStatus = initStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MemberDTO [no=" + no + ", id=" + id + ", pwd=" + pwd + ", phone=" + phone + ", email=" + email
				+ ", createDate=" + createDate + ", retiredDate=" + retiredDate + ", status=" + status + ", name="
				+ name + ", initStatus=" + initStatus + ", role=" + role + "]";
	}
	
	
	
}
