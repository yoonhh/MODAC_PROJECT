package com.modac.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.member.model.vo.Member;
import com.modac.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/deleteNotice")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteController() {
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
		
		String noticeNo = request.getParameter("nno");
	
		int result1 = new NoticeService().deleteNotice(noticeNo);
		
		System.out.println("result :"+result1);
		if(result1 > 0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/noticeList");
		}else {
			request.setAttribute("errorMsg", "공지사항 삭제 실패");
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














