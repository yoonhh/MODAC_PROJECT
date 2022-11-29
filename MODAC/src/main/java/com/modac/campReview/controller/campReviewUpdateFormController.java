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
 * Servlet implementation class campReviewUpdateFormController
 */
@WebServlet("/updateForm.cr")
public class campReviewUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public campReviewUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int postNo = Integer.parseInt(request.getParameter("crno"));
		
		CampReview cr = new CampReviewService().selectCampReview(postNo);
		ReviewTag rt = new CampReviewService().selectReviewTag(postNo);
		Attachment at = new CampReviewService().selectAttachment(postNo);
		
		request.setAttribute("cr", cr);
		request.setAttribute("rt", rt);
		request.setAttribute("at", at);
		request.getRequestDispatcher("views/campReview/campReviewUpdateForm.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
