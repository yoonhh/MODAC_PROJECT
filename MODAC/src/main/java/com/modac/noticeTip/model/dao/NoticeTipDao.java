package com.modac.noticeTip.model.dao;

import static com.modac.common.JDBCTemplate.close;

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
import com.modac.noticeTip.model.vo.NoticeTip;

public class NoticeTipDao {
	
	private Properties prop = new Properties();
	
	public NoticeTipDao() {
		String fileName = NoticeTipDao.class.getResource("/sql/noticeTip/noticeTip-mapper.xml").getPath();
	
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<NoticeTip> selectNoticeTipList(Connection conn,  PageInfo pi, String field, String query){
		
		ArrayList<NoticeTip> ntList = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeTipList");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
			psmt.setInt(2, startRow);
			psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				ntList.add(new NoticeTip(rset.getString("POST_NO"),
								 rset.getString("POST_TITLE"),
								 rset.getString("POST_CONTENT"),
								 rset.getString("URL"),
								 rset.getDate("CREATE_DATE")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		
		return ntList;
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
	
	
	public NoticeTip selectNoticeTip(String postNo, Connection conn) {
		
		// select문은 반환형이 ResultSet
		
		ResultSet rset = null;
		
		NoticeTip n = null;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("selectNoticeTip");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				n = new NoticeTip(rset.getString("POST_NO"),
								  rset.getString("POST_TITLE"),
								  rset.getString("POST_CONTENT"),
							   	  rset.getString("URL")
							     );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return n;
	}
	
	
	public Attachment selectAttachment(Connection conn, String postNo) {
		
		Attachment at = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, postNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				
				at.setPhotoNo(rset.getString("PHOTO_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setNewName(rset.getString("NEW_NAME"));
				at.setPath(rset.getString("PATH"));
				at.setFileLevel(rset.getInt("FILE_LEVEL"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return at;
	}
	
	public void selectAttachment(Connection conn, ArrayList<NoticeTip> qList) {
		
		Attachment at = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		try {
			
			psmt = conn.prepareStatement(sql);
			for(NoticeTip nt : qList) {
				psmt.setString(1, nt.getPostNo());
				
				rset = psmt.executeQuery();
				
				if(rset.next()) {
					at = new Attachment();
					
					at.setPhotoNo(rset.getString("PHOTO_NO"));
					at.setOriginName(rset.getString("ORIGIN_NAME"));
					at.setNewName(rset.getString("NEW_NAME"));
					at.setPath(rset.getString("PATH"));
					at.setFileLevel(rset.getInt("FILE_LEVEL"));
					
					nt.setAt(at);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
	}
	
	public int insertNoticeTip(NoticeTip nt, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		String sql = prop.getProperty("insertNoticeTip");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, nt.getPostTitle());
			psmt.setString(2, nt.getPostContent());
			psmt.setString(3, nt.getWriter());
			psmt.setString(4, nt.getUrl());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	
	public int insertAttachment(Attachment at, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, at.getOriginName());
			psmt.setString(2, at.getNewName());
			psmt.setString(3, at.getPath());
			
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	public int updateNoticeTip(NoticeTip nt, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("updateNoticeTip");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, nt.getPostTitle());
			psmt.setString(2, nt.getPostContent());
			psmt.setString(3, nt.getUrl());
			psmt.setString(4, nt.getPostNo());
			
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
	}
	
	
//	public int insertNewAttachment(Attachment at, Connection conn) {
//
//		int result = 0;
//
//		PreparedStatement psmt = null;
//
//		String sql = prop.getProperty("insertNewAttachment");
//
//		try {
//			psmt = conn.prepareStatement(sql);
//
//			psmt.setString(1, at.getPostNo());
//			psmt.setString(2, at.getOriginName());
//			psmt.setString(3, at.getNewName());
//			psmt.setString(4, at.getPath());
//
//			result = psmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(psmt);
//		}
//		System.out.println("result1 : " + result);
//
//		return result;
//	}
	
	public int updateAttachment(Attachment at, Connection conn) {

		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("updateAttachment");

		try {
			psmt = conn.prepareStatement(sql);

				psmt.setString(1, at.getOriginName());
				psmt.setString(2, at.getNewName());
				psmt.setString(3, at.getPath());
				psmt.setString(4, at.getPhotoNo());

				result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		System.out.println("result3 : " + result);

		return result;
	}
	
	
	public int deleteNoticeTip(String postNo, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("deleteNoticeTip");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
	}
}
