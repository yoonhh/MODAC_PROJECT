package com.modac.usedProduct.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.modac.common.MyFileRenamePolicy;
import com.modac.member.model.vo.Member;
import com.modac.usedProduct.model.service.MarketService;
import com.modac.usedProduct.model.vo.Attachment;
import com.modac.usedProduct.model.vo.Market;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MarketInsertController
 */
@WebServlet("/insert.mk") //게시글 작성
public class MarketInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			//첨부파일 용량
			int maxSize = 20 * 1024 * 1024;
			
			//첨부파일 저장 경로
			String savePath = request.getServletContext().getRealPath("/resources/market_upfiles/");
			
			//전달된 파일명 수정 작업 후 서버에 업로드
			MultipartRequest multirequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", 
					new MyFileRenamePolicy());
			
			//db 저장
			Market m = new Market();
			
			String memberNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
			String postTitle = multirequest.getParameter("title");
			String postContent = multirequest.getParameter("content");
			
			m.setPostTitle(postTitle);
			m.setPostContent(postContent);
			m.setMemberNo(memberNo);
			
			
			//첨부파일 저장
			ArrayList<Attachment> list = new ArrayList<>();
			
			String titleImg = multirequest.getParameter("titleImg");
			
			
			for(int i = 0; i <= 3; i++) {
				Attachment at = new Attachment();
				String key = "file" + (i+1);
				
				if(multirequest.getOriginalFileName(key) != null) { //첨부파일이 있을 경우
					//DB에 저장
					at.setOriginName(multirequest.getOriginalFileName(key));
					at.setNewName(multirequest.getFilesystemName(key));
					at.setPath("/resources/market_upfiles/");
					at.setFileLevel(i+1);
					
					list.add(at);	
				}else { //첨부파일이 없을 경우 폴더에서 로고 사진 가져와서 DB에 저장
					at.setOriginName("logo.png");
					at.setNewName("logo.png");
					at.setPath("/resources/modacLogo/");
					at.setFileLevel(i+1);
					
					list.add(at);
				}
			}
			
			
			int result = new MarketService().insertMarketPost(m , list);
			System.out.println(list);
			
			if( result > 0) { //성공 : list.th 재요청
				request.getSession().setAttribute("alertMsg", "성공적으로 게시되었습니다.");
				response.sendRedirect(request.getContextPath()+"/list.mk");
			}else { //실패 => 에러페이지
				request.setAttribute("errorMsg", "게시글 작성에 실패했습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
