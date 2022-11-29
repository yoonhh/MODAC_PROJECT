package com.modac.camStagram.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.camStagram.model.service.CamStagramService;
import com.modac.common.model.vo.Reply;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class camStagramReplyInsertController
 */
@WebServlet("/replyinsert.cs")
public class camStagramReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public camStagramReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String replyContent = request.getParameter("replycontent");
		  System.out.println("너왜 안나오니 con의 replycontent : " + replyContent);
	      String postNo = request.getParameter("csno");
		  System.out.println("너왜 안나오니 con의 postNo : " + postNo);
		  
		  String memberNo =((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		  System.out.println("너왜 안나오니 con의 memberNo : " + memberNo);
	      
	      Reply r = new Reply();
	      r.setReplyContent(replyContent);
	      r.setPostNo(postNo);;
	      r.setWriter(memberNo+"");// 데이터 저장
	      
	      int result = new CamStagramService().insertReply(r);// 넘겨주고
	      
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
