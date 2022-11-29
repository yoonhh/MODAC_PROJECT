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
 * Servlet implementation class fineUpdatePwdController
 */
@WebServlet("/fineupdatePw.me")
public class fineUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fineUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updateMem = new MemberService().fineupdatePwd(memberId, memberName, email, updatePwd);
		System.out.println("updateMem : "+updateMem);
		
//		Member fineupdatePwd = new MemberService().fineupdatePwd(memberId,memberName,email,updatePwd);
		HttpSession session = request.getSession();
		System.out.println(session);
		if(updateMem==null) {
			session.setAttribute("alertMsg", "비밀번호변경에 실패했습니다.");
		}else {
			session.setAttribute("alertMsg", "성공적으로 비밀번호가 변경되었습니다.");
			
		}
		response.sendRedirect(request.getContextPath());
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
