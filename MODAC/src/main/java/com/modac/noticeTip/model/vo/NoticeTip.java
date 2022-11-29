package com.modac.noticeTip.model.vo;

import java.sql.Date;

import com.modac.common.model.vo.Attachment;

public class NoticeTip {

	private String boardNo;
	private String postNo;
	private String postTitle;
	private String postContent;
	private String writer;
	private String url;
	private Date createDate;
	private String status;
	private Attachment at;
	

	public NoticeTip() {
		super();
	}

	public NoticeTip(String boardNo, String postNo, String postTitle, String postContent, String writer, String url,
			Date createDate, String status) {
		super();
		this.boardNo = boardNo;
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.writer = writer;
		this.url = url;
		this.createDate = createDate;
		this.status = status;
	}
	
	

	public NoticeTip(String postNo, String postTitle, String postContent,  String url) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.url = url;
	}

	public NoticeTip(String postNo, String postTitle, String postContent, String url, Date createDate) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.url = url;
		this.createDate = createDate;
	}
	
	

	public NoticeTip(String postNo, String url) {
		super();
		this.postNo = postNo;
		this.url = url;
	}

	public Attachment getAt() {
		return at;
	}
	
	public void setAt(Attachment at) {
		this.at = at;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		return "NoticeTip [boardNo=" + boardNo + ", postNo=" + postNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", writer=" + writer + ", url=" + url + ", createDate=" + createDate + ", status="
				+ status + "]";
	}
	
	
}
