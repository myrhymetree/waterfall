package com.greedy.waterfall.output.model.dto;

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
public class OutputAttachmentDTO {
	
	private int fileNo;
	private String filePath;
	private String originName;
	private String randomName;
	private String status;
	private java.sql.Date registedDate;
	private int outputNo;

}
