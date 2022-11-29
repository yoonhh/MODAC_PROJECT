package com.modac.campReview.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.camStagram.model.service.CamStagramService;
import com.modac.campReview.model.service.CampReviewService;
import com.modac.common.model.vo.Reply;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class camStagramReplyInsertController
 */
@WebServlet("/replyinsert.cr")
public class campReviewReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public campReviewReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String replyContent = request.getParameter("replycontent");
	      String postNo = request.getParameter("crno");
		  String memberNo =((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
	      
	      Reply r = new Reply();
	      r.setReplyContent(replyContent);
	      r.setPostNo(postNo);;
	      r.setWriter(memberNo+"");// 데이터 저장
	      
	      int result = new CampReviewService().insertReply(r);// 넘겨주고
	      
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
