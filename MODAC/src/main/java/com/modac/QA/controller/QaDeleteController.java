package com.modac.QA.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.QA.model.service.QaService;
import com.modac.QA.model.vo.Qa;
import com.modac.member.model.vo.Member;
import com.modac.notice.model.service.NoticeService;

/**
 * Servlet implementation class QaDeleteController
 */
@WebServlet("/delete.qno")
public class QaDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String qno = request.getParameter("qno");
		
		Qa q = new QaService().selectQa(qno);
		
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberNic().equals(q.getMemberNic()))){
			request.setAttribute("errorMsg", "게시글 삭제 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
		
	
		int result1 = new QaService().deleteQa(qno);
		
		if(result1 > 0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 Q&A가 삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/qaList");
		}else {
			request.setAttribute("errorMsg", "Q&A 삭제 실패");
			request.getRequestDispatcher("views/common/errorPage3.jsp").forward(request, response);
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
