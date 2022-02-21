package com.greedy.waterfall.output.model.dto;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.task.model.dto.TaskDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OutputDTO {
	
	private int outputNo;
	private int outputVer;
	private String content;
	private java.sql.Date registedDate;
	private String status;
	private int memberNo;
	private int projectNo;
	private MemberDTO member;
	private TaskDTO task;
	private ProjectDTO project;
	private OutputAttachmentDTO outputFile;
	
	


}
