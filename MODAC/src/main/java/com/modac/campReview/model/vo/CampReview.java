package com.modac.campReview.model.vo;

import java.sql.Date;
import java.util.List;

public class CampReview {
	
	private String boardNo;
	private String postNo;
	private String postTitle;
	private String postContent;
	private String memberNo;
	private Date createDate;
	private int readCount;
	private List<Integer> tagList;
	private String status;
	private String tagName;
	private String memberNic;
	private String titleImg;
	
	public CampReview() {
		super();
	}

	public CampReview(String boardNo, String postNo, String postTitle, String postContent, String memberNo,
			Date createDate, int readCount,List<Integer> tagList, String status, String tagName, String memberNic) {
		super();
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.memberNo = memberNo;
		this.createDate = createDate;
		this.readCount = readCount;
		this.tagList = tagList;
		this.status = status;
		this.tagName = tagName;
		this.memberNic = memberNic;
	}
	
	//목록
	public CampReview(String postNo, String postTitle, String memberNic,
			Date createDate, int readCount) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.readCount = readCount;
	}
	
	// 디테일 
	public CampReview(String postNo, String postTitle, String postContent, String memberNic,
			Date createDate, String titleImg) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.titleImg = titleImg;
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

	
	public List<Integer> getTagList() {
		return tagList;
	}

	public void setTagList(List<Integer> tagList) {
		this.tagList = tagList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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

	

	@Override
	public String toString() {
		return "CampReview [boardNo=" + boardNo + ", postNo=" + postNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", memberNo=" + memberNo + ", createDate=" + createDate + ", readCount=" + readCount
				+ ", tagList=" + tagList + ", status=" + status + ", tagName=" + tagName + ", memberNic=" + memberNic + "]";
	}



	
	

}
