package com.modac.campReview.model.dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.modac.campReview.model.vo.CampReview;
import com.modac.campReview.model.vo.ReviewTag;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;

import java.util.List;

import static com.modac.common.JDBCTemplate.*;

public class CampReviewDao {

	private Properties prop = new Properties();
	
	public CampReviewDao() {
		String fileName = CampReviewDao.class.getResource("/sql/campReview/campReview-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	
	 public ArrayList<CampReview> selectCampReviewList(Connection conn, PageInfo pi, String field, String query){
		 
		 ArrayList<CampReview> list = new ArrayList<>();
		 
		 PreparedStatement psmt = null;
		 
		 ResultSet rset = null;
		 
		 String sql = prop.getProperty("selectCampRevieList");
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
				list.add(new CampReview(rset.getString("POST_NO"),
										rset.getString("POST_TITLE"),
										rset.getString("MEMBER_NIC"),
										rset.getDate("CREATE_DATE"),
										rset.getInt("READ_COUNT")        
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
	 
	 public int increaseCount(int campReviewNo, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("increaseCount");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, campReviewNo);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		return result;
	 }
	 
	 public CampReview selectCampReview(int campReviewNo, Connection conn) {
		 
		 ResultSet rset = null;
		 
		 CampReview cr = null;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("selectCampReview");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, campReviewNo);
			psmt.setInt(2, campReviewNo);
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				cr = new CampReview( rset.getString("POST_NO"),
									 rset.getString("POST_TITLE"),
						             rset.getString("POST_CONTENT"),
						             rset.getString("MEMBER_NIC"),
						             rset.getDate("CREATE_DATE"),
						             rset.getString("TITLE_IMG")
						            );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		return cr;
		 
	 }
	 
	 public ReviewTag selectReviewTag(int postNo, Connection conn) {
		 
		 ResultSet rset = null;
		 
		 ReviewTag rt = null;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("selectReviewTag");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, postNo);
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				rt = new ReviewTag( rset.getString("POST_NO"),
									 rset.getString("TAG_NO")
						            );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		return rt;
		 
	 }
	 
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
	
	 public int insertCampReview(CampReview cr, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("insertCampReview");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cr.getPostNo());
			psmt.setString(2, cr.getPostTitle());
			psmt.setString(3, cr.getPostContent());
			psmt.setString(4, cr.getMemberNo());

			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 public int insertTag(CampReview cr, Connection conn) {
			
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 List<Integer> tList=cr.getTagList();
		 String sql = prop.getProperty("insertTag");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cr.getPostNo());
			
			for(Integer tagNo:tList) {
				psmt.setInt(2, tagNo.intValue());
				result += psmt.executeUpdate();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 public int insertNewTag(CampReview cr, Connection conn) {
			
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 List<Integer> tList=cr.getTagList();
		 String sql = prop.getProperty("insertNewTag");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cr.getPostNo());
			for(Integer tagNo:tList) {
				psmt.setInt(2, tagNo.intValue());
			result += psmt.executeUpdate();}
			
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
	 
	 public int updateDeleteAttachment(CampReview cr, Connection conn) {
			
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("updateDeleteAttachment");

		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cr.getPostNo());

			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 return result;
	 }
	 
	
	 public int updateCampReview(CampReview cr, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("updateCampReview");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cr.getPostTitle());
			psmt.setString(2, cr.getPostContent());
			psmt.setString(3, cr.getPostNo());
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
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
			psmt.setString(4, at.getPostNo());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 public int deleteTag(CampReview cr, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("deleteTag");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cr.getPostNo());
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		 return result;
		 
	 }
	 
	 public int deleteCampReview(int postNo, Connection conn) {
		 
		 int result = 0;

		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("deleteCampReview");
		 
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

	public int selectPostNo(Connection conn) {
		// TODO Auto-generated method stub
		 int result = 0;

		 PreparedStatement psmt = null;
		 
		 String sql = "SELECT SEQ_REVNO.NEXTVAL as nv FROM DUAL";
		 ResultSet rset = null;
		 
		 try {
			psmt = conn.prepareStatement(sql);
						
			rset=psmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("nv");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
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
