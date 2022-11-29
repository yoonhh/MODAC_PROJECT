package com.modac.campReview.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.campReview.model.service.CampReviewService;
import com.modac.campReview.model.vo.CampReview;
import com.modac.campReview.model.vo.ReviewTag;
import com.modac.common.model.vo.Attachment;

/**
 * Servlet implementation class campReviewDetailController
 */
@WebServlet("/detail.cr")
public class campReviewDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public campReviewDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int campReviewNo = Integer.parseInt(request.getParameter("crno"));
		
		int result = new CampReviewService().increaseCount(campReviewNo);
		
		
		if(result > 0 ) { // 성공, 상세조회 페이지 
			CampReview cr = new CampReviewService().selectCampReview(campReviewNo);
			ReviewTag rt = new CampReviewService().selectReviewTag(campReviewNo);
			Attachment at = new CampReviewService().selectAttachment(campReviewNo);
			
			request.setAttribute("cr", cr);
			request.setAttribute("rt", rt);
			request.setAttribute("at", at);
			
			request.getRequestDispatcher("views/campReview/campReviewDetailView.jsp").forward(request, response);
			
		}else { // 실패, 에러페이지 
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
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
