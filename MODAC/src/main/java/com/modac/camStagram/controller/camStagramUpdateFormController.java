package com.modac.camStagram.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.camStagram.model.service.CamStagramService;
import com.modac.camStagram.model.vo.CamStagram;
import com.modac.common.model.vo.Attachment;

/**
 * Servlet implementation class campReviewUpdateFormController
 */
@WebServlet("/updateForm.cs")
public class camStagramUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public camStagramUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int postNo = Integer.parseInt(request.getParameter("csno"));
		
		CamStagram cs = new CamStagramService().selectCamStagram(postNo);
		Attachment at = new CamStagramService().selectAttachment(postNo);
		
		request.setAttribute("cs", cs);
		request.setAttribute("at", at);
		request.getRequestDispatcher("views/camStagram/camStagramUpdateForm.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
