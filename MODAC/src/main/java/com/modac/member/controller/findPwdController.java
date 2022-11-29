package com.modac.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modac.member.model.service.MemberService;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class findPwdController
 */
@WebServlet("/findPw.me")
public class findPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findPwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private MemberService ms = new MemberService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		
		Member findPwd = ms.findPwd(memberId, memberName, email);
		System.out.println("findPwd : "+findPwd); 
		
		if(findPwd == null) {
			
			request.setAttribute("errorMsg", "올바르지 않은 정보입니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);

		
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("views/member/newPwd.jsp");
			
			request.setAttribute("memberId", memberId);
			request.setAttribute("memberName", memberName);
			request.setAttribute("email", email);
			
//			request.setAttribute("findPwd", findPwd);
			
			rd.forward(request, response);
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
