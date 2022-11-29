<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.modac.notice.model.vo.Notice, com.modac.common.model.vo.Attachment, java.util.ArrayList"%>
<%
	Notice n = (Notice)request.getAttribute("n");
	//Attachment at = (Attachment)request.getAttribute("at");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head><script type="text/javascript" src="/___vscode_livepreview_injected_script"></script>
<meta charset="UTF-8">
<title>모닥불 소식 상세페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');

      .content1{
          width: 20%;
		  height: 1000px;
	      padding: 50px 20px 10px;
	      background-color: antiquewhite;
	      float: left; 
        }

        .content2{
            width: 80%;
		    padding: 10px 50px 20px;
            float: left; 
        }
        
        .form-control {
          margin: 5px;
        }
        
        .date{
          float: right; 
        }

        #contentBox{
          resize: none;
          border: none;
          text-align:left;
        }
        
        .form-control:disabled{
          background-color : white;
        }
        
       .foorm-control {
		    display: block;
		    width: 100%;
		    padding: 0.375rem 0.75rem;
		    font-size: 1rem;
		    font-weight: 400;
		    line-height: 1.5;
		    color: #495057;
		    background-color: #fff;
		    background-clip: padding-box;
		    border: 1px solid #ced4da;
		    border-radius: 0.25rem;
		    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
		}


		.list-area { 
		 	width: 80%; 
			margin: auto;
		 }
		 
		 .list-title{
       		margin-top: 40px;
      		color: rgb(74,57,51);
      		font-family: 'Hahmlet', serif;
      		font-size: 35px;
		 }
		 
		 hr{
 			border: 0;
    		height: 1px;
    		background: #ccc;"
 		}
 		
	 	.sidemenu {
	    	font-family: 'Do Hyeon', sans-serif;
	        color: #4a3933;
	        font-size: 30px;
	    }
	    .sidemenu2 {
	        font-family: 'Do Hyeon', sans-serif;
	        color: #4a3933;
	        font-size: 20px;
	    }
</style>

</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="content">


		<div class="content1">
			<nav class="flex-column">
				<a class="nav-link active sidemenu" aria-current="page"
					href="<%=contextPath %>/noticeList"><h3>공지사항</h3></a><br>
				<br> <a class="nav-link sidemenu2" href="<%=contextPath %>/noticeList">모닥불 소식</a> 
				<a class="nav-link sidemenu2" href="<%=contextPath%>/campTipList">캠핑 팁</a>
				<a class="nav-link sidemenu2" href="<%=contextPath %>/qaList">Q&A</a>
				<a class="nav-link sidemenu2" href="<%=contextPath %>/faqList">FAQ</a>
			</nav>
		</div>


		<div class="content2">
			<br>
			<div class="list-area">
			<h3 class="list-title">모닥불 소식</h3>
			<br>
			<div align="right" class="insert-area">

				<!-- 관리자들은 본인 게시글 상관없이 공지사항 게시판 관리 가능 -->
				<% if(loginMember != null && loginMember.getMemberLevel() == 10) {%>
				<a href="<%=contextPath %>/updateForm.no?nno=<%=n.getNoticeNo()%>"
					class="btn text-white last1" style="background-color: orange;">수정하기</a> <a
					href="<%=contextPath %>/deleteNotice?nno=<%=n.getNoticeNo()%>"
					class="btn text-white last1" style="background-color: #BDBDBD;">삭제하기</a>
				<% } %>

			</div>
			
			<br>

			<div class="foorm-control">
				<input type='hidden' name="noticeNo" value="<%= n.getNoticeNo() %>">
				<br>
				<h3>&nbsp; <%=n.getNoticeTitle() %></h3>
				<br> 
				
				<span>&nbsp; <%=n.getNoticeWriter() %></span> 
				<span class="date"><%=n.getCreateDate() %>&nbsp;&nbsp;</span> 
				
				<br>
				<hr>
				
				<% for(int i = 0; i<list.size(); i++){ %>
						<img src="<%= contextPath%>/<%= list.get(i).getPath()+list.get(i).getNewName() %>" width="430" height="320">
				<%} %>
				
					
				<textarea class="foorm-control" style="height: 500px; text-align:left;" id="contentBox" disabled><%=n.getNoticeContent()%></textarea>
				<% if(list.isEmpty()) {%>
					첨부파일 없음
				<%}else{
				  	int i = 1;%>
				<% for(Attachment at : list){ %>
					<a href="<%=contextPath %>/<%= at.getPath() %><%=at.getNewName() %>" download="<%=at.getOriginName()%>" name="upfile<%=i %>" value="<%=at.getFileLevel()%>"> 
						<%= at.getOriginName() %>
					</a>
				<%	i++;
			  		} %>
				<%} %>
			</div>
			<br>
			<div align="center">
				<a href="<%=contextPath%>/noticeList"
					class="btn text-white" style="background-color: orange;">목록으로</a>

			</div>
			<br> <br>
			</div>
		</div>


	</div>



	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>