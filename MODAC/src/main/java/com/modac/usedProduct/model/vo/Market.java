package com.modac.usedProduct.model.vo;

import java.sql.Date;

public class Market {
	
	private String postNo;
	private String postTitle;
	private String postContent;
	private Date createDate;
	private int readCount;
	private String status;
	private String sale;
	private String memberNo;
	private String boardNo;
	private String sort;
	private String saleView;
	private String memberNic;
	
	private String titleImg;
	
	public Market() {
		super();
	}

	public Market(String postNo, String postTitle, String postContent, Date createDate, int readCount, String status,
			String sale, String memberNo, String boardNo, String titleImg, String sort, String saleView, String memberNic) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createDate = createDate;
		this.readCount = readCount;
		this.status = status;
		this.sale = sale;
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.titleImg = titleImg;
		this.sort = sort;
		this.saleView = saleView;
		this.memberNic = memberNic;
		
	}

	//게시글 목록페이지
	public Market(String postTitle, Date createDate, int readCount, String sale, String memberNic, String postNo, String titleImg) {
		super();
		this.postTitle = postTitle;
		this.createDate = createDate;
		this.readCount = readCount;
		this.sale = sale;
		this.memberNic = memberNic;
		this.postNo = postNo;
		this.titleImg = titleImg;
	}
	
	//상세페이지(게스트/작성자)
	public Market(String postTitle, String postContent, Date createDate, String sale, String memberNo, String postNo,String memberNic ) {
		super();
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.createDate = createDate;
		this.sale = sale;
		this.memberNo = memberNo;
		this.postNo = postNo;
		this.memberNic = memberNic;
	}
	
	//게시글조회 목록버전
	public Market(String postNo, String postTitle, Date createDate, int readCount, String memberNo) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.createDate = createDate;
		this.readCount = readCount;
		this.memberNo = memberNo;
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

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSaleView() {
		return saleView;
	}

	public void setSaleView(String saleView) {
		this.saleView = saleView;
	}
	
	public String getMemberNic() {
		return memberNic;
	}

	public void setMemberNic(String memberNic) {
		this.memberNic = memberNic;
	}

	@Override
	public String toString() {
		return "Market [postNo=" + postNo + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", createDate=" + createDate + ", readCount=" + readCount + ", status=" + status + ", sale=" + sale
				+ ", memberNo=" + memberNo + ", boardNo=" + boardNo + ", titleImg=" + titleImg + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
