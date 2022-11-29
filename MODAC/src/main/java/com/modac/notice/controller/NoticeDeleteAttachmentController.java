package com.modac.notice.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.notice.model.service.NoticeService;
import com.modac.common.model.vo.*;

/**
 * Servlet implementation class NoticeDeleteAttachmentController
 */
@WebServlet("/delete.at")
public class NoticeDeleteAttachmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteAttachmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String photoNo = request.getParameter("photoNo");
	      String savePath = request.getSession().getServletContext().getRealPath("/resources/notice_upfiles/");
	      String newName = request.getParameter("newName");
	      
	      //미리보기 사진 파일경로
	      File file = new File(savePath+newName);
	      System.out.println("ff :" +file);
	      
	      if(file.exists()) {
	         //사진이 존재하면 사진 삭제
	         boolean result = file.delete();
	         //삭제됐다면 결과 반환
	         if(result == true) {
	            response.getWriter().print("NNNNY");
	         }
	         System.out.println("dd :"+result);
	      }
	      
	      //DB에서 미리보기 사진 상태값을 Y로 변경
	     new NoticeService().deleteUpfile(photoNo);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
