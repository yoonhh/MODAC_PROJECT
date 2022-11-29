package com.modac.recipe.model.vo;

import java.sql.Date;

public class Recipe {
	
	private String boardNo;
	private String postNo;
	private String postTitle;
	private String postContent;
	private String memberNo;
	private Date createDate;
	private int readCount;
	private String time;
	private String difficulty;
	private String mainIngre;
	private String subIngre;
	private String status;
	private String memberNic;
	
	private String titleImg; // 썸네일 파일명 

	public Recipe() {
		super();
	}

	public Recipe(String boardNo, String postNo, String postTitle, String postContent, String memberNo, Date createDate,
			int readCount, String time, String difficulty, String mainIngre, String subIngre, String status,
			String titleImg, String memberNic) {
		super();
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.memberNo = memberNo;
		this.createDate = createDate;
		this.readCount = readCount;
		this.time = time;
		this.difficulty = difficulty;
		this.mainIngre = mainIngre;
		this.subIngre = subIngre;
		this.status = status;
		this.titleImg = titleImg;
		this.memberNic = memberNic;
	}
	
	//목록
	public Recipe(String titleImg, String postNo, String postTitle, String memberNic, Date createDate, String time, String difficulty, String mainIngre) {
		super();
		this.titleImg = titleImg;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.time = time;
		this.difficulty = difficulty;
		this.mainIngre = mainIngre;
	}

	//디테일
	public Recipe(String postNo, String postTitle, String postContent, String memberNic, Date createDate, String time,
			String difficulty, String mainIngre, String subIngre, String titleImg) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.time = time;
		this.difficulty = difficulty;
		this.mainIngre = mainIngre;
		this.subIngre = subIngre;
		this.titleImg = titleImg;
	}
	
	public Recipe(String postNo, String postTitle,String memberNic ,Date createDate, int readCount) {
	      super();
	      this.postNo = postNo;
	      this.postTitle = postTitle;
	      this.createDate = createDate;
	      this.memberNic = memberNic;
	      this.readCount=readCount;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getMainIngre() {
		return mainIngre;
	}

	public void setMainIngre(String mainIngre) {
		this.mainIngre = mainIngre;
	}

	public String getSubIngre() {
		return subIngre;
	}

	public void setSubIngre(String subIngre) {
		this.subIngre = subIngre;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getMemberNic() {
		return memberNic;
	}

	public void setMemberNic(String memberNic) {
		this.memberNic = memberNic;
	}

	@Override
	public String toString() {
		return "Recipe [boardNo=" + boardNo + ", postNo=" + postNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", memberNo=" + memberNo + ", createDate=" + createDate + ", readCount=" + readCount
				+ ", time=" + time + ", difficulty=" + difficulty + ", mainIngre=" + mainIngre + ", subIngre="
				+ subIngre + ", status=" + status + ", memberNic=" + memberNic + ", titleImg=" + titleImg + "]";
	}


	
	

}
