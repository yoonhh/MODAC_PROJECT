package com.modac.recipe.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.modac.campReview.model.vo.MyFileRenamePolicy;
import com.modac.common.model.vo.Attachment;
import com.modac.recipe.model.service.RecipeService;
import com.modac.recipe.model.vo.Recipe;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class campReviewUpdateController
 */
@WebServlet("/update.r")
public class recipeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recipeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 10;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/recipe_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			

			String postNo = multiRequest.getParameter("postNo");
			String postTitle = multiRequest.getParameter("title");
			String postContent = multiRequest.getParameter("content");
			String time = multiRequest.getParameter("time");
			String difficulty = multiRequest.getParameter("difficulty");
			String mainIngre = multiRequest.getParameter("mainIngre");
			String subIngre = multiRequest.getParameter("subIngre");
			
			Recipe r = new Recipe();
			r.setPostTitle(postTitle);
			r.setPostContent(postContent);
			r.setPostNo(postNo);
			r.setTime(time);
			r.setDifficulty(difficulty);
			r.setMainIngre(mainIngre);
			r.setSubIngre(subIngre);
			
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));// 원본명
				at.setNewName(multiRequest.getFilesystemName("upfile"));//수정명(실제 서버에 업로드되어있는 파일명)
				at.setPath("resources/recipe_upfiles/");
				
				if(multiRequest.getParameter("originFileNo") != null) {
					// 기존 파일이 있는 경우
	                at.setPhotoNo(multiRequest.getParameter("originFileNo"));
	                new File(savePath+multiRequest.getParameter("originFileName")).delete();
	                at.setPostNo(postNo);
	          
	            }else {
	                // 기존에 파일이 없는 경우
	                // => insert Attachment
	                // ref_bno + 현재 게시물 번호 
	                at.setPostNo(postNo);
	                at.setBoardNo("7");
	            }
			}
			
			int result = new RecipeService().updateRecipe(r, at);
			
            // case1 : 새로운 첨부파일 없는 경우(x) => b, null -> Board Update
            // case2 : 새로운 첨부파일 있는 경우(o), 기존 첨부파일도 있는 경우(o) => b, at에 fileNo 
            //            => Board Update, Attachment update
            // case3 : 새로운 첨부파일 있는 경우(o), 기존 첨부파일도 없는 경우(x) => b, at에 refNo 
            //            => Board Update, Attachment insert
            
			System.out.println("update controller : "+result);
			
            if(result > 0) { //수정성공 => 상세조회페이지
                request.getSession().setAttribute("alertMsg","성공적으로 수정되었습니다.");
                response.sendRedirect(request.getContextPath()+"/detail.r?rno=" + postNo);
            }else { // 수정실패 => errorPage
                request.setAttribute("errorMsg", "게시글 수정에 실패했습니다.");
                request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
            }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
