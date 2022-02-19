package com.greedy.waterfall.board.model.dto;

import java.sql.Date;

import com.greedy.waterfall.board.model.noticedto.NoticeAttachmentDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;

public class NoticeDTO {
	
	private int no;
	private java.sql.Date updatedDate;
	private java.sql.Date registedDate;
	private String title;
	private String content;
	private String status;
	private int projectNo;
	private int boardCategoryNo;
	private int memberNo;
	private MemberDTO memberName;
	private int count;
	private NoticeAttachmentDTO attachmentDTO;
	
	public NoticeDTO() {}

	public NoticeDTO(int no, Date updatedDate, Date registedDate, String title, String content, String status,
			int projectNo, int boardCategoryNo, int memberNo, MemberDTO memberName, int count,
			NoticeAttachmentDTO attachmentDTO) {
		super();
		this.no = no;
		this.updatedDate = updatedDate;
		this.registedDate = registedDate;
		this.title = title;
		this.content = content;
		this.status = status;
		this.projectNo = projectNo;
		this.boardCategoryNo = boardCategoryNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.count = count;
		this.attachmentDTO = attachmentDTO;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public java.sql.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.sql.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public java.sql.Date getRegistedDate() {
		return registedDate;
	}

	public void setRegistedDate(java.sql.Date registedDate) {
		this.registedDate = registedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}

	public int getBoardCategoryNo() {
		return boardCategoryNo;
	}

	public void setBoardCategoryNo(int boardCategoryNo) {
		this.boardCategoryNo = boardCategoryNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public MemberDTO getMemberName() {
		return memberName;
	}

	public void setMemberName(MemberDTO memberName) {
		this.memberName = memberName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public NoticeAttachmentDTO getAttachmentDTO() {
		return attachmentDTO;
	}

	public void setAttachmentDTO(NoticeAttachmentDTO attachmentDTO) {
		this.attachmentDTO = attachmentDTO;
	}

	@Override
	public String toString() {
		return "NoticeDTO [no=" + no + ", updatedDate=" + updatedDate + ", registedDate=" + registedDate + ", title="
				+ title + ", content=" + content + ", status=" + status + ", projectNo=" + projectNo
				+ ", boardCategoryNo=" + boardCategoryNo + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", count=" + count + ", attachmentDTO=" + attachmentDTO + "]";
	}

	

	
	
	
}
