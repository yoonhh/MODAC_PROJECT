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
import com.modac.common.model.vo.Attachment;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class QaCheckPwdController
 */
@WebServlet("/qaCheckPwd")
public class QaCheckPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaCheckPwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qaNo = request.getParameter("qno");
		String postPwd = request.getParameter("postPwd");
		System.out.println("qaNo : " + qaNo);
		
		QaService qService = new QaService();
		Qa qa = new Qa();
		qa.setQaNo(qaNo);
		qa.setPostPwd(postPwd);
		
		int result2 = qService.checkPwd(qa);
		System.out.println("result2 : " + result2);
		
		int result1 = new QaService().increaseCount(qaNo);
		System.out.println("result1 : " + result1);
		
		if (result1 > 0 && result2 > 0) { // 성공했을 경우 => 해당 Q&A 상세조회
			Qa q = qService.selectQa(qaNo);
			ArrayList<Attachment> list = new QaService().selectAttachment(qaNo);

			request.setAttribute("q", q);
			request.setAttribute("list", list);

			System.out.println("qaNo : " + qaNo);
			request.getRequestDispatcher("views/QA/qaDetail.jsp").forward(request, response);
		} else { // 실패시 에러페이지
			 request.setAttribute("errorMsg", "Q&A 조회 실패");
			 request.getRequestDispatcher("views/common/errorPage3.jsp").forward(request, response);
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
