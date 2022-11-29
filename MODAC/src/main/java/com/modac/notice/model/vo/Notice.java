package com.modac.notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private String noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private int count;
	private Date createDate;
	private String status;
	private int noticeCategory;
	
	public Notice() {
		super();
	}

	public Notice(String noticeNo, int noticeCategory, String noticeTitle, String noticeContent, String noticeWriter, int count,
			Date createDate, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeCategory = noticeCategory;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
		
	}
	
	public Notice(String noticeNo, int noticeCategory, String noticeTitle, String noticeWriter, Date createDate, int count) {
		super();
		this.noticeNo = noticeNo;
		this.noticeCategory = noticeCategory;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.createDate = createDate;
		this.count = count;
		
	}
	
	public Notice(String noticeNo, int noticeCategory, String noticeTitle, String noticeContent, String noticeWriter, Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeCategory = noticeCategory;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.createDate = createDate;
	}
	
	public int getNoticeCategory() {
		return noticeCategory;
	}
	
	public void setNoticeCategory(int noticeCategory) {
		this.noticeCategory = noticeCategory;
	}
	
	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeWriter=" + noticeWriter + ", count=" + count + ", createDate=" + createDate + ", status="
				+ status + ", noticeCategory=" + noticeCategory +"]";
	}
	
	
	
}























