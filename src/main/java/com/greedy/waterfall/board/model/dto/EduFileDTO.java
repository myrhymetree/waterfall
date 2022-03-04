package com.greedy.waterfall.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EduFileDTO implements java.io.Serializable {

	private int fileNo;					//파일 번호
	private int refBoardNo;				//게시글 번호
	private String savedPath;			//파일 저장 경로
	private String originalName; 		//원본 파일 이름
	private String randomName;			//랜덤 저장 파일 이름
	private String status;				//삭제여부
	private int fileCategoryNo;			//게시판 카테고리 구분 번호
}
