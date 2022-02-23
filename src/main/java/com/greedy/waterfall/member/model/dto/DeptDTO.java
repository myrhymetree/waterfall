package com.greedy.waterfall.member.model.dto;

import java.sql.Date;

public class DeptDTO {

	private String deptCode;	
	private String deptName;

	public DeptDTO() {}

	public DeptDTO(String deptCode, String deptName) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "DeptDTO [deptCode=" + deptCode + ", deptName=" + deptName + "]";
	}		
}
