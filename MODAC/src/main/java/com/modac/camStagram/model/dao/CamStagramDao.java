package com.modac.camStagram.model.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;
import com.modac.camStagram.model.vo.BoardLike;
import com.modac.camStagram.model.vo.CamStagram;
import com.modac.common.model.vo.Attachment;

import static com.modac.common.JDBCTemplate.*;

public class CamStagramDao {

	private Properties prop = new Properties();
	
	public CamStagramDao() {
		String fileName = CamStagramDao.class.getResource("/sql/camStagram/camStagram-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//0
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
	
	 //0
	 public ArrayList<CamStagram> selectCamStagramList(Connection conn, PageInfo pi, String field, String query){
		 
		 ArrayList<CamStagram> list = new ArrayList<>();
		 
		 PreparedStatement psmt = null;
		 
		 ResultSet rset = null;
		 
		 String sql = prop.getProperty("selectCamStagramList");
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
				list.add(new CamStagram(rset.getString("TITLE_IMG"),
		                				rset.getString("POST_NO"),
						                rset.getString("POST_CONTENT"),
										rset.getString("MEMBER_NO"),
										rset.getString("MEMBER_NIC"),
										rset.getDate("CREATE_DATE"),
										rset.getString("LIKE_COUNT"),
										rset.getString("REPLY_COUNT")
										));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		return list;
	 };
	 
	 //0
	 public int increaseCount(int postNo, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("increaseCount");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, postNo);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		return result;
	 }
	 
	 //0
	 public CamStagram selectCamStagram(int postNo, Connection conn) {
		 
		 ResultSet rset = null;
		 
		 CamStagram cs = null;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("selectCamStagram");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, postNo);
			psmt.setInt(2, postNo);
			psmt.setInt(3, postNo);
			psmt.setInt(4, postNo);
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				cs = new CamStagram( rset.getString("TITLE_IMG"),
			                         rset.getString("POST_NO"),
						             rset.getString("POST_CONTENT"),
									 rset.getString("MEMBER_NO"),
						             rset.getString("MEMBER_NIC"),
						             rset.getDate("CREATE_DATE"),
									 rset.getString("LIKE_COUNT"),
									 rset.getString("REPLY_COUNT")
						            );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		 System.out.println("dao cs : "+cs);
		return cs;
		 
	 }
	 
	 //0
	 public Attachment selectAttachment(int postNo, Connection conn) {
		 
		 Attachment at = null;
		
		 PreparedStatement psmt = null;
		 
		 ResultSet rset = null;

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
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		 
		return at;
		 
	 }
	 
public BoardLike selectBoardLike(int postNo, String memberNo, Connection conn) {
		 
		 BoardLike bl = null;
		
		 PreparedStatement psmt = null;
		 
		 ResultSet rset = null;

		 String sql = prop.getProperty("selectBoardLike");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, postNo);
			psmt.setString(2, memberNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				bl = new BoardLike();
				bl.setPostNo(rset.getString("POST_NO"));
				bl.setMemberNo(rset.getString("MEMBER_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		return bl;
		 
	 }
	
	 //0
	 public int insertCamStagram(CamStagram cs, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("insertCamStagram");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cs.getPostContent());
			psmt.setString(2, cs.getMemberNo());

			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 //0
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
	
	 //0
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

			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 //0
	 public int updateCamStagram(CamStagram cs, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("updateCamStagram");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cs.getPostContent());
			psmt.setString(2, cs.getPostNo());

			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 return result;
		 
	 }
	 
	 //0
	 public int updateAttachment(Attachment at, Connection conn) {
			
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("updateAttachment");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, at.getOriginName());
			psmt.setString(2, at.getNewName());
			psmt.setString(3, at.getPath());
			psmt.setString(4, at.getPostNo());

			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 //0
	 public int deleteCamStagram(int postNo, Connection conn) {
		 
		 int result = 0;

		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("deleteCamStagram");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, postNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
		 
	 }
	 
	 //0
	 public void deleteAttachment(int postNo, Connection conn) {

		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("deleteAttachment");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, postNo);
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 
	 }
	 
	 public int insertBoardLike(String postNo, String memberNo, Connection conn) {

		 int postNo1 = Integer.parseInt(postNo);
		 int memberNo1 = Integer.parseInt(memberNo);
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("insertBoardLike");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, postNo1);
			psmt.setInt(2, memberNo1);

			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 public int deleteBoardLike(String postNo, String memberNo, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("deleteBoardLike");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, postNo);
			psmt.setString(2, memberNo);

			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 public int insertReply(Connection conn, Reply r) {
		   
		   int result = 0;
		   PreparedStatement psmt = null;
		   
		   String sql = prop.getProperty("insertReply");
		   
		   try {
		      psmt = conn.prepareStatement(sql);
		      psmt.setString(1, r.getReplyContent());
		      psmt.setString(2, r.getPostNo());
		      psmt.setString(3, r.getWriter());
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
	
	
	
}
