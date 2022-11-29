package com.modac.camStagram.model.vo;

public class BoardLike {
	
	private String postNo;
	private String MemberNo;
	
	public BoardLike() {
		super();
	}

	public BoardLike(String postNo, String memberNo) {
		super();
		this.postNo = postNo;
		this.MemberNo = memberNo;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getMemberNo() {
		return MemberNo;
	}

	public void setMemberNo(String memberNo) {
		MemberNo = memberNo;
	}

	@Override
	public String toString() {
		return "BoardLike [postNo=" + postNo + ", MemberNo=" + MemberNo + "]";
	};
	
	
	
	

}
