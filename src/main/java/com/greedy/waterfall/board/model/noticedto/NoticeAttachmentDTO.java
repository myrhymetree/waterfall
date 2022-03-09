package com.greedy.waterfall.board.model.noticedto;

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
public class NoticeAttachmentDTO {
	
	private int fileNo;
	private String filePath;
	private String originName;
	private String randomName;
	private String status;
	private int noticeNo;
	private int categoryNo;
	

}
