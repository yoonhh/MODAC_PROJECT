package com.modac.member.model.service;

import java.sql.Connection;

import static com.modac.common.JDBCTemplate.*;
import com.modac.member.model.dao.MemberDao;
import com.modac.member.model.vo.Member;

public class MemberService {
	/**
	 * 로그인 요청 서비스
	 * @param memberId => 사용자가 입력했던 아이디값.
	 * @param memberPwd => 사용자가 입력했던 비밀번호값.
	 * 
	 */
	public Member loginMember(String memberId , String memberPwd) {
		
		Connection conn = getConnection();
		Member m = new MemberDao().loginMember(memberId , memberPwd , conn);
		
		close();
		
		return m;
	}
	/**
	 * 회원 가입용 서비스
	 * @param m => 회원가입할 회원의 정보를 담은 Member객체
	 * @return => 처리된 행수 (int)
	 */
	public int insertMember(Member m) {
	    
	    Connection conn = getConnection();
	    
	    int result = new MemberDao().insertMember(m , conn);
	    
	    if(result > 0) {
	        commit(conn);
	    }else {
	        rollback(conn);
	    }
	    close();
	    
	    return result;
	}
	
	
	
	/**
	 * 
	 * @param checkId => 회원가입 아이디 체크
	 * @return
	 */
	public int idcheck(String checkId) {
	    Connection conn = getConnection();
	    int count = new MemberDao().idcheck(conn, checkId);
	    close();
	    return count;
	}
	
	
	
	
	
	
	/**
	 * 아이디 찾기
	 * @param memberName
	 * @param email
	 * @return
	 */
	public Member fineId(String memberName, String email) {
		Connection conn = getConnection();
		
		Member m = new MemberDao().fineId(memberName, email, conn);
		close();
		
		return m;
		
	}
	
	
	
	
	/**
	 * 비밀번호찾기
	 * @param memberId
	 * @param memberName
	 * @param email
	 * @return
	 */
	public Member findPwd(String memberId, String memberName, String email) {
		
		Connection conn = getConnection();
		
		Member m = new MemberDao().findPwd(memberId, memberName, email, conn);
		
		close();
		
		return m;
	}
	
	
	/**
	 * 비밀번호찾고변경
	 */
	public Member fineupdatePwd(String memberId, String memberName, String email, String updatePwd) {
		Connection conn = getConnection();
		
		Member updateMem = null;
		
		int result = 0;
		
		result = new MemberDao().fineUpdatePwd(memberId, memberName, email, updatePwd,conn);
		System.out.println("result : "+result);
		if(result > 0){
			commit(conn);
			updateMem = new MemberDao().selectMember(memberId,conn);
			System.out.println("updateMem Service : " + updateMem);
		}else {
			rollback(conn);
		}
		close();
		return updateMem;
	}
	
	
	
	/**
	* 이메일 중복 체크
	* @param checkEamil
	* @return
	*/
	public int emailCheck(String checkemail) {
		Connection conn = getConnection();
		System.out.println(checkemail);
		int result = new MemberDao().emailCheck(conn, checkemail);
		System.out.println(result);
		close();
		return result;
	}
	
	
	
}
