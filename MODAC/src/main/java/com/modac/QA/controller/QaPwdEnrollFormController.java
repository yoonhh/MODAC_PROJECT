package com.modac.QA.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class QaPwdEnrollFormController
 */
@WebServlet("/qaCheckPwd.qa")
public class QaPwdEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaPwdEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member admin = (Member)request.getSession().getAttribute("loginMember");
		
		String qaNo = request.getParameter("qno");
		
		if(admin != null && admin.getMemberLevel() == 10) {
			response.sendRedirect(request.getContextPath()+"/qaDetail?qno="+qaNo);
		}else {
			if((request.getSession().getAttribute("loginMember") == null)){
				request.setAttribute("errorMsg", "로그인이 필요합니다");
				request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
				return;
			}else {
				request.getRequestDispatcher("views/QA/qaCheckPwd.jsp").forward(request, response);
				System.out.println(qaNo);
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
