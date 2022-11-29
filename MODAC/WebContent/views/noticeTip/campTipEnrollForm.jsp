<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');

	.content>div {
		height: 1000px;
		float: left;
	}
	
	.content1 {
		width: 20%;
		height: 1000px;
		padding: 50px 20px 10px;
		background-color: antiquewhite;
		float: left;
	}
	
	.content2 {
		width: 80%;
		padding: 50px 200px 20px;
		float: left;
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
 			width: 100%; 
 			margin: auto;
 		}
 			
 		.list-title{
			margin-top: 40px;
      		color: rgb(74,57,51);
      		font-family: 'Hahmlet', serif;
      		font-size: 35px;	
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
                    <a class="nav-link active sidemenu" aria-current="page" href="<%=contextPath %>/noticeList"><h3>공지사항</h3></a> <br><br>
                    <a class="nav-link sidemenu2" href="<%=contextPath %>/noticeList">모닥불 소식</a>
                    <a class="nav-link sidemenu2" href="<%=contextPath%>/campTipList">캠핑 팁</a>
                    <a class="nav-link sidemenu2" href="<%=contextPath%>/qaList">Q&A</a>
                    <a class="nav-link sidemenu2" href="<%=contextPath %>/faqList">FAQ</a>
                </nav>
        </div>
        
        <div class="content2">
        
        	<div class="list-area">
            <h3 class="list-title">캠핑 팁</h3>
            <br>
           
           <form id="enroll-form" action="<%=contextPath %>/insert.nt" method="post" enctype="multipart/form-data">
	          <div class="foorm-control" id="form-control">
	          	  <input type="hidden" name="writer" value="<%= loginMember.getMemberNic()%>">
	              <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요." aria-label="title">
	              <br>
	              <div class="input-group mb-3">
  					<span class="input-group-text" id="basic-addon1"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-link-45deg" viewBox="0 0 16 16">
  <path d="M4.715 6.542 3.343 7.914a3 3 0 1 0 4.243 4.243l1.828-1.829A3 3 0 0 0 8.586 5.5L8 6.086a1.002 1.002 0 0 0-.154.199 2 2 0 0 1 .861 3.337L6.88 11.45a2 2 0 1 1-2.83-2.83l.793-.792a4.018 4.018 0 0 1-.128-1.287z"/>
  <path d="M6.586 4.672A3 3 0 0 0 7.414 9.5l.775-.776a2 2 0 0 1-.896-3.346L9.12 3.55a2 2 0 1 1 2.83 2.83l-.793.792c.112.42.155.855.128 1.287l1.372-1.372a3 3 0 1 0-4.243-4.243L6.586 4.672z"/>
</svg></span>
  					<input type="text" class="form-control" name="link" placeholder="링크를 입력해 주세요" aria-label="Username" aria-describedby="basic-addon1">
				  </div>
	              <input type="file" class="form-control" name="upfile" value="1"><br>
	              <textarea class="form-control" style="height:400px; resize: none;" name="content"></textarea>
	          </div>
	          
	          	<br><br>
		        
		        <div align="center">
		                
		          <button type="button" class="btn text-white" style="background-color: #BDBDBD;" class="last1" onclick="history.back();">이전으로</button>
		
		          <button type="submit" class="btn text-white" style="background-color: orange;" class="last1">등록하기</button>
		        </div>
		  </form>
		 </div>
        </div>
    </div>
	
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>
