package com.greedy.waterfall.board.model.noticedto;

public class NoticeAttachmentDTO {
	
	private int fileNo;
	private String filePath;
	private String originName;
	private String randomName;
	private String status;
	private int noticeNo;
	
	public NoticeAttachmentDTO() {}

	public NoticeAttachmentDTO(int fileNo, String filePath, String originName, String randomName, String status,
			int noticeNo) {
		super();
		this.fileNo = fileNo;
		this.filePath = filePath;
		this.originName = originName;
		this.randomName = randomName;
		this.status = status;
		this.noticeNo = noticeNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getRandomName() {
		return randomName;
	}

	public void setRandomName(String randomName) {
		this.randomName = randomName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	@Override
	public String toString() {
		return "NoticeAttachmentDTO [fileNo=" + fileNo + ", filePath=" + filePath + ", originName=" + originName
				+ ", randomName=" + randomName + ", status=" + status + ", noticeNo=" + noticeNo + "]";
	}
	
	

}
