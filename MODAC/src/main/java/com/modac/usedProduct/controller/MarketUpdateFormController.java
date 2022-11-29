package com.modac.usedProduct.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.usedProduct.model.service.MarketService;
import com.modac.usedProduct.model.vo.Attachment;
import com.modac.usedProduct.model.vo.Market;

/**
 * Servlet implementation class MarketUpdateController
 */
@WebServlet("/updateForm.mk") //마켓 게시글 수정페이지
public class MarketUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String postNo = request.getParameter("mno");
		
		Market m = new MarketService().selectMarket(postNo);
		ArrayList<Attachment> list = new MarketService().selectAttachment(postNo);
		
		request.setAttribute("m", m);
		request.setAttribute("list", list);
		
		//마켓 게시글 수정페이지
		request.getRequestDispatcher("/views/usedProduct/marketUpdateForm.jsp").forward(request, response);
		System.out.println(list);
		System.out.println(m);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
