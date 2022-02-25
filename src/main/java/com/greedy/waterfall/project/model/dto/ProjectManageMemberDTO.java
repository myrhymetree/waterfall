package com.greedy.waterfall.project.model.dto;

import java.util.List;

public class ProjectManageMemberDTO {

	private int projectNo;
	private int memberNo;
	private String memberName;
	private int completeTask;
	private int progressingTask;
	private int registOutput;
	private int registIssue;
	private java.sql.Date joinDate;
	private java.sql.Date quitDate;
	
	private List<ProjectRoleDTO> role;
}
