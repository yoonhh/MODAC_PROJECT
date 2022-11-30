package com.modac.circle.model.vo;

import java.util.Date;

public class Circle {
	
	private String postNo;
	private String postTitle;
	private String postContent;
	private Date createDate;
	private String status;
	private String boardNo;
	private String memberNo;
	private int readCount;
	private String memberNic;
	
	public Circle() {
		super();
	}
	
	public Circle(String postNo, String postTitle, String postContent, Date createDate, String status, String boardNo,
			String memberNo, int readCount, String memberNic) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createDate = createDate;
		this.status = status;
		this.boardNo = boardNo;
		this.memberNo = memberNo;
		this.readCount = readCount;
		this.memberNic=memberNic;
	}

	public Circle(String postNo, String postTitle,  String memberNic,Date createDate,
			int readCount) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.createDate = createDate;
		this.memberNic = memberNic;
		this.readCount = readCount;
	}

	public Circle(String postNo, String postTitle, String postContent, Date createDate, String memberNic) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createDate = createDate;
		this.memberNic = memberNic;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
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

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getMemberNic() {
		return memberNic;
	}

	public void setMemberNic(String memberNic) {
		this.memberNic = memberNic;
	}

	@Override
	public String toString() {
		return "Circle [postNo=" + postNo + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", createDate=" + createDate + ", status=" + status + ", boardNo=" + boardNo + ", memberNo="
				+ memberNo + ", readCount=" + readCount + ", memberNic=" + memberNic + "]";
	}
	

	


}
