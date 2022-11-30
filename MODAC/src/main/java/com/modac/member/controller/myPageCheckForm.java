package com.modac.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class myPageCheckForm
 */
@WebServlet("/myPagecheck.me")
public class myPageCheckForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myPageCheckForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberPwd = request.getParameter("memberPwd");
		
		HttpSession session = request.getSession();
		String memberPwd_ = ((Member)session.getAttribute("loginMember")).getMemberPwd();
		
		if(!memberPwd.equals(memberPwd_)) {
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			response.sendRedirect(request.getContextPath()+"/myPagePwd.me");
		}else {
			request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
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
