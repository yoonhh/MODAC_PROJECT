package com.modac.recipe.model.dao;


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
import com.modac.campReview.model.vo.CampReview;
import com.modac.common.model.vo.Attachment;
import com.modac.recipe.model.vo.Recipe;

import static com.modac.common.JDBCTemplate.*;

public class RecipeDao {

	private Properties prop = new Properties();
	
	public RecipeDao() {
		String fileName = RecipeDao.class.getResource("/sql/recipe/recipe-mapper.xml").getPath();
		
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
	
	 public ArrayList<Recipe> selectRecipeList(Connection conn, PageInfo pi, String field, String query){
		 
		 ArrayList<Recipe> list = new ArrayList<>();
		 
		 PreparedStatement psmt = null;
		 
		 ResultSet rset = null;
		 
		 String sql = prop.getProperty("selectRecipeList");
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
				list.add(new Recipe(rset.getString("TITLE_IMG"),
		                				rset.getString("POST_NO"),
						                rset.getString("POST_TITLE"),
										rset.getString("MEMBER_NIC"),
										rset.getDate("CREATE_DATE"),
										rset.getString("TIME"),
										rset.getString("DIFFICULTY"),
										rset.getString("MAIN_INGRE")
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
	 
	 public int increaseCount(int recipeNo, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("increaseCount");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, recipeNo);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		return result;
	 }
	 
	 
	 public Recipe selectRecipe(int recipeNo, Connection conn) {
		 
		 ResultSet rset = null;
		 
		 Recipe r = null;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("selectRecipe");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, recipeNo);
			psmt.setInt(2, recipeNo);
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				r = new Recipe( rset.getString("POST_NO"),
						             rset.getString("POST_TITLE"),
						             rset.getString("POST_CONTENT"),
						             rset.getString("MEMBER_NIC"),
						             rset.getDate("CREATE_DATE"),
						             rset.getString("TIME"),
						             rset.getString("DIFFICULTY"),
						             rset.getString("MAIN_INGRE"),
						             rset.getString("SUB_INGRE"),
						             rset.getString("TITLE_IMG")
						            );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return r;
		 
	 }
	 
	 
	 public Attachment selectAttachment(int recipeNo, Connection conn) {
		 
		 Attachment at = null;
		
		 PreparedStatement psmt = null;
		 
		 ResultSet rset = null;

		 String sql = prop.getProperty("selectAttachment");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, recipeNo);
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
	
	 public int insertRecipe(Recipe r, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("insertRecipe");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, r.getPostTitle());
			psmt.setString(2, r.getPostContent());
			psmt.setString(3, r.getMemberNo());
			psmt.setString(4, r.getTime());
			psmt.setString(5, r.getDifficulty());
			psmt.setString(6, r.getMainIngre());
			psmt.setString(7, r.getSubIngre());

			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	 
	 public int updateDeleteAttachment(Recipe r, Connection conn) {
			
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("updateDeleteAttachment");

		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, r.getPostNo());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 return result;
	 }
	 
	 public int updateRecipe(Recipe r, Connection conn) {
		 
		 int result = 0;
		 
		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("updateRecipe");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, r.getPostTitle());
			psmt.setString(2, r.getPostContent());
			psmt.setString(3, r.getTime());
			psmt.setString(4, r.getDifficulty());
			psmt.setString(5, r.getMainIngre());
			psmt.setString(6, r.getSubIngre());
			psmt.setString(7, r.getPostNo());
			

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
	 
	 public int deleteRecipe(int RecipeNo, Connection conn) {
		 
		 int result = 0;

		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("deleteRecipe");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, RecipeNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
		 
	 }
	 
	 public void deleteAttachment(int RecipeNo, Connection conn) {

		 PreparedStatement psmt = null;
		 
		 String sql = prop.getProperty("deleteAttachment");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, RecipeNo);
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		 
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
