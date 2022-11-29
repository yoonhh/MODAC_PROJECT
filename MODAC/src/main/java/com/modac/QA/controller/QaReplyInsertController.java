package com.modac.QA.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.QA.model.service.QaService;
import com.modac.common.model.vo.Reply;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class QaReplyInsertController
 */
@WebServlet("/insertReply.qa")
public class QaReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String replyContent = request.getParameter("replycontent");
		String postNo = request.getParameter("qno");
		String memberNic =((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		
		Reply r = new Reply();
		r.setReplyContent(replyContent);
		r.setPostNo(postNo);
		r.setWriter(memberNic+"");// 데이터 저장
		
		int result = new QaService().insertReply(r);// 넘겨주고
		
		response.getWriter().print(result);
		System.out.println("conreuslt:" +result);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
