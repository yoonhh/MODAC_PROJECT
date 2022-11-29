package com.modac.QA.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.QA.model.service.QaService;
import com.modac.common.model.vo.Attachment;
import com.modac.notice.model.service.NoticeService;
import com.modac.notice.model.vo.Notice;
import com.modac.QA.model.vo.Qa;

/**
 * Servlet implementation class QaDetailController
 */
@WebServlet("/qaDetail")
public class QaDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String qno = request.getParameter("qno");
		
		QaService qService = new QaService();
		
		int result = new QaService().increaseCount(qno);

		if((request.getSession().getAttribute("loginMember") == null)){
			request.setAttribute("errorMsg", "로그인이 필요합니다");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
		}
		
		if (result > 0) { // 성공했을 경우 => 해당 공지사항 상세조회
			
			
			Qa q = qService.selectQa(qno);
			ArrayList<Attachment> list = new QaService().selectAttachment(qno);

			request.setAttribute("q", q);
			request.setAttribute("list", list);

			request.getRequestDispatcher("views/QA/qaDetail.jsp").forward(request, response);
		} else { // 실패시 에러페이지
			 request.setAttribute("errorMsg", "Q&A 조회 실패");
			 request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);;
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
