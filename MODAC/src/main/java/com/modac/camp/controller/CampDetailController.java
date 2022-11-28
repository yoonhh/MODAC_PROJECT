package com.modac.camp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.camp.model.service.CampChangeService;
import com.modac.camp.model.service.CampService;
import com.modac.camp.model.vo.Camp;

/**
 * Servlet implementation class CampDetailController
 */
@WebServlet("/campInfo.ca")
public class CampDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CampDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 받아온 캠핑장 이름
		String ccName = request.getParameter("cName");
		String cName = ccName.trim();
		System.out.println("ss "+cName);
		
		// 캠핑장 이름을 캠핑장 번호로 변환
		int cNo = new CampChangeService().selectCNo(cName);
		System.out.println("dd " + cNo);
		
		// 조회수
		int result = new CampService().increaseCount(cNo);
		
		if(result>0) { // 성공했을 경우 -> 상세조회
			// 캠핑장 기본 정보
			Camp c = new CampService().selectCampDetail(cNo);
			// 자연경관
			ArrayList<Camp> n1 = new CampService().n1Detail(cNo);
			// 지형
			ArrayList<Camp> n2 = new CampService().n2Detail(cNo);
			// 편의시설
			ArrayList<Camp> n3 = new CampService().n3Detail(cNo);
			
			
			
			request.setAttribute("c", c);
			request.setAttribute("n1", n1);
			request.setAttribute("n2", n2);
			request.setAttribute("n3", n3);
			request.getRequestDispatcher("views/camp/campDetailView.jsp").forward(request, response);
			
		} else { // 실패
			request.setAttribute("errorMsg", "캠핑장 정보가 없습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		System.out.println(result);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
