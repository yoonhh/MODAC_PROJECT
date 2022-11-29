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
 * Servlet implementation class FaqUpdateController
 */
@WebServlet("/updateFaq")
public class FaqUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "FAQ 수정 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
		
		request.setCharacterEncoding("UTF-8");

		String faqNo = request.getParameter("fno");
		String faqTitle = request.getParameter("title");
		String faqContent = request.getParameter("content");

		Faq f = new Faq();

		f.setFaqNo(faqNo);
		f.setFaqTitle(faqTitle);
		f.setFaqContent(faqContent);

		int result = new FaqService().updateFaq(f);

		if (result > 0) { // 성공시 => 상세보기 화면으로 이동

			request.getSession().setAttribute("alertMsg", "성공적으로 FAQ가 수정되었습니다.");
			response.sendRedirect(request.getContextPath() + "/faqList");

		} else { // 실패시 => 에러페이지로 이동

			request.setAttribute("errorMsg", "FAQ 수정 실패!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
