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
import com.modac.noticeTip.model.service.NoticeTipService;
import com.modac.noticeTip.model.vo.NoticeTip;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NoticeTipInsertController
 */
@WebServlet("/insert.nt")
public class NoticeTipInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeTipInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!(request.getSession().getAttribute("loginMember") != null &&
				((Member)request.getSession().getAttribute("loginMember")).getMemberLevel() == 10)){
			request.setAttribute("errorMsg", "캠핑 팁 등록 권한이 없습니다.");
			request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
			return;
		}
		
		request.setCharacterEncoding("UTF-8");

		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1. 전송 파일 용량제한
			int maxSize = 1024 * 1024 * 10;
			
			// 2. 전달될 파일을 저장할 서버의 폴더 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/campTip_upfiles/");
			
			// 3. 전달된 파일명 수정 및 서버에 업로드 작업
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 4. DB에 기록할 데이터들을 뽑아서 Attachment객체에 담기
		
			String noticeTipTitle = multiRequest.getParameter("title");
			String noticeTipContent = multiRequest.getParameter("content");
			String writer = multiRequest.getParameter("writer");
			String url = multiRequest.getParameter("link");
			
			NoticeTip nt = new NoticeTip();
			
			nt.setPostTitle(noticeTipTitle);
			nt.setPostContent(noticeTipContent);
			nt.setWriter(writer);
			nt.setUrl(url);
			
			Attachment at = new Attachment();
			//multiRequest.getOriginalFileName("키")
			if(multiRequest.getOriginalFileName("upfile") != null) {
				at.setOriginName(multiRequest.getOriginalFileName("upfile")); // 원본명
				at.setNewName(multiRequest.getFilesystemName("upfile")); // 수정명
				at.setPath("resources/campTip_upfiles/"); 
			}else {
				at.setOriginName("최종로고_1.png");
				at.setNewName("최종로고_1.png");
				at.setPath("/resources/modacLogo/");
				at.setFileLevel(1);
	               
	            }
			
			// 4. 서비스 요청
			int result = new NoticeTipService().insertNoticeTip(nt, at);
			
			if(result > 0) { // 성공
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/campTipList");
			}else { // 실패시
				if(at != null) {
					new File(savePath + at.getNewName()).delete();
				}
				request.setAttribute("errorMsg", "게시글 작성 실패");
				request.getRequestDispatcher("views/common/errorPage3.jsp").forward(request, response);
			}
						 
//			Enumeration e = multiRequest.getFileNames();
//			int i =1;
//			while(e.hasMoreElements()) {
//				// 첨부파일이 있는 경우
//				// Attachment 객체 생성 + 원본명, 수정명, 파일경로 + 파일레벨 담기
//				// list에 추가하기
//				Attachment at = new Attachment();
//				String key = (String)e.nextElement();
//				System.out.println(key);
//				
//				at.setOriginName(multiRequest.getOriginalFileName(key));
//				at.setNewName(multiRequest.getFilesystemName(key));
//				at.setPath("/resources/notice_upfiles/");
//				at.setFileLevel(i++);
//
//				list.add(at);
//			}
//		
//
//			int result = new NoticeService().insertNotice(n, at);
			
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
