package com.modac.campReview.model.vo;

public class ReviewTag {
	
	private String postNo;
	private String tagNo;
	
	public ReviewTag(){
		super();
	}

	public ReviewTag(String postNo, String tagNo) {
		super();
		this.postNo = postNo;
		this.tagNo = tagNo;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getTagNo() {
		return tagNo;
	}

	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}
	
	
	
	
	
}
