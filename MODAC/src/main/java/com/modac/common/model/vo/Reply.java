package com.modac.common.model.vo;


public class Reply {
	
	private String replyNo;
	private String replyContent;
	private String boardNo;
	private String createDate;
	private String hiddenReply;
	private String status;
	private String writer;
	private String postNo;
	
	public Reply() {
		super();
	}
	

	public Reply(String replyNo, String replyContent, String writer,String createDate ) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.writer = writer;
	}


	public Reply(String replyNo, String replyContent, String boardNo, String createDate, String hiddenReply,
			String status, String writer, String postNo) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.boardNo = boardNo;
		this.createDate = createDate;
		this.hiddenReply = hiddenReply;
		this.status = status;
		this.writer = writer;
		this.postNo = postNo;
	}
	
	

	public String getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getHiddenReply() {
		return hiddenReply;
	}

	public void setHiddenReply(String hiddenReply) {
		this.hiddenReply = hiddenReply;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", boardNo=" + boardNo + ", createDate="
				+ createDate + ", hiddenReply=" + hiddenReply + ", status=" + status + ", writer=" + writer
				+ ", postNo=" + postNo + "]";
	}
	
	
	

}
