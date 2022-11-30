package com.modac.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modac.member.model.service.MemberService;
import com.modac.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩 설정
				request.setCharacterEncoding("UTF-8");
				// 2) 요청시 전달받은 값을 뽑아서 변수 및 객체에 담기
				String memberId = request.getParameter("memberId");
				String memberName = request.getParameter("memberName");
				String memberNic = request.getParameter("memberNic");
				String email = request.getParameter("email");
				
				
				//Member 객체 생성
				Member m = new Member(memberId, memberName, memberNic, email);
				
				//3) 요청 처리
				Member updateMem = new MemberService().updateMember(m);
				
				//4) 돌려받은 처리결과를 가지고 사용자가 보게될 응담화면 지정.
				if(updateMem == null) {
					request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				}else {
					HttpSession session = request.getSession();
					session.setAttribute("loginMember", updateMem);
					session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다." );
					
					//응답페이지 => /jsp/myPage.me
					//url 재요청
					response.sendRedirect(request.getContextPath()+"/myPage.me");
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
