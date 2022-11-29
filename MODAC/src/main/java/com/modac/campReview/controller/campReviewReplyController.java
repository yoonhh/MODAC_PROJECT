package com.modac.campReview.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.modac.campReview.model.service.CampReviewService;
import com.modac.common.model.vo.Reply;

/**
 * Servlet implementation class camStagramReplyController
 */
@WebServlet("/replylist.cr")
public class campReviewReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public campReviewReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postNo = Integer.parseInt(request.getParameter("crno"));
		
	      ArrayList<Reply> list = new CampReviewService().selectReplyList(postNo);
	      
	      //Gson을 이용해서 응답 => ArrayList -> JSONObject 배열 형태로 변환
	      response.setContentType("application/json; charset=UTF-8");
	      new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
