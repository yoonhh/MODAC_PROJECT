package com.modac.noticeTip.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.common.model.vo.Attachment;
import com.modac.member.model.vo.Member;
import com.modac.noticeTip.model.service.NoticeTipService;
import com.modac.noticeTip.model.vo.NoticeTip;

/**
 * Servlet implementation class NoticeTipUpdateFormController
 */
@WebServlet("/updateForm.nt")
public class NoticeTipUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeTipUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "캠핑 팁 수정 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}

		String postNo = request.getParameter("ntNo");
		
		NoticeTip nt = new NoticeTipService().selectNoticeTip(postNo);
		Attachment at = new NoticeTipService().selectAttachment(postNo);
		
		request.setAttribute("nt", nt);
		request.setAttribute("at", at);
	
		request.getRequestDispatcher("views/noticeTip/campTipUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
