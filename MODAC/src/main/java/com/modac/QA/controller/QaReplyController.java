package com.modac.QA.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.modac.QA.model.service.QaService;
import com.modac.QA.model.vo.Qa;
import com.modac.common.model.vo.Reply;

/**
 * Servlet implementation class QaReplyController
 */
@WebServlet("/replyList.qa")
public class QaReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postNo = Integer.parseInt(request.getParameter("qno"));// qno라는 키값을 형변환함
		ArrayList<Reply> list = new QaService().selectReplyList(postNo);
		
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
