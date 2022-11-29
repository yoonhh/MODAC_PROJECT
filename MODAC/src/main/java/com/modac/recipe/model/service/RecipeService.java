package com.modac.recipe.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;
import com.modac.recipe.model.dao.RecipeDao;
import com.modac.camStagram.model.dao.CamStagramDao;
import com.modac.campReview.model.dao.CampReviewDao;
import com.modac.common.model.vo.Attachment;
import com.modac.recipe.model.vo.Recipe;

import static com.modac.common.JDBCTemplate.*;

public class RecipeService {

	public int selectListCount() {
		
		Connection conn = getConnection();
		int listCount = new RecipeDao().selectListCount(conn);
		close();
		return listCount;
	}
	
	public ArrayList<Recipe> selectRecipeList(PageInfo pi, String field, String query){
		
		Connection conn = getConnection();
		
		ArrayList<Recipe> list = new RecipeDao().selectRecipeList(conn, pi, field, query);
		
		close();
		
		return list;
		
	}
	
	public int increaseCount(int recipeNo) {
		
		Connection conn = getConnection();
		
		int result = new RecipeDao().increaseCount(recipeNo, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close();
		return result;		
	}
	

	public Recipe selectRecipe(int recipeNo) {
		
		Connection conn = getConnection();
		
		Recipe cr= new RecipeDao().selectRecipe(recipeNo, conn);
		
		close();
		
		return cr;
	}
	
	
	public Attachment selectAttachment(int recipeNo) {

		Connection conn = getConnection();
		
		Attachment at= new RecipeDao().selectAttachment(recipeNo, conn);
		
		close();
		
		return at;
		
	}
	
	public int insertRecipe(Recipe r, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new RecipeDao().insertRecipe(r, conn);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new RecipeDao().insertAttachment(at, conn);
			
		} 
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close();
		
		return result1 * result2;
	}
	
	public int updateRecipe(Recipe r, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new RecipeDao().updateRecipe(r,conn);
		
		int result2 = 1;
		
        // 새롭게 첨부된 파일이 있는 경우 
        if(at != null) {
            // 기존에 첨부파일이 있었을 경우 => update문 실행
        	at.getPostNo();
            if(at.getPhotoNo() != null) {
                result2 = new RecipeDao().updateAttachment(at, conn);
            }else {//기존에 첨부파일이 없었을 경우 => insert문 실행
                result2 = new RecipeDao().insertNewAttachment(at, conn);
            }
        }else { // 새로운 첨부파일이 없는 경우 
        	if(r.getTitleImg() == null) {
        		r.getPostNo();
        		result2 = new RecipeDao().updateDeleteAttachment(r, conn);
        	}
        }
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close();
		
		return result1 * result2;
	}
	
	public int deleteRecipe(int RecipeNo) {
		Connection conn = getConnection();
		
		int result = new RecipeDao().deleteRecipe(RecipeNo, conn);
		
		new RecipeDao().deleteAttachment(RecipeNo, conn);
		
		commitRollback(result, conn);
		
		return result;
		
	}
	
	public void commitRollback(int result, Connection conn) {
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close();
	}
	

	public ArrayList<Reply> selectReplyList(int postNo){
		
	      Connection conn = getConnection();
	      ArrayList<Reply> list = new RecipeDao().selectReplyList(conn, postNo);
	      
	      close();
	      return list;
	      
	   }
	
	public int insertReply(Reply r) {
	      
	      Connection conn = getConnection();
	      
	      int result = new RecipeDao().insertReply(conn, r);
	      if(result>0) {
	         commit(conn);
	         
	      }else {
	         rollback(conn);
	      }
	      return result;
	   }
	
	
	
	
	
}
