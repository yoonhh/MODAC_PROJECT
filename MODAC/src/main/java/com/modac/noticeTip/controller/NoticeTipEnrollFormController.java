package com.modac.noticeTip.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class NoticeTipEnrollFormController
 */
@WebServlet("/noticeTipEnrollForm")
public class NoticeTipEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeTipEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "캠핑 팁 등록 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("views/noticeTip/campTipEnrollForm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
