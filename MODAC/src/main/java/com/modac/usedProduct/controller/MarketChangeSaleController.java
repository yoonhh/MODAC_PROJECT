package com.modac.usedProduct.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.usedProduct.model.service.MarketService;

/**
 * Servlet implementation class MarketChangeSaleController
 */
@WebServlet("/changeSale.mk")
//판매완료 버튼
public class MarketChangeSaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketChangeSaleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String postNo = request.getParameter("mno"); 
				
		int result = new MarketService().changeSale(postNo);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "판매완료로 변경했습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.mk?mno="+postNo);
		}else { //실패 => 에러페이지
			request.setAttribute("errorMsg", "변경 실패했습니다.");
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
