package com.modac.recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.recipe.model.service.RecipeService;
import com.modac.common.model.vo.Attachment;
import com.modac.recipe.model.vo.Recipe;

/**
 * Servlet implementation class campReviewDetailController
 */
@WebServlet("/detail.r")
public class recipeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recipeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int recipeNo = Integer.parseInt(request.getParameter("rno"));
	
	RecipeService rService = new RecipeService();

	int result = rService.increaseCount(recipeNo);
	
	if(result > 0 ) { // 성공, 상세조회 페이지 
		Recipe r = rService.selectRecipe(recipeNo);
		Attachment at = rService.selectAttachment(recipeNo);
		
		request.setAttribute("r", r);
		request.setAttribute("at", at);
		
		System.out.println("r : "+r+" / at : "+at );
		request.getRequestDispatcher("views/recipe/recipeDetailView.jsp").forward(request, response);
		
	}else { // 실패, 에러페이지 
		request.setAttribute("errorMsg", "게시글 조회 실패");
		request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
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
