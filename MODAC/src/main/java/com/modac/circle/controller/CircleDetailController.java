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
import com.modac.common.model.vo.Reply;

/**
 * Servlet implementation class CircleDetailController
 */
@WebServlet("/cdetail.bo")
public class CircleDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CircleDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo = Integer.parseInt(request.getParameter("bno"));
		
		CircleBoardService cService = new CircleBoardService();
		
		//조회수 증가 / 게시글 조회 (Board)/ 첨부파일 조회(Attachment)
		
		int result = cService.increaseCount(postNo);
		
		if(result>0) { // 유효한 게시글 일때 => 게시글, 첨부파일 => 상세조회 페이지
			
			Circle c = cService.selectBoard(postNo);
			Attachment at = cService.selectAttachment(postNo);
			ArrayList<Reply> list = new CircleBoardService().selectReplyList(postNo);
			
			
			request.setAttribute("c", c);
			request.setAttribute("at", at);
			request.setAttribute("list",list);
			
			
			request.getRequestDispatcher("views/circle/circleDetailView.jsp").forward(request, response);
			
			
			
		}else {//에러페이지
			request.setAttribute("errorMsg", "게시글 상세조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
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
