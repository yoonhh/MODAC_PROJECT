package com.modac.QA.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.modac.QA.model.vo.Qa;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;
import com.modac.notice.model.vo.Notice;

import static com.modac.common.JDBCTemplate.*;

public class QaDao {
	
	private Properties prop = new Properties();
	
	public QaDao() {
		String fileName = QaDao.class.getResource("/sql/QA/qa-mapper.xml").getPath();
	
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Qa> selectQaList(Connection conn, PageInfo pi, String field, String query){
		
		ArrayList<Qa> qList = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQaList");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
			psmt.setInt(2,  startRow);
			psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				qList.add(new Qa(rset.getString("POST_NO"),
								 rset.getString("POST_TITLE"),
								 rset.getString("HIDDEN_POST"),
								 rset.getString("MEMBER_NIC"),
								 rset.getDate("CREATE_DATE"),
								 rset.getInt("READ_COUNT")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		System.out.println("qList"+qList);
		return qList;
	}

	
	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		 PreparedStatement psmt = null;
		 ResultSet rset = null;
		 String sql = prop.getProperty("selectListCount");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			rset = psmt. executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		 return listCount;
		 
	}
	
	public int increaseCount(String qaNo, Connection conn) {
		
		// update문 -> 처리된 행의 갯수를 반환
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, qaNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
		
	}
	
	public Qa selectQa(String qaNo, Connection conn) {
		
		// select문은 반환형이 ResultSet
		
		ResultSet rset = null;
		
		Qa q = null;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("selectQa");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, qaNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				q = new Qa(	   rset.getString("POST_NO"),
							   rset.getString("POST_TITLE"),
							   rset.getString("POST_CONTENT"),
							   rset.getString("MEMBER_NIC"),
							   rset.getDate("CREATE_DATE"),
							   rset.getString("HIDDEN_POST"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return q;
	}
	
	public int checkPwd(Qa q, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("checkPwd");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, q.getQaNo());
			psmt.setString(2, q.getPostPwd());
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		
		return result;
		
	}
	
	public ArrayList<Attachment> selectAttachmentList(Connection conn, String qno) {
		
		ArrayList<Attachment> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, qno);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				
				at.setPhotoNo(rset.getString("PHOTO_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setNewName(rset.getString("NEW_NAME"));
				at.setPath(rset.getString("PATH"));
				at.setFileLevel(rset.getInt("FILE_LEVEL"));
				
				list.add(at);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	
	
	public int insertQa(Qa q, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		String sql;
		if(q.getHiddenPost() != null) {
			sql = prop.getProperty("insertHiddenQa");
		}else {
			sql = prop.getProperty("insertQa");
		}
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, q.getQaTitle());
			psmt.setString(2, q.getQaContent());
			psmt.setString(3, q.getMemberNo());
			
			if(q.getHiddenPost() != null) {
				psmt.setString(4, q.getHiddenPost());
				psmt.setString(5, q.getPostPwd());
			}
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
		
	}

	public int insertAttachmentList(ArrayList<Attachment> list, Connection conn) {
		int result = 1;

		int result2 = 1;

		PreparedStatement psmt = null;

		String sql = prop.getProperty("insertAttachmentList");

		try {
			psmt = conn.prepareStatement(sql);
			for (Attachment at : list) {

				psmt.setString(1, at.getOriginName());
				psmt.setString(2, at.getNewName());
				psmt.setString(3, at.getPath());
				psmt.setInt(4, at.getFileLevel());

				// 실행
				result2 = psmt.executeUpdate();
				if (result2 == 0) {
					result = 0;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}

		return result;
	}
	
	
	public int updateQa(Qa q, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("updateQa");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, q.getQaTitle());
			psmt.setString(2, q.getQaContent());
			psmt.setString(3, q.getQaNo());
			
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
	}

	public int insertNewAttachment(ArrayList<Attachment> list, Connection conn) {

		int result = 0;

		PreparedStatement psmt = null;

		String sql = prop.getProperty("insertNewAttachment");

		try {
			psmt = conn.prepareStatement(sql);

			for (Attachment at : list) {
				psmt.setString(1, at.getPostNo());
				psmt.setString(2, at.getOriginName());
				psmt.setString(3, at.getNewName());
				psmt.setString(4, at.getPath());
				psmt.setInt(5, at.getFileLevel());

				result = psmt.executeUpdate();

				if (result == 0) {
					result = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		System.out.println("result : " + result);

		return result;
	}
	
	public int insertNewAttachment(Attachment at, Connection conn) {

		int result = 0;

		PreparedStatement psmt = null;

		String sql = prop.getProperty("insertNewAttachment");

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, at.getPostNo());
			psmt.setString(2, at.getOriginName());
			psmt.setString(3, at.getNewName());
			psmt.setString(4, at.getPath());
			psmt.setInt(5, at.getFileLevel());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		System.out.println("result1 : " + result);

		return result;
	}
	
	
	public int updateAttachment(ArrayList<Attachment> list, Connection conn) {

		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("updateAttachment");

		try {
			psmt = conn.prepareStatement(sql);

			for (Attachment at : list) {

				psmt.setString(1, at.getOriginName());
				psmt.setString(2, at.getNewName());
				psmt.setString(3, at.getPath());
				psmt.setInt(4, at.getFileLevel());
				psmt.setString(5, at.getPhotoNo());

				result = psmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		System.out.println("result2: " + result);

		return result;
	}
	
	public int updateAttachment(Attachment at, Connection conn) {

		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("updateAttachment");

		try {
			psmt = conn.prepareStatement(sql);


				psmt.setString(1, at.getOriginName());
				psmt.setString(2, at.getNewName());
				psmt.setString(3, at.getPath());
				psmt.setInt(4, at.getFileLevel());
				psmt.setString(5, at.getPhotoNo());

				result = psmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		System.out.println("result3 : " + result);

		return result;
	}
	
	
	
	public int deleteQa(String qaNo, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("deleteQa");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, qaNo);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
	}
	
	
	public ArrayList<Reply> selectReplyList(Connection conn, int postNo ){
		ArrayList<Reply> list =  new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReplyList");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,  postNo);
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Reply(
						
						rset.getString(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4)
						
						));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		
		return list;
		
	}
	
	
	public int insertReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertReply");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, r.getReplyContent());
			psmt.setString(2, r.getPostNo());
			psmt.setString(3,r.getWriter());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(psmt);
		}
		System.out.println("result : "+result);
		return result;
		
		
	}
	
}
