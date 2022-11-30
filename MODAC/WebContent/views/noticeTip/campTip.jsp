<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList , com.modac.noticeTip.model.vo.NoticeTip, 
    com.modac.member.model.vo.Member, com.modac.common.model.vo.PageInfo, com.modac.common.model.vo.Attachment"%>
    
<%
    ArrayList<NoticeTip> ntlist = (ArrayList<NoticeTip>) request.getAttribute("ntlist");
    PageInfo pi = (PageInfo) request.getAttribute("pi");

    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모닥불 소식 메인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');

	.content>div {
		height: 1300px;
		float: left;
	}
	
	.content1 {
		width: 20%;
		height: 1300px;
		padding: 50px 20px 10px;
		background-color: antiquewhite;
		float: left;
	}
	
	.content2 {
		width: 80%;
		padding: 50px 50px 20px;
		float: left;
	}
	
	.searchbar {
		margin-top: 20px;
		display: table;
		margin-left: auto;
		margin-right: auto;
	}
	
	#writeEdit {
		float: right;
	}
	
	.list-area {
		width: 80%;
		margin: auto;
	}
	
	.input-group-text{
		text-decoration: none;
	}
	
	.moveBtn{
       color: white;
       background-color: rgb(74,57,51);
       border : none;
       width: 80px;
       border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px
    }
    .pageBtn{
       color: black;
       background-color: gainsboro;
       border-radius: 50%;
       border: gainsboro;
       width: 30px;
       height: 30px;
    }
    .pageBtn:hover{
       width: 30px;
       height: 30px;
       color: white;
       background-color: orange;
    }
    
     .navbar{
    	width: 600px;
    }
    
    .insertBtn{
    	margin-right : 155px;
    }
    
    .list-title{
    	text-align:center;
       	font-family: 'Do Hyeon', sans-serif;
        color: #4a3933;
        font-size: 45px;
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
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>

	<%@ include file="../common/menubar.jsp" %>
        <div class="content">
            <div class="content1">
                <nav class="flex-column">
                    <a class="nav-link active sidemenu" aria-current="page" href="<%=contextPath %>/noticeList"><h3>공지사항</h3></a> <br><br>
                    <a class="nav-link sidemenu2" href="<%=contextPath %>/noticeList">모닥불 소식</a> 
                    <a class="nav-link sidemenu2" href="<%=contextPath%>/campTipList">캠핑 팁</a> 
                    <a class="nav-link sidemenu2" href="<%=contextPath %>/qaList">Q&A</a> 
                    <a class="nav-link sidemenu2" href="<%=contextPath %>/faqList">FAQ</a>
                </nav>
            </div>
            <div class="content2">
                <h3 class="list-title">캠핑 팁</h3>
                <br>
                <div class="searchbar">
                   <nav class="navbar">
							<form class="container-fluid">
                         		<div class="input-group">
                            		<select class="form-select" name ="f" aria-label="Default select example" style="width:25%;">
                                	<option  ${(param.f == "POST_TITLE")? "selected":""} value="POST_TITLE">제목</option>
                                	<option  ${(param.f == "POST_NIC")? "selected":""} value="POST_CONTENT">내용</option>
                               		</select>
                            	<input type="text" name ="q" class="form-control" placeholder="검색어를 입력하세요" aria-label="Username" aria-describedby="basic-addon1" style="width: 60%;" value="${param.q}">
                            	<input type="submit" class="input-group-text" id="basic-addon1" value="검색">
                        		</div>
                        	</form>
							<br>
					</nav>
                </div>

                 <% if(loginMember != null && loginMember.getMemberLevel() == 10){ %>
                    <div class="insertBtn">
                        <a id="writeEdit" class="btn text-white" style="background-color: orange;" href="<%=contextPath %>/noticeTipEnrollForm">글 작성</a>
                    </div>
                <% } %> 

                <br><br><br>
                
             <div class="list-area">
             	<%
					if (ntlist.isEmpty()) {
				%>
					<div class="card mb-3" style="max-width: 800px; float:none; margin:0 auto">
						<br><p style="text-align:center;">리스트가 존재하지 않음</p>
					</div>
             	<%
					} else {
				%>
				<%
					for (NoticeTip nt : ntlist) {
				%>
				<div class="card mb-3" style="max-width: 800px; float:none; margin:0 auto">
					<div class="row g-0" id="pageLink" onClick="window.open('<%= nt.getUrl()%>')">
						<div class="col-md-4">
							<% 
								String campThumb = "";
								if(nt.getAt() == null){
									campThumb = "resources/modacLogo/최종로고_1.png";
								}else{
									campThumb = nt.getAt().getPath()+ nt.getAt().getNewName(); 
								}
							%>
							<img src="<%= contextPath%>/<%= campThumb %>" class="img-fluid rounded-start" alt="썸네일">
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<input type="hidden" name="postNo" value="<%=nt.getPostNo()%>">
								<h5 class="card-title" name="title"><%= nt.getPostTitle() %></h5>
								<p class="card-text" name="content"><%= nt.getPostContent() %></p>
								<input type="hidden" name="link" value="<%=nt.getUrl() %>">
								<p class="card-text">
									<small class="text-muted" name="createDate"><%=nt.getCreateDate() %></small>
								</p>

							</div>
						</div>
					</div>
					<div>
					<%
						if(loginMember != null && loginMember.getMemberLevel() == 10) {
					%>
						<a href="<%=contextPath%>/delete.nt?ntNo=<%=nt.getPostNo() %>" class="btn text-white btn-sm last1" style="float:right; margin: 2px; background-color: #BDBDBD;">삭제하기</a>
						<a href="<%=contextPath%>/updateForm.nt?ntNo=<%=nt.getPostNo()%>"
						class="btn text-white btn-sm last1"  style="float:right; margin: 2px; background-color: orange;">수정하기</a> 
					<% }%>
					</div>
				</div>
				<%
					}
				}
				%>
				
			</div>
		

        	
        	<br>
       		<div align="center" class="paging-area">
	         <% if(currentPage != 1) { %>
	            <button class="moveBtn" onclick="doPageClick(<%=currentPage -1 %>)">&lt;이전</button>
	         <% } %>
         
         	 <% for(int i = startPage; i <= endPage; i++) { %>
             <%if(i != currentPage) {%>
               <button  class="pageBtn" onclick="doPageClick(<%=i%>)"><%=i %></button>
             <%} else {%>
               <button class="pageBtn" style="background-color: orange; color: white;" disabled><%= i %></button>
             <%} %>
         	 <% } %>
         
	         <% if(currentPage != maxPage) { %>
	            <button class="moveBtn" onclick="doPageClick(<%=currentPage +1 %>)">&gt;다음</button>
	         <% } %>
      		</div>
			
			<script>
				function doPageClick(currentPage){
					location.href = "<%=contextPath%>/campTipList?currentPage="+currentPage;
				}
			</script>
		</div>
        </div>





	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></scrip>
</body>
</html>