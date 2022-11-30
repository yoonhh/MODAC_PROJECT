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
 * Servlet implementation class CheckFindController
 */
@WebServlet("/campSearch.ca")
public class CheckFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckFindController() {
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
		
		
		// 지역 검색
		String loc1 = request.getParameter("loc1");
		String loc2 = request.getParameter("loc2");
		
		// checkbox 검색
		String [] item1 = request.getParameterValues("item1");
		String pet = request.getParameter("pet");
		System.out.println(loc1 + loc2+ item1 + pet);
		
		ArrayList<Camp> clist = new ArrayList<>();
		
		switch(loc1) {
			case "a" : loc1 = "강원도"; break;
			case "b" : loc1 = "경기도"; break;
			case "c" : loc1 = "경상남도"; break;
			case "d" : loc1 = "경상북도"; break;
			case "e" : loc1 = "전라남도"; break;
			case "f" : loc1 = "전라북도"; break;
			case "g" : loc1 = "충청남도"; break;
			case "h" : loc1 = "충청북도"; break;
			case "i" : loc1 = "제주도"; break;
			case "j" : loc1 = "광주시"; break;
			case "k" : loc1 = "서울시"; break;
			case "l" : loc1 = "세종시"; break;
			case "m" : loc1 = "대전시"; break;
			case "n" : loc1 = "인천시"; break;
			case "o" : loc1 = "대구시"; break;
			case "p" : loc1 = "울산시"; break;
			case "q" : loc1 = "부산시"; break;
		}
		
		if(loc1.equals("z") && item1 == null && pet == null) {
			
			// * listCount : 총 게시글 갯수
			listCount = new CampService().selectListCount();
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
			
			
			
			ArrayList<Camp> list = new CampService().selectCampList(pi);
			
			System.out.print(list);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.getRequestDispatcher("views/camp/campSearch.jsp").forward(request, response);
			
		} else {
			
			if(item1 == null && pet == null) { // 지역 검색
				
				listCount = new CampService().locationListCount(loc1, loc2);
				
				currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
				
				pageLimit = 5;
				
				boardLimit = 8;
				
				maxPage = (int)Math.ceil((double)listCount / boardLimit);
				
				startPage = (currentPage -1) / pageLimit * pageLimit + 1;
				
				endPage = startPage + pageLimit -1;
				
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage , endPage);
				
				clist = new CampService().cSelect(loc1, loc2, pi);
				System.out.println(clist);
				request.setAttribute("pi", pi);
			
			
			} else if(loc1.equals("z")) { // 체크박스 검색
				
				listCount = new CampService().checkBoxListCount(item1, pet);
				
				currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
				
				pageLimit = 5;
				
				boardLimit = 8;
				
				maxPage = (int)Math.ceil((double)listCount / boardLimit);
				
				startPage = (currentPage -1) / pageLimit * pageLimit + 1;
				
				endPage = startPage + pageLimit -1;
				
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage , endPage);

				
				clist = new CampService().cSelect(item1, pet, pi);
				System.out.println(clist);
				request.setAttribute("pi", pi);
				
			} else { // 모든 검색 이용
				
				
				listCount = new CampService().allCount(loc1, loc2, item1, pet);
				
				currentPage = Integer.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));
				
				pageLimit = 5;
				
				boardLimit = 8;
				
				maxPage = (int)Math.ceil((double)listCount / boardLimit);
				
				startPage = (currentPage -1) / pageLimit * pageLimit + 1;
				
				endPage = startPage + pageLimit -1;
				
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage , endPage);
				
				
				clist = new CampService().cSelect(loc1, loc2, item1, pet, pi);
				System.out.println(item1);
				
				request.setAttribute("pi", pi);
			}
			
			System.out.println(loc1);
			System.out.println(loc2);
			
			request.setAttribute("clist", clist);
			request.setAttribute("item1", item1);
			System.out.println(" ");
			System.out.println(item1);
			
			request.getRequestDispatcher("views/camp/campCheckSearch.jsp").forward(request, response);
			
			
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
