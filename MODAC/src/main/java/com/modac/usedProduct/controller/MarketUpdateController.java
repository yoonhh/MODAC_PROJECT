package com.modac.usedProduct.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.modac.common.MyFileRenamePolicy;
import com.modac.usedProduct.model.service.MarketService;
import com.modac.usedProduct.model.vo.Attachment;
import com.modac.usedProduct.model.vo.Market;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MarketUpdateController
 */
@WebServlet("/update.mk")
public class MarketUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//enctype이 mutipart/form-data인지 확인 후 true라면 코드 실행
		if(ServletFileUpload.isMultipartContent(request)) {
			//1-1 전송파일의 용량제한
			int maxSize = 10 * 1024 * 1024;
			
			//1-2 전달된 파일을 저장시킬 서버의 폴더의 물리적인 경로 알아내기
			String savePath = request.getSession().getServletContext().getRealPath("/resources/market_upfiles/");
			
			//2 전달된 파일명 수정 작업 후 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			//3 본격적으로 sql문 실행시 필요한 값 
			// -board테이블에 update 항상 수행
			String postNo = multiRequest.getParameter("mno");
			String postTitle = multiRequest.getParameter("title");
			String postContent = multiRequest.getParameter("content");
			
			Market m = new Market();
			m.setPostNo(postNo);
			m.setPostTitle(postTitle);
			m.setPostContent(postContent);
			
			
			ArrayList<Attachment> list = new ArrayList<>();
			
			for(int i = 0; i < list.size(); i++) {
				String key = "file" + (i+1);
			
				if(multiRequest.getOriginalFileName(key) != null) {
					//새롭게 전달된 첨부파일이 있는 경우에만 at변수에 필요한 값을 추가
					Attachment at = new Attachment();
					
					//4가지 공통적으로 필요한 변수 셋팅
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setNewName(multiRequest.getFilesystemName(key));
					at.setPath("resources/market_upfiles/");
					at.setFileLevel(i+1);
						
					//첨부파일이 있을경우 원본파일의 파일번호, 수정명을 hidden으로 넘겨 받았음
					if(multiRequest.getParameter("originPhotoNo"+i) != null) {
						//기존에 파일이 있는 경우
						//=>update Attachment 
						//기존의 파일번호를 가지고
						at.setPhotoNo(multiRequest.getParameter("originPhotoNo"+i));
						
						//기존의 첨부파일 삭제
						new File(savePath+multiRequest.getParameter("newPhotoName"+i)).delete();
					}else {
						//기존에 파일이 없는 경우
						//=>insert Attachment
						//ref_bno + 현재 게시물 번호
						at.setPostNo(postNo);
					}
					list.add(at);
				}
			}	
			
			//모두 하나의 트랜잭션으로 처리하기
			int result = new MarketService().updateMarket(m, list);
			
			/*
			 * case1 : 새로운 첨부파일이 없는 경우(x) => b, null => Board Update
			 * case2 : 새로운 첨부파일이 있는 경우(o), 기존 첨부파일이 있는 경우(o) => b, at에 fileNo => Board update, Attachment update
			 * case3 : 새로운 첨부파일이 있는 경우(o), 기존 첨부파일이 없는 경우(x) => b, at에 refNo => Board update, Attachment Insert 
			 */
			if(result > 0 ) { //수정성공 => 상세조회페이지
				request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath()+"/detail.mk?mno="+postNo);
				
			}else { //수정실패 => errorPage
				request.setAttribute("errprMsg", "수정 실패하였습니다.");
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
