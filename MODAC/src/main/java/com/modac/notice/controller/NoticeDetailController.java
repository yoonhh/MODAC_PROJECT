package com.modac.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.common.model.vo.Attachment;
import com.modac.notice.model.service.NoticeService;
import com.modac.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/noticeDetail")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클릭했을 때 공지사항 글번호 nno
		String noticeNo = request.getParameter("nno");
		
		NoticeService nService = new NoticeService();
		
		// 조회수 증가용 서비스 호출
		int result = new NoticeService().increaseCount(noticeNo);
		
		if(result > 0) { // 성공했을 경우 => 해당 공지사항 상세조회
			Notice n = nService.selectNotice(noticeNo);
			ArrayList<Attachment> list = new NoticeService().selectAttachment(noticeNo);
			
			request.setAttribute("n", n);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("views/notice/noticeDetail.jsp").forward(request, response);
		}else { // 실패시 에러페이지
			 request.setAttribute("errorMsg", "공지사항 조회 실패");
			 request.getRequestDispatcher("views/common/errorPage2.jsp");
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














