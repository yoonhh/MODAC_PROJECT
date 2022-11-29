package com.modac.usedProduct.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.usedProduct.model.service.MarketService;

/**
 * Servlet implementation class MarketDeleteController
 */
@WebServlet("/delete.mk") //게시글 삭제
public class MarketDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postNo = request.getParameter("mno");
		
		int result = new MarketService().deleteMarket(postNo);
		
		if(result > 0){
			request.getSession().setAttribute("alertMsg", "성공적으로 삭제했습니다.");
			response.sendRedirect(request.getContextPath()+"/list.mk");
		}else { //실패시
			request.setAttribute("errorMsg", "삭제 실패했습니다.");
			request.getRequestDispatcher("views/common/errorMsg.jsp").forward(request, response);
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
