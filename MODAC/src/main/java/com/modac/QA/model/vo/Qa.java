package com.modac.QA.model.vo;

import java.sql.Date;

public class Qa {

	private String boardNo;
	private String memberNo;
	private String qaNo;
	private String qaTitle;
	private String qaContent;
	private String memberNic;
	private Date createDate;
	private int count;
	private String status;
	private String hiddenPost;
	private String postPwd;
	
	public Qa() {
		super();
	}
	
	public Qa(String boardNo, String memberNo, String qaNo, String qaTitle, String qaContent, String memberNic,
			Date createDate, int count, String status, String hiddenPost, String postPwd) {
		super();
		this.boardNo = boardNo;
		this.memberNo = memberNo;
		this.qaNo = qaNo;
		this.qaTitle = qaTitle;
		this.qaContent = qaContent;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.count = count;
		this.status = status;
		this.hiddenPost = hiddenPost;
		this.postPwd = postPwd;
	}

	// 리스트
	public Qa(String qaNo, String qaTitle, String hiddenPost, String memberNic, Date createDate, int count) {
		super();
		this.qaNo = qaNo;
		this.qaTitle = qaTitle;
		this.hiddenPost = hiddenPost;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.count = count;
	}

	// 디테일
	public Qa(String qaNo, String qaTitle, String qaContent, String memberNic, Date createDate, String hiddenPost) {
		super();
		this.qaNo = qaNo;
		this.qaTitle = qaTitle;
		this.qaContent = qaContent;
		this.memberNic = memberNic;
		this.createDate = createDate;
		this.hiddenPost = hiddenPost;
	}
	

	public Qa(String qaNo, String postPwd) {
		super();
		this.qaNo = qaNo;
		this.postPwd = postPwd;
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

	public String getQaNo() {
		return qaNo;
	}

	public void setQaNo(String qaNo) {
		this.qaNo = qaNo;
	}

	public String getQaTitle() {
		return qaTitle;
	}

	public void setQaTitle(String qaTitle) {
		this.qaTitle = qaTitle;
	}

	public String getQaContent() {
		return qaContent;
	}

	public void setQaContent(String qaContent) {
		this.qaContent = qaContent;
	}

	public String getMemberNic() {
		return memberNic;
	}

	public void setMemberNic(String memberNic) {
		this.memberNic = memberNic;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHiddenPost() {
		return hiddenPost;
	}

	public void setHiddenPost(String hiddenPost) {
		this.hiddenPost = hiddenPost;
	}

	public String getPostPwd() {
		return postPwd;
	}

	public void setPostPwd(String postPwd) {
		this.postPwd = postPwd;
	}

	@Override
	public String toString() {
		return "Qa [boardNo=" + boardNo + ", memberNo=" + memberNo + ", qaNo=" + qaNo + ", qaTitle=" + qaTitle
				+ ", qaContent=" + qaContent + ", memberNic=" + memberNic + ", createDate=" + createDate + ", count="
				+ count + ", status=" + status + ", hiddenPost=" + hiddenPost + ", postPwd=" + postPwd + "]";
	}
	
	
}
