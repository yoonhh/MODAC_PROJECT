package com.modac.circle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.modac.circle.model.service.CircleBoardService;
import com.modac.circle.model.vo.Circle;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class CircleInsertController
 */
@WebServlet("/cinsert.bo")
public class CircleInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CircleInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024*1024*10;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/circle_upfiles/");
		
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());	
			
		String postContent = multiRequest.getParameter("content");
		String postTitle = multiRequest.getParameter("title");
		String memberNo = multiRequest.getParameter("memberNo");
		
		
		Circle c = new Circle();
		c.setPostContent(postContent);
		c.setPostTitle(postTitle);
		c.setMemberNo(memberNo);
		
		Attachment at = null;
		
		//multiRequest.getOriginalFileName("키")
		//=> 첨부파일이 있었을 경우 원본명 없을경우, null이 뜸.
		if(multiRequest.getOriginalFileName("upfile")!=null) {
			at = new Attachment();
			at.setOriginName(multiRequest.getOriginalFileName("upfile"));//원본명
			at.setNewName(multiRequest.getFilesystemName("upfile"));// 수정명(실제 서버에 업로드 되었는파일명)
			at.setPath("resources/circle_upfiles/");
		
	}
		
		int result = new CircleBoardService().insertBoard(c,at);
		
		if(result>0){// 성공=>list.bo?currentPage=1
			request.getSession().setAttribute("alertMsg", "게시글 작성 성공!");
			response.sendRedirect(request.getContextPath()+"/clist.bo"); 
		}else {//실패시 => 첨부파일 있었을경우 이미 업로드된 첨부파일을 서버에 보관할 이유가 없다(용량만 차지)
			
			
			request.setAttribute("errorMsg", "게시글 작성 실패");
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
