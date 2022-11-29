package com.modac.noticeTip.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.MyFileRenamePolicy;
import com.modac.member.model.vo.Member;
import com.modac.notice.model.service.NoticeService;
import com.modac.noticeTip.model.service.NoticeTipService;
import com.modac.noticeTip.model.vo.NoticeTip;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NoticeTipUpdateController
 */
@WebServlet("/updateNoticeTip")
public class NoticeTipUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeTipUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "캠핑 팁 수정 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
	
		request.setCharacterEncoding("UTF-8");
		
		if (ServletFileUpload.isMultipartContent(request)) {
			// 1_1. 전송파일의 용량제한
			int maxSize = 10 * 1024 * 1024;
			// 1_2. 전달된 파일ㅇ르 저장시킬 서버의 폴더의 물리적인 경로 알아내기

			String savePath = request.getSession().getServletContext().getRealPath("/resources/campTip_upfiles/");

			// 2. 전달된 파일명 수정작업 후 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());

			String noticeTipNo = multiRequest.getParameter("postNo");
			String noticeTipTitle = multiRequest.getParameter("title");
			String noticeTipContent = multiRequest.getParameter("content");
			String url = multiRequest.getParameter("link");
			
			NoticeTip nt = new NoticeTip();
			
			nt.setPostNo(noticeTipNo);
			nt.setPostTitle(noticeTipTitle);
			nt.setPostContent(noticeTipContent);
			nt.setUrl(url);
			
			String key = "upfile";
			Attachment at = new Attachment();
			
			if (multiRequest.getOriginalFileName(key) != null) {
				
				at.setOriginName(multiRequest.getOriginalFileName(key));
				at.setNewName(multiRequest.getFilesystemName(key));
				at.setPath("resources/campTip_upfiles/");
				at.setFileLevel(1);
				
				if (multiRequest.getParameter("originFileNo") != null) {
					// 기존에 파일이 있는경우
					// => update Attachment
					// 기존의 파일번호를 가지고
					at.setPhotoNo(multiRequest.getParameter("originFileNo"));
					
					// 기존의 첨부파일 삭제
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
				}
			}
			
			int result = new NoticeTipService().updateNoticeTip(nt, at);
			// case1 : 새로운 첨부파일이 없는경우(x) => c, null =>Board Update만 실행
			// case2 : 새로운 첨부파일이 있는경우(o), 기존 첨부파일도 있는경우(o) => b, at에 fileNo => Board Update, Attachment update 두번실행
			// case3 : 새로운 첨부파일 있는경우(o), 기존 첨부파일은 없는경우(x) => b, at에 refNo = >Board update, Attachment Insert

			if (result > 0) { // 수정성공=>상세조회 페이지
				request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath() + "/campTipList");
			} else {// 수정실패 => errorPage
				request.setAttribute("errorMsg", "게시글 수정에 실패했습니다.");
				request.getRequestDispatcher("views/common/errorPage3.jsp").forward(request, response);
			}
			
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
