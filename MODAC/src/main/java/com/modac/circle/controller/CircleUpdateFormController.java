package com.modac.circle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.circle.model.service.CircleBoardService;
import com.modac.circle.model.vo.Circle;
import com.modac.common.model.vo.Attachment;

/**
 * Servlet implementation class CircleUpdateFormController
 */
@WebServlet("/cupdateForm.bo")
public class CircleUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CircleUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CircleBoardService cService = new CircleBoardService();
		
		int postNo = Integer.parseInt(request.getParameter("bno"));
		
		
		
		
		Circle c = cService.selectBoard(postNo);
		
	
		Attachment at = cService.selectAttachment(postNo);
		
		request.setAttribute("c", c);
	
		request.setAttribute("at", at);
		
		
		request.getRequestDispatcher("views/circle/circleUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
