package com.modac.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modac.circle.model.service.CircleBoardService;
import com.modac.circle.model.vo.Circle;
import com.modac.common.model.vo.PageInfo;
import com.modac.member.model.dao.MemberDao;
import com.modac.member.model.service.MemberService;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class MyPageSelfController
 */
@WebServlet("/myPagecSelf.me")
public class MyPagecSelfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPagecSelfController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount;// 현재 총 게시글 개수
		int currentPage;// 현재 페이지(즉, 사용자가 요청한 페이지)
		int pageLimit;// 페이지 하단에 보여질 페이징바의 페이지 최대 개수
		int boardLimit;//페이지에 보여질 게시글의 최대개수
		
		int maxPage; // 가장 마지막 페이지가 몇번째 페이지 인지( 총 페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage;// 페이지 하단에 보여질 페이징바의 끝 수
		
		//* listCount : 총 게시글 개수
		listCount = new MemberService().selectListCount();//107
		
		//* currentPage : 현재페이지
		currentPage = Integer.parseInt(request.getParameter("currentPage")==null ? "1" : request.getParameter("currentPage"));
		
		// *pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대개수(페이지목록들 몇개 단위씩)
		pageLimit = 10;
		
		//*boardLimit : 한 페이지에 보여질 게시글의 최대개수(게시글 몇개 단위씩)
		boardLimit = 10;
		
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = (currentPage - 1)/pageLimit *pageLimit+1;
		
		
		endPage = startPage+pageLimit -1;
		
		
		if(endPage>maxPage) {
			endPage=maxPage;
		}
		
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		
		HttpSession session = request.getSession();
		String memberId = ((Member)session.getAttribute("loginMember")).getMemberId();
		
		//------------------------페이징 처리 끝-------------------------------------
	
		
		
		ArrayList<Circle> list =new MemberService().selectList(pi,memberId);
	
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/member/myPagecSelf.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
