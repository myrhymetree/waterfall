package com.greedy.waterfall.output.model.dto;

import org.springframework.web.multipart.MultipartFile;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.task.model.dto.ChildTaskDTO;
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
@ToString
@Builder
public class OutputDTO {
	
	private int outputNo;
	private int outputVer;
	private int taskNo;
	private int fileNo;
	private String content;
	private java.sql.Date registedDate;
	private String status;
	private int memberNo;
	private String memberName;
	private int projectNo;
	private TaskDTO task;
	private ChildTaskDTO childTask;
	private OutputProjectDTO project;
	private OutputAttachmentDTO outputFile;
	private String taskName;
	private String beforeOriginName;
	private String afterOriginName;
	
	


}
