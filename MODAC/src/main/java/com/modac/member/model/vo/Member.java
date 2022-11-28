package com.modac.member.model.vo;

import java.util.Date;

public class Member {
	private String memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberNic;
	private String status;
	private Date inDate;
	private Date modiDate;
	private int memberLevel;
	private String email;
	
	
	public Member() {
		
	}
	
	
	public Member(String memberId, String memberPwd, String memberName, String memberNic, String email) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberNic = memberNic;
		this.email = email;
	}
	
	
	// 아이디 찾기
	public Member(String memberName, String email) {
		super();
		this.memberName = memberName;
		this.email = email;
	}

	
	
	
	// 비밀번호 찾기
	public Member(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	
	
	
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNic() {
		return memberNic;
	}
	public void setMemberNic(String memberNic) {
		this.memberNic = memberNic;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getModiDate() {
		return modiDate;
	}
	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", memberNic=" + memberNic + ", status=" + status + ", inDate=" + inDate + ", modiDate="
				+ modiDate + ", memberLevel=" + memberLevel + ", email=" + email + "]";
	}
	public Member(String memberNo, String memberId, String memberPwd, String memberName, String memberNic,
			String status, Date inDate, Date modiDate, int memberLevel, String email) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberNic = memberNic;
		this.status = status;
		this.inDate = inDate;
		this.modiDate = modiDate;
		this.memberLevel = memberLevel;
		this.email = email;
	}
	
	
	
	
}

