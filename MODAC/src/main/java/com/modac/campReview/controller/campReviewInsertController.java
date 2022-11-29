package com.modac.campReview.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.modac.campReview.model.service.CampReviewService;
import com.modac.campReview.model.vo.CampReview;
import com.modac.campReview.model.vo.MyFileRenamePolicy;
import com.modac.common.model.vo.Attachment;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class campReviewInsertController
 */
@WebServlet("/insert.cr")
public class campReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public campReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 10;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/campReview_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
					
			String memberNo = multiRequest.getParameter("memberNo");
			String postTitle = multiRequest.getParameter("title");
			String postContent = multiRequest.getParameter("content");
			
			String[] tagNo = multiRequest.getParameterValues("tag"); 
			
			CampReview cr = new CampReview();
			cr.setPostTitle(postTitle);
			cr.setPostContent(postContent);
			cr.setMemberNo(memberNo);
			

			ArrayList<Integer> tagList = new ArrayList<>();
			for(int i=0; i<tagNo.length; i++){
			    tagList.add(Integer.parseInt(tagNo[i]));
			}
			cr.setTagList(tagList);
			
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));// 원본명
				at.setNewName(multiRequest.getFilesystemName("upfile"));//수정명(실제 서버에 업로드되어있는 파일명)
				at.setPath("resources/campReview_upfiles/");
			}else {
				at = new Attachment();
	            at.setOriginName("logo.png");
	            at.setNewName("logo.png");
	            at.setPath("/resources/modacLogo/");
	         }
			
		int result = new CampReviewService().insertCampReview(cr, at);
		
		System.out.println();

		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "게시글 작성 성공!");
			response.sendRedirect(request.getContextPath()+"/list.cr");
		
		}else { //실패시 => 첨부파일 있엇을경우 이미 업로드된 첨부파일을 서버에 보관할 이유가 없다(용량만차지)
			if(at != null) {
				// 삭제시키고자 하는 파일객체 생성 -> delete메소드 호출
				new File(savePath+at.getNewName()).delete();
			}
			
			request.setAttribute("errorMsg","게시글 작성 실패");
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
