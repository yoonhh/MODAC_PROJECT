package com.modac.QA.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.modac.QA.model.service.QaService;
import com.modac.QA.model.vo.Qa;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.MyFileRenamePolicy;
import com.modac.notice.model.service.NoticeService;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class QaInsertController
 */
@WebServlet("/qaInsert")
public class QaInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1. 전송 파일 용량제한
			int maxSize = 1024 * 1024 * 10;
			
			// 2. 전달될 파일을 저장할 서버의 폴더 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/qa_upfiles/");
			
			// 3. 전달된 파일명 수정 및 서버에 업로드 작업
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String qaTitle = multiRequest.getParameter("title");
			String qaContent = multiRequest.getParameter("content");
			String qaWriter = multiRequest.getParameter("qaWriter");//추가하기
			String hiddenPost = multiRequest.getParameter("hiddenPost");
			String postPwd = multiRequest.getParameter("postPwd");
			
			Qa q = new Qa();
			
			q.setQaTitle(qaTitle);
			q.setQaContent(qaContent);
			q.setMemberNo(qaWriter);
			System.out.println("hiddenPost"+hiddenPost);
			if(hiddenPost != null) {
				q.setHiddenPost(hiddenPost);
				q.setPostPwd(postPwd);
			}
				
			
			ArrayList<Attachment> list = new ArrayList<>();
			
			for (int i = 1; i <= 4; i++) { // 파일의 갯수는 최대 4개. file1, file2, file3, file4

				String key = "upfile" + i;

				if (multiRequest.getOriginalFileName(key) != null) {

					// 첨부파일이 있는 경우
					// Attachment 객체 생성 + 원본명, 수정명, 파일경로 + 파일레벨 담기
					// list에 추가하기
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setNewName(multiRequest.getFilesystemName(key));
					at.setPath("/resources/qa_upfiles/");
					at.setFileLevel(i);

					list.add(at);
				}

			}

			int result = new QaService().insertQa(q, list);

			if (result > 0) { // 성공 -> list.th 재요청
				request.getSession().setAttribute("alertMsg", "성공적으로 업로드 되었습니다.");
				response.sendRedirect(request.getContextPath() + "/qaList");
			} else { // 실패 => 에러페이지
				request.setAttribute("errorMsg", "Q&A 업로드 실패");
				request.getRequestDispatcher("views/common/errorPage2.jsp").forward(request, response);
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
