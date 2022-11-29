package com.modac.QA.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.QA.model.service.QaService;
import com.modac.QA.model.vo.Qa;
import com.modac.notice.model.service.NoticeService;
import com.modac.notice.model.vo.Notice;
import com.modac.common.model.vo.PageInfo;

/**
 * Servlet implementation class QaListController
 */
@WebServlet("/qaList")
public class QaListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String field_ = request.getParameter("f");
	      String query_ = request.getParameter("q");
	   
	      String field = "POST_TITLE";
	      if(field_ != null)
	         field = field_;
	      
	      String query = "";
	      if(query_!=null)
	         query = query_;
		
		
		int listCount;
		int currentPage;
		int pageLimit;
		int boardLimit;
		int maxPage;
		int startPage;
		int endPage;
		
		listCount = new QaService().selectListCount();
		currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
		pageLimit = 5;
		boardLimit = 7;
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		startPage = (currentPage - 1) / pageLimit * pageLimit +1;
		endPage = startPage + pageLimit -1;
		
	
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Notice> list = new NoticeService().selectNoticeList(pi, "NOTICE_TITLE", query);
		
		request.setAttribute("list", list);
		
		ArrayList<Qa> qList = new QaService().selectQaList(pi, field, query);
		
		request.setAttribute("qList", qList);
		request.setAttribute("pi", pi); 
		
		request.getRequestDispatcher("views/QA/qna.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
