package com.modac.FAQ.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.FAQ.model.service.FaqService;
import com.modac.FAQ.model.vo.Faq;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class FaqInsertController
 */
@WebServlet("/faqInsert")
public class FaqInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "FAQ 등록 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		
		String faqTitle = request.getParameter("title");
		String faqContent = request.getParameter("content");
		
		Faq f = new Faq();
		f.setFaqTitle(faqTitle);
		f.setFaqContent(faqContent);
		
		int result = new FaqService().insertFaq(f);
		
		if(result > 0) { //성공시 => list.no 리스트 페이지로 redirect
			request.getSession().setAttribute("alertMsg", "성공적으로 FAQ가 등록되었습니다.");
			response.sendRedirect(request.getContextPath()+"/faqList"); // jsp/list.no
			
		}else { // 실패시 => 에러페이지 보여지도록
			request.setAttribute("errorMsg", "FAQ 등록 실패");
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
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
