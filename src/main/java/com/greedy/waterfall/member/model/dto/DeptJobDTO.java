package com.greedy.waterfall.member.model.dto;

public class DeptJobDTO {

	private String deptCode;	
	private String deptName;
	private String jobCode;
	private String jobName;
	
	public DeptJobDTO() {}

	public DeptJobDTO(String deptCode, String deptName, String jobCode, String jobName) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.jobCode = jobCode;
		this.jobName = jobName;
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

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public String toString() {
		return "DeptJobDTO [deptCode=" + deptCode + ", deptName=" + deptName + ", jobCode=" + jobCode + ", jobName="
				+ jobName + "]";
	}
	
	
	
}
