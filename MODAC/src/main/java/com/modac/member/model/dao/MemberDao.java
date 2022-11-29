package com.modac.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.modac.common.JDBCTemplate;
import com.modac.member.model.vo.Member;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * 로그인
	 * @param memberId
	 * @param memberPwd
	 * @param conn
	 * @return
	 */
	public Member loginMember(String memberId, String memberPwd, Connection conn) {
		Member m = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, memberId);
			psmt.setString(2, memberPwd);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getString("MEMBER_NO"),
						rset.getString("MEMBER_ID"),
						rset.getString("MEMBER_PWD"),
						rset.getString("MEMBER_NAME"),
						rset.getString("MEMBER_NIC"),
						rset.getString("STATUS"),
						rset.getDate("IN_DATE"),
						rset.getDate("MODI_DATE"),
						rset.getInt("MEMBER_LEVEL"),
						rset.getString("EMAIL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}	
		return m;
	}
	/**
	 * 회원가입
	 * @param m
	 * @param conn
	 * @return
	 */
	public int insertMember(Member m , Connection conn) {
	    
	    // insert문 처리된 행의 갯수를 반환하여 result에 저장시킬것.
	    int result = 0;
	    
	    PreparedStatement psmt = null;
	    
	    String sql = prop.getProperty("insertMember");
	    try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, m.getMemberId());
			psmt.setString(2, m.getMemberPwd());
			psmt.setString(3, m.getMemberName());
			psmt.setString(4, m.getEmail());
			psmt.setString(5, m.getMemberNic());
			
			result = psmt.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(psmt);
		}
	    System.out.println("dao : " + result);
	    return result;
	}
	
	/**
	 * 아이디 체크
	 * @param conn
	 * @param checkId
	 * @return
	 */
	public int idcheck(Connection conn,String checkId) {
	    
	    int count = 0;
	    PreparedStatement psmt = null;
	    ResultSet rset = null;
	    String sql = prop.getProperty("idCheck");
	    try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, checkId);
            
            rset = psmt.executeQuery();
            
            if(rset.next()) {
                count = rset.getInt(1);
            }
            
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(psmt);
        }
	    System.out.println(count);
	    return count;
	}
	
	/**
	 * 아이디 찾기
	 * @param memberName
	 * @param email
	 * @param conn
	 * @return
	 */
	public Member fineId(String memberName,String email,Connection conn) {
		Member m = null;
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("fineId");
		
		
		try {
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1, memberName);
			psmt.setString(2, email);
			
			rset=psmt.executeQuery();
			
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberName(rset.getString("MEMBER_NAME"));	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
			return m;
	}
	/**
	 * 비밀번호찾기
	 * @param memberId
	 * @param memberName
	 * @param email
	 * @param conn
	 * @return
	 */
	public Member findPwd(String memberId, String memberName, String email, Connection conn) {
		Member m = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findPwd");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, memberId);
			psmt.setString(2, memberName);
			psmt.setString(3, email);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getString("MEMBER_PWD"));
			}
			System.out.println("dao : " + m);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	/**
	 * 비밀번호찾고 업데이트
	 * @return
	 */
	public int fineUpdatePwd(String memberId, String memberName, String email, String updatePwd, Connection conn) {
		int result = 0;
		
		PreparedStatement psmt =null;
		
		String sql = prop.getProperty("updateFinePwd");
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, updatePwd);
			psmt.setString(2, memberId);
			psmt.setString(3, memberName);
			psmt.setString(4, email);
			
			result = psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(psmt);
		}
		return result;
	}
	/**
	 * 맴버커밋
	 * @param memberId
	 * @param conn
	 * @return
	 */
	public Member selectMember(String memberId, Connection conn) {
		Member m = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, memberId);
			
			rset = psmt.executeQuery();
			
				if(rset.next()) {
					m = new Member(rset.getString("MEMBER_NO"),
							rset.getString("MEMBER_ID"),
							rset.getString("MEMBER_PWD"),
							rset.getString("MEMBER_NAME"),
							rset.getString("MEMBER_NIC"),
							rset.getString("STATUS"),
							rset.getDate("IN_DATE"),
							rset.getDate("MODI_DATE"),
							rset.getInt("MEMBER_LEVEL"),
							rset.getString("EMAIL"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		return m;
	}
	
	public int emailCheck(Connection conn, String checkemail) {
	    int count = 0;
	    PreparedStatement psmt = null;
	    ResultSet rset = null;
	    String sql = prop.getProperty("emailCheck");
	    try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, checkemail);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(psmt);
		}
	    return count;
	}
}
