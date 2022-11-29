package com.modac.report.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.modac.member.model.vo.Member;
import com.modac.report.model.service.reportService;
import com.modac.usedProduct.model.service.MarketService;
import com.modac.usedProduct.model.vo.Attachment;
import com.modac.usedProduct.model.vo.Market;

/**
 * Servlet implementation class reportController
 */
@WebServlet("/report.ri")
public class reportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
//		String senderNo = request.getParameter("sender");
		Member member = (Member)request.getSession().getAttribute("loginMember");
		
		String senderNo = member.getMemberNo();
		String postNo = request.getParameter("postNo");
		String reportTitle = request.getParameter("reportTitle");
		String reportContent = request.getParameter("reportContent");
		
		
		int result = new reportService().insertReport(senderNo, postNo, reportTitle, reportContent);
		int count = new reportService().countReport(postNo);
		System.out.println(result);
		System.out.println("신고카운터 : " + count);
		request.setAttribute("count", count);
		
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "신고하기완료");
			request.getRequestDispatcher("/views/common/menubar.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("alertMsg", "신고하기 실패");
			request.getRequestDispatcher("/views/common/menubar.jsp").forward(request, response);
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
