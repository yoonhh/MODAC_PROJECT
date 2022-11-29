package com.modac.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.common.model.vo.Attachment;
import com.modac.member.model.vo.Member;
import com.modac.notice.model.service.NoticeService;
import com.modac.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateFromController
 */
@WebServlet("/updateForm.no")
public class NoticeUpdateFromController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateFromController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "공지사항 수정 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
		
		String noticeNo = request.getParameter("nno");
		
		Notice n = new NoticeService().selectNotice(noticeNo);
		ArrayList<Attachment> list = new NoticeService().selectAttachment(noticeNo);
		
		request.setAttribute("n", n);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/notice/noticeUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
