package com.modac.recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.common.model.vo.Reply;
import com.modac.member.model.vo.Member;
import com.modac.recipe.model.service.RecipeService;

/**
 * Servlet implementation class camStagramReplyInsertController
 */
@WebServlet("/replyinsert.r")
public class recipeReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recipeReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String replyContent = request.getParameter("replycontent");
	      String postNo = request.getParameter("rno");
		  
		  String memberNo =((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
	      
	      Reply r = new Reply();
	      r.setReplyContent(replyContent);
	      r.setPostNo(postNo);;
	      r.setWriter(memberNo+"");// 데이터 저장
	      
	      int result = new RecipeService().insertReply(r);// 넘겨주고
	      
	      response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
