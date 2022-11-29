package com.modac.usedProduct.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modac.common.MyFileRenamePolicy;
import com.modac.usedProduct.model.service.MarketService;
import com.modac.usedProduct.model.vo.Attachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MarketDeleteAttachmentController
 */
@WebServlet("/delete.at")
//게시글 수정 페이지 미리보기 사진 삭제
public class MarketDeleteAttachmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketDeleteAttachmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int fileLevel = Integer.parseInt(request.getParameter("fileLevel"));
		//String postNo = request.getParameter("postNo");
		//String filePath = request.getParameter("filePath");
		
		String photoNo = request.getParameter("photoNo");
		String newName = request.getParameter("newName");
		String savePath = request.getSession().getServletContext().getRealPath("/resources/market_upfiles/");
		
		//미리보기 사진 파일경로
		File file = new File(savePath+newName);
		System.out.println(file);
		
		if(file.exists()) {
			//사진이 존재하면 사진 삭제
			boolean result = file.delete();
			
			//DB에서 미리보기 사진 상태값을 Y로 변경
			int result1 = new MarketService().deleteAtt(photoNo);
			
			//삭제됐다면 결과 반환
			if(result == true) {
				response.getWriter().print("NNNNY");
			}
		}
		
		
		
		//System.out.println(result);		
		//System.out.println(fileLevel);
		//System.out.println(postNo);
		//System.out.println(photoNo);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
