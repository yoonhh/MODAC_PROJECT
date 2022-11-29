package com.modac.report.model.vo;

import java.sql.Date;

public class report {
	private String reportNo;
	
	private String postNo;
	private String boardNo;
	private String reportTitle;
	private String reportContent;
	private String senderNo;
	
	private Date createDate;
	
	private int count;
	
	
	
	public report(String reportNo, String postNo, String boardNo, String reportTitle, String reportContent,
			String senderNo, Date createDate, int count) {
		super();
		this.reportNo = reportNo;
		this.postNo = postNo;
		this.boardNo = boardNo;
		this.reportTitle = reportTitle;
		this.reportContent = reportContent;
		this.senderNo = senderNo;
		this.createDate = createDate;
		this.count=count;
	}


	public report() {
		super();
	}
	
	
	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getSenderNo() {
		return senderNo;
	}
	public void setSenderNo(String senderNo) {
		this.senderNo = senderNo;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
