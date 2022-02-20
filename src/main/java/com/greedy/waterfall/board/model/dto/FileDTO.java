package com.greedy.waterfall.board.model.dto;

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
@ToString
@Builder
public class FileDTO {

	private int fileNo;
	private String filePath;
	private String fileOriginName;
	private String fileRandomName;
	private String fileStatus;
	private int refIssueNo;
	private int refTaskNo;
	private int refBoardNo;
	private int fileCategoryNo;
	

}