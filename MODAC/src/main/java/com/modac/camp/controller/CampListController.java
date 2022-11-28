package com.modac.camp.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.camp.model.service.CampService;
import com.modac.camp.model.vo.Camp;
import com.modac.common.model.vo.PageInfo;

/**
 * Servlet implementation class CampListController
 */
@WebServlet("/list.ca")
public class CampListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CampListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 페이징 처리 시작
		int listCount; //현재 총 게시글 갯수
		int currentPage; //현재 페이지(즉, 사용자가 요청한 페이지)
		int pageLimit; //페이지 하단에 보여질 페이징바의최대갯수
		int boardLimit; //페이지에 보여질 게시글의 최댁 갯수
		int maxPage; //가장 마지막 페이지가 몇 번쩨 페이지인지 총 페이지 수
		int startPage; //페이징 하단에 보여질 페이징바의 시작수
		int endPage; ///페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글 갯수
		listCount = new CampService().selectListCount(); // 120개
		
		// * currentPage : 현재 페이지
		currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
		
		// * pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대갯수(페이지목록들 몇개 단위씩)
		pageLimit = 5;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대갯수(게시글 몇개 단위씩)
		boardLimit = 8;
		
		//* maxPage : 가장 마지막 페이지가 몇번 페이지인지(총 페이지수)
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// * startPage : 페이지 하단에 보여질 페이징 바의 시작수
		startPage = (currentPage -1) / pageLimit * pageLimit + 1;
		
		//* endPage : 페이지 하단에 보여질 페이징바의 끝수
		endPage = startPage + pageLimit -1;
		
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		//페이지 정보드를 하나의 공간에 담아서 보내기
		//페이지 정보를 담은 vo클래스를 활용
		// => 사진게시판. 공지사항 게시판에서도 사용할 수 있기 때문에 common
		
		//1. 페이징바 만들때 필요한 객체
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage , endPage);
				
		//2. 현재 사용자가 요청한 페이지에 보여줄 페이징바 객체 저달
		//request.setAttribute("pi", pi);
		
		// 페이징 처리 끝
		
		
		
		
		ArrayList<Camp> list = new CampService().selectCampList(pi);
		
		System.out.print(list);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/camp/campSearch.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
