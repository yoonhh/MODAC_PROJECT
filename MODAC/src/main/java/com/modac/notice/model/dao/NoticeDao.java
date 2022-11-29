package com.modac.notice.model.dao;

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

import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.notice.model.vo.Notice;

import static com.modac.common.JDBCTemplate.*;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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

			rset = psmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt("COUNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		return listCount;

	}

	
	public ArrayList<Notice> selectNoticeList(Connection conn,  PageInfo pi, String field, String query){
	
		// select문 = > ResultSet객체(여러행)
		ArrayList<Notice> list = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeList");
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
				list.add(new Notice(rset.getString("POST_NO"),
									rset.getInt("CATEGORY_NO"),
									rset.getString("NOTICE_TITLE"),
									rset.getString("WRITER"),
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
		System.out.println("list : " + list);
		return list;
	}
	
	
	public int increaseCount(String noticeNo, Connection conn) {
		
		// update문 -> 처리된 행의 갯수를 반환
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, noticeNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
		
	}
	
	
	public Notice selectNotice(String noticeNo, Connection conn) {
		
		// select문은 반환형이 ResultSet
		
		ResultSet rset = null;
		
		Notice n = null;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, noticeNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getString("POST_NO"),
							   rset.getInt("CATEGORY_NO"),
							   rset.getString("NOTICE_TITLE"),
							   rset.getString("NOTICE_CONTENT"),
							   rset.getString("WRITER"),
							   rset.getDate("CREATE_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return n;
	}
	
	
	public int insertNotice(Notice n, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertNotice");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, n.getNoticeCategory());
			psmt.setString(2, n.getNoticeTitle());
			psmt.setString(3, n.getNoticeContent());
			psmt.setString(4, n.getNoticeWriter());
			
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
	
	
	public int updateNotice(Notice n, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("updateNotice");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, n.getNoticeTitle());
			psmt.setString(2, n.getNoticeContent());
			psmt.setInt(3, n.getNoticeCategory());
			psmt.setString(4, n.getNoticeNo());
			
			
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
	
	public int deleteNotice(String noticeNo, Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, noticeNo);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		
		return result;
	}
	
	   public int deleteAttachment(String noticeNo, Connection conn) {
		      
		      int result = 0;
		   	
		      PreparedStatement psmt = null;
		      
		      String sql = prop.getProperty("deleteAttachment");
		      
		      try {
		         psmt = conn.prepareStatement(sql);
		         
		         psmt.setString(1, noticeNo);
		         
		         psmt.executeUpdate();
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         close(psmt);
		      }
		      
		      return result;
		   }
	
	   public int deleteUpfile(String photoNo, Connection conn) {
		      
		      int result = 0;
		   	
		      PreparedStatement psmt = null;
		      
		      String sql = prop.getProperty("deleteUpfile");
		      
		      try {
		         psmt = conn.prepareStatement(sql);
		         
		         psmt.setString(1, photoNo);
		         
		         psmt.executeUpdate();
		         
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         close(psmt);
		      }
		      
		      return result;
		   }
	   
	   
	public ArrayList<Attachment> selectAttachmentList(Connection conn, String noticeNo) {
		
		ArrayList<Attachment> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, noticeNo);
			
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
	

	
}
