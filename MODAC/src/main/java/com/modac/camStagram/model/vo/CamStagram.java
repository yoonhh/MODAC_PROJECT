package com.modac.camStagram.model.vo;

import java.util.Date;

public class CamStagram {
	
	private String boardNo;
	private String postNo;
	private String postContent;
	private String memberNo;
	private Date createDate;
	private int readCount;
	private String status;
	private String memberNic;
	private String titleImg;
	private String likeCount;
	private String replyCount;
	
	
	
	public CamStagram() {
		super();
	}
	
	public CamStagram(String boardNo, String postNo, String postContent, String memberNo, Date createDate,
			int readCount, String status, String memberNic, String titleImg, String likeCount, String replyCount) {
		super();
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.postContent = postContent;
		this.memberNo = memberNo;
		this.createDate = createDate;
		this.readCount = readCount;
		this.status = status;
		this.memberNic = memberNic;
		this.titleImg = titleImg;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
	}
	
	
	//베스트
	

	public CamStagram(String postNo, String postContent, Date createDate, String memberNic, 
			String titleImg, String likeCount, String replyCount) {
		super();
		this.postNo = postNo;
		this.postContent = postContent;
		this.createDate = createDate;
		this.memberNic = memberNic;
		this.titleImg = titleImg;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
	}
	
	public CamStagram( String titleImg, String postNo, String postContent, String memberNic, Date createDate) {
		super();
		this.titleImg = titleImg;
		this.postNo = postNo;
		this.postContent = postContent;		
		this.memberNic = memberNic;
		this.createDate = createDate;
	}

	// 목록
	public CamStagram(String titleImg, String postNo, String postContent, String memberNo, String memberNic, 
			Date createDate, String likeCount, String replyCount) {
		super();

		this.titleImg = titleImg;
		this.postNo = postNo;
		this.postContent = postContent;
		this.memberNo = memberNo;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
	}
	
	// 디테일 
	public CamStagram(String titleImg, String postNo, String postContent, String memberNic, Date createDate, 
			String likeCount, String replyCount) {
		super();

		this.titleImg = titleImg;
		this.postNo = postNo;
		this.postContent = postContent;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
	}
	
	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemberNic() {
		return memberNic;
	}

	public void setMemberNic(String memberNic) {
		this.memberNic = memberNic;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}

	public String getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(String replyCount) {
		this.replyCount = replyCount;
	}

	@Override
	public String toString() {
		return "CamStargram [boardNo=" + boardNo + ", postNo=" + postNo + ", postContent=" + postContent + ", memberNo="
				+ memberNo + ", createDate=" + createDate + ", readCount=" + readCount + ", status=" + status
				+ ", memberNic=" + memberNic + ", titleImg=" + titleImg + ", likeCount=" + likeCount + ", replyCount="
				+ replyCount + "]";
	}


	
	

}
