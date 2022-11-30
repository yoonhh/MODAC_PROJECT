package com.modac.circle.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.modac.circle.model.vo.Circle;
import com.modac.common.JDBCTemplate;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;

public class CircleBoardDao {
private Properties prop = new Properties();
	
	public CircleBoardDao() {
		try {
			prop.loadFromXML(new FileInputStream(CircleBoardDao.class.getResource("/sql/circle/cboard-mapper.xml").getPath()));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	public int insertBoard(Circle c, Connection conn) {
		int result = 0;
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			psmt=conn.prepareStatement(sql);
			
			
			psmt.setString(1, c.getPostTitle());
			psmt.setString(2, c.getPostContent());
			psmt.setString(3, c.getMemberNo());
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(psmt);
		}
		
		return result;
	}
	
public int insertAttachment(Attachment at, Connection conn) {
		
		int result = 0;
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1, at.getOriginName());
			psmt.setString(2, at.getNewName());
			psmt.setString(3, at.getPath());
			psmt.setInt(4, at.getFileLevel());
			
			
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(psmt);
		}
		
		
		return result;
	}

public int insertNewAttachment(Attachment at, Connection conn) {
	
	int result = 0;
	PreparedStatement psmt = null;
	
	String sql = prop.getProperty("insertNewAttachment");
	
	try {
		psmt=conn.prepareStatement(sql);
		
		psmt.setString(1, at.getPostNo());
		psmt.setString(2, at.getOriginName());
		psmt.setString(3, at.getNewName());
		psmt.setString(4, at.getPath());
		
		
		result = psmt.executeUpdate();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(psmt);
	}

	
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
		psmt.setString(4, at.getPhotoNo());
		result=psmt.executeUpdate();
	} catch (SQLException e) {
					e.printStackTrace();
	}finally {
		JDBCTemplate.close(psmt);
	}
	return result;
	
}
	

	public int increaseCount(Connection conn, int postNo) {
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, postNo);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(psmt);
		}
		
		return result;
	}
	
public Circle selectBoard(Connection conn, int postNo) {
		
		//select => ResultSet
		
		Circle c = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, postNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				c = new Circle(
							rset.getString("POST_NO"),
							
							rset.getString("POST_TITLE"),
							rset.getString("POST_CONTENT"),
							rset.getDate("CREATE_DATE"),
							rset.getString("MEMBER_NIC"));
			}
					
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		
		return c;
	}

public ArrayList<Circle> selectList(Connection conn, PageInfo pi, String field, String query){
	
	//select문 => ResultSet
	
	ArrayList<Circle> list = new ArrayList<>();
	
	PreparedStatement psmt = null;
	
	ResultSet rset = null;
	
	
	String sql =  prop.getProperty("selectList");
	sql = sql.replace("$", field);
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
		
		psmt.setString(1, "%"+query+"%");
		
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

public int selectListCount(Connection conn ,String field, String query) {
	// select문 -> Result객체
	int listCount = 0;
	
	PreparedStatement psmt = null;
	
	ResultSet rset = null;
	
	String sql = prop.getProperty("selectListCount");
	sql=sql.replace("$", field);
	try {
		psmt = conn.prepareStatement(sql);
	
		psmt.setString(1, "%"+query+"%");
		
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


public int updateBoard(Connection conn, Circle c) {
	int result = 0;
	PreparedStatement psmt = null;
	String sql = prop.getProperty("updateBoard");
	
	try {
		psmt = conn.prepareStatement(sql);
		
		psmt.setString(1, c.getPostTitle());
		psmt.setString(2, c.getPostContent());
		psmt.setString(3, c.getPostNo());
		result = psmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(psmt);
	}
	
	return result;
}

public int deleteBoard(int postNo, Connection conn) {
	int result = 0;
	PreparedStatement psmt = null;
	String sql = prop.getProperty("deleteBoard");
	
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, postNo);
		result=psmt.executeUpdate();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(psmt);
	}
	return result;
}



public Attachment selectAttachment(Connection conn, int postNo) {
	
	Attachment at = null;
	PreparedStatement psmt = null;
	ResultSet rset=null;
	
	String sql = prop.getProperty("selectAttachment");
	
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, postNo);
		
		rset = psmt.executeQuery();
		
		if(rset.next()) {
			at = new Attachment();
			
			at.setPhotoNo(rset.getString("PHOTO_NO"));
			at.setOriginName(rset.getString("ORIGIN_NAME"));
			at.setNewName(rset.getString("NEW_NAME"));
			at.setPath(rset.getString("PATH"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(psmt);
	}
	return at;
	
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		
		JDBCTemplate.close(psmt);
	}
	System.out.println("result : "+result);
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
		JDBCTemplate.close(rset);
		JDBCTemplate.close(psmt);
	}
	
	return list;
	
}

public int replyDel(Connection conn, int replyNo) {
	int result = 0;
	PreparedStatement psmt = null;
	String sql = prop.getProperty("deletereply");
	
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setInt(1, replyNo);
		result=psmt.executeUpdate();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(psmt);
	}
	return result;
	
	
}




}
