package com.modac.recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.campReview.model.service.CampReviewService;
import com.modac.common.model.vo.Attachment;
import com.modac.recipe.model.service.RecipeService;
import com.modac.recipe.model.vo.Recipe;

/**
 * Servlet implementation class campReviewUpdateFormController
 */
@WebServlet("/updateForm.r")
public class recipeUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recipeUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int postNo = Integer.parseInt(request.getParameter("rno"));
		
		Recipe r = new RecipeService().selectRecipe(postNo);
		Attachment at = new RecipeService().selectAttachment(postNo);
		
		request.setAttribute("r", r);
		request.setAttribute("at", at);
		request.getRequestDispatcher("views/recipe/recipeUpdateForm.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
