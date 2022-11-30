package com.modac.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.modac.campReview.model.vo.CampReview;
import com.modac.circle.model.vo.Circle;
import com.modac.common.JDBCTemplate;
import com.modac.common.model.vo.PageInfo;
import com.modac.member.model.vo.Member;
import com.modac.recipe.model.vo.Recipe;
import com.modac.usedProduct.model.vo.Market;

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
	
	/***
	 * 마이페이지에서 회원정보 수정
	 * 
	 */
	public int updateMember(Member m, Connection conn) {
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			
			/*
			 * UPDATE MEMBER SET
			 * USER_NAME=?,
			 * PHONE = ?,
			 * EMAIL=?...
			 * WHERE USER_ID=?
			 * 
			 */
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, m.getMemberName());
			
			psmt.setString(2, m.getMemberNic());
			psmt.setString(3, m.getEmail());
			
			psmt.setString(4, m.getMemberId());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(psmt);
		}
		return result;
		
	}
	
	/***
	 * 마이페이지에서 비밀번호 변경
	 * 
	 */
	public int updatePwdMember(String memberId, String memberPwd, String updatePwd, Connection conn) {
		int result = 0;
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, updatePwd);
			psmt.setString(2, memberId);
			psmt.setString(3, memberPwd);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			JDBCTemplate.close(psmt);
		}
		return result;
	}
	
	/***
	 * 마이페이지에서 회원탈퇴
	 */
	public int deleteMember(String memberId, String memberPwd, Connection conn) {
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, memberId);
			psmt.setString(2, memberPwd);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(psmt);
		}
		
		return result;
	}
	
	/***
	 * 
	 * 자기글 - 동아리 게시판
	 */

	public ArrayList<Circle> selectList(Connection conn, PageInfo pi,String memberId){
		
		//select문 => ResultSet
		
		ArrayList<Circle> list = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		
		String sql =  prop.getProperty("selectList");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			/*
			 * 
			 * boardLimit 이 10이라고 가정.
			 * 
			 * currentPage = 1 => 시작값1,   끝값 10
			 * currentPage = 2 => 시작값 11, 끝값 20
			 * currentPage = 3 => 시작값 21, 끝값 30
			 * 
			 * 
			 * 시작값 = (currentPage -1) * boardLimit +1;
			 * 끝값 = 시작값 + boardLimit -1;
			 * 
			 * 
			 */
			
			int startRow = (pi.getCurrentPage()-1)* pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit()-1;
			
			
			psmt.setString(1, memberId);
			psmt.setInt(2, startRow);
			psmt.setInt(3, endRow);
			
			
			rset = psmt.executeQuery();
			System.out.println(rset);
			while(rset.next()) {
				list.add(new Circle(rset.getString("POST_NO"),
						  
						  rset.getString("POST_TITLE"),
						  rset.getString("MEMBER_NIC"),
						  rset.getDate("CREATE_DATE"),
						  rset.getInt("READ_COUNT")));
			}
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
			
		}
		return list;
		
	}

	/***
	 * 자기글 - 동아리 게시판 - 페이징 처리
	 * @return
	 */
	public int selectListCount(Connection conn ) {
		// select문 -> Result객체
		int listCount = 0;
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			psmt = conn.prepareStatement(sql);
		
			
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("READ_COUNT");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		
		return listCount;
		
		
		
	}
	/***
	 * 
	 * 자기글 - 캠프리뷰 게시판
	 */
	public ArrayList<CampReview> crselectList(Connection conn, PageInfo pi,String memberId){
		
		//select문 => ResultSet
		
		ArrayList<CampReview> list = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		
		String sql =  prop.getProperty("crselectList");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			/*
			 * 
			 * boardLimit 이 10이라고 가정.
			 * 
			 * currentPage = 1 => 시작값1,   끝값 10
			 * currentPage = 2 => 시작값 11, 끝값 20
			 * currentPage = 3 => 시작값 21, 끝값 30
			 * 
			 * 
			 * 시작값 = (currentPage -1) * boardLimit +1;
			 * 끝값 = 시작값 + boardLimit -1;
			 * 
			 * 
			 */
			
			int startRow = (pi.getCurrentPage()-1)* pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit()-1;
			
			
			psmt.setString(1, memberId);
			psmt.setInt(2, startRow);
			psmt.setInt(3, endRow);
			
			
			rset = psmt.executeQuery();
			System.out.println(rset);
			while(rset.next()) {
				list.add(new CampReview(rset.getString("POST_NO"),
						  
						  rset.getString("POST_TITLE"),
						  rset.getString("MEMBER_NIC"),
						  rset.getDate("CREATE_DATE"),
						  rset.getInt("READ_COUNT")));
			}
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
			
		}
		return list;
		
	}
	/***
	 * 
	 * 자기글 - 캠프리뷰 게시판 - 페이징 처리
	 */
	public int crselectListCount(Connection conn ) {
		// select문 -> Result객체
		int listCount = 0;
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("crselectListCount");
		
		try {
			psmt = conn.prepareStatement(sql);
		
			
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("READ_COUNT");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		
		return listCount;
		
		
		
	}

	/***
	 * 
	 * 자기글 - 중고게시판
	 */

	public ArrayList<Market> upselectList(Connection conn, PageInfo pi,String memberId){
		
		//select문 => ResultSet
		
		ArrayList<Market> list = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		
		String sql =  prop.getProperty("upselectList");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			/*
			 * 
			 * boardLimit 이 10이라고 가정.
			 * 
			 * currentPage = 1 => 시작값1,   끝값 10
			 * currentPage = 2 => 시작값 11, 끝값 20
			 * currentPage = 3 => 시작값 21, 끝값 30
			 * 
			 * 
			 * 시작값 = (currentPage -1) * boardLimit +1;
			 * 끝값 = 시작값 + boardLimit -1;
			 * 
			 * 
			 */
			
			int startRow = (pi.getCurrentPage()-1)* pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit()-1;
			
			
			psmt.setString(1, memberId);
			psmt.setInt(2, startRow);
			psmt.setInt(3, endRow);
			
			
			rset = psmt.executeQuery();
			System.out.println(rset);
			while(rset.next()) {
				list.add(new Market(rset.getString("POST_NO"),
						  
						  rset.getString("POST_TITLE"),
						  rset.getString("MEMBER_NIC"),
						  rset.getDate("CREATE_DATE"),
						  rset.getInt("READ_COUNT")));
			}
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
			
		}
		return list;
		
	}

	/***
	 * 자기글 - 중고게시판 - 페이징 처리
	 * 
	 */

	public int upselectListCount(Connection conn ) {
		// select문 -> Result객체
		int listCount = 0;
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("upselectListCount");
		
		try {
			psmt = conn.prepareStatement(sql);
		
			
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("READ_COUNT");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		
		return listCount;
		
		
		
	}


	/***
	 * 
	 * 자기글 - 레시피 게시판
	 */
	public ArrayList<Recipe> cpselectList(Connection conn, PageInfo pi,String memberId){
		
		//select문 => ResultSet
		
		ArrayList<Recipe> list = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		
		String sql =  prop.getProperty("cpselectList");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			/*
			 * 
			 * boardLimit 이 10이라고 가정.
			 * 
			 * currentPage = 1 => 시작값1,   끝값 10
			 * currentPage = 2 => 시작값 11, 끝값 20
			 * currentPage = 3 => 시작값 21, 끝값 30
			 * 
			 * 
			 * 시작값 = (currentPage -1) * boardLimit +1;
			 * 끝값 = 시작값 + boardLimit -1;
			 * 
			 * 
			 */
			
			int startRow = (pi.getCurrentPage()-1)* pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit()-1;
			
			
			psmt.setString(1, memberId);
			psmt.setInt(2, startRow);
			psmt.setInt(3, endRow);
			
			
			rset = psmt.executeQuery();
			System.out.println(rset);
			while(rset.next()) {
				list.add(new Recipe(rset.getString("POST_NO"),
						  
						  rset.getString("POST_TITLE"),
						  rset.getString("MEMBER_NIC"),
						  rset.getDate("CREATE_DATE"),
						  rset.getInt("READ_COUNT")))
						  ;
			}
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
			
		}
		return list;
		
	}

	/***
	 * 
	 * 자기글 - 레시피 게시판- 페이징 처리
	 */

	public int cpselectListCount(Connection conn ) {
		// select문 -> Result객체
		int listCount = 0;
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("cpselectListCount");
		
		try {
			psmt = conn.prepareStatement(sql);
		
			
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("READ_COUNT");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		
		return listCount;
		
		
		
	}
}
