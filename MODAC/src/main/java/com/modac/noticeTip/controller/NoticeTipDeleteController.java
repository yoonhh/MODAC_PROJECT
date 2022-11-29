package com.modac.noticeTip.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.QA.model.service.QaService;
import com.modac.QA.model.vo.Qa;
import com.modac.member.model.vo.Member;
import com.modac.noticeTip.model.service.NoticeTipService;
import com.modac.noticeTip.model.vo.NoticeTip;

/**
 * Servlet implementation class NoticeTipDeleteController
 */
@WebServlet("/delete.nt")
public class NoticeTipDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeTipDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "공지사항 삭제 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
		
		String ntNo = request.getParameter("ntNo");
		
		NoticeTip nt = new NoticeTipService().selectNoticeTip(ntNo);
		
		int result1 = new NoticeTipService().deleteNoticeTip(ntNo);
		
		if(result1 > 0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 캠프 팁이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/campTipList");
		}else {
			request.setAttribute("errorMsg", "캠프 팁 삭제 실패");
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
