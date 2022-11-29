<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList , com.modac.notice.model.vo.Notice, 
    com.modac.member.model.vo.Member, com.modac.common.model.vo.PageInfo"%>
    
<%ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<%
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
		height: 1000px;
		float: left;
	}
	
	.content1 {
		width: 20%;
		height: 100%;
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
    	margin-right : 110px;
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
                <h3 class="list-title">모닥불 소식</h3>
                <br>
                <div class="searchbar">
                    <nav class="navbar">
							<form class="container-fluid">
                         		<div class="input-group">
                            		<select class="form-select" name ="f" aria-label="Default select example" style="width:25%;">
                                	<option  ${(param.f == "NOTICE_TITLE")? "selected":""} value="NOTICE_TITLE">제목</option>
                                	<option  ${(param.f == "NOTICE_CONTENT")? "selected":""} value="NOTICE_CONTENT">내용</option>
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
                        <a id="writeEdit" class="btn text-white" style="background-color: orange;" href="<%=contextPath %>/noticeEnrollForm">글 작성</a>
                    </div>
                <% } %> 

                <br><br><br>
                
                <div class="list-area">
                <table class="table table-hover" style="text-align: center;">
                    <thead>
                      <tr>
                        <th scope="col" width="90px;" >글번호</th>
                        <th scope="col" width="450px;">제목</th>
                        <th scope="col" width="150px;">작성자</th>
                        <th scope="col" width="130px;">등록일자</th>
                        <th scope="col" width="80px;">조회수</th>
                      </tr>
                    </thead>
                    <tbody>
						<%
							if (list.isEmpty()) {
						%>
						<!-- 리스트가 비어있는 경우 -->
						<tr>
							<td colspan="5">존재하는 공지사항이 없습니다.</td>
						</tr>
						<%
							} else {
						%>
						<%
							for (Notice n : list) {
						%>
						<%
							if (n.getNoticeCategory() != 1) {
						%>
						<tr style="background-color: rgb(250, 241, 241)">
							<td style="display:none;"><%=n.getNoticeNo()%></td>
							<th scope="row"><span class="badge text-bg-danger">공지</span></th>
							<td><%=n.getNoticeTitle()%></td>
							<td><%=n.getNoticeWriter()%></td>
							<td><%=n.getCreateDate()%></td>
							<td><%=n.getCount()%></td>
						</tr>

						<%
							} else {
						%>
						<tr>
							<th scope="row"><%=n.getNoticeNo()%></th>
							<td><%=n.getNoticeTitle()%></td>
							<td><%=n.getNoticeWriter()%></td>
							<td><%=n.getCreateDate()%></td>
							<td><%=n.getCount()%></td>
						</tr>
						<%
							}
						%>
						<%
							}
						%>
						<%
							}
						%>

					</tbody>
                  </table>
                  </div>
		
			<script>
				$(function(){
						$(".list-area>table>tbody>tr").click(function(){
							// 클릭시 해당 공지사항의 번호를 넘겨야함
							// 해당 tr요소의 자손중에서 첫번째 td의 영역의 내용이 필요하다.
							
							let nno = $(this).children().eq(0).text(); 
							// 현재 내가 클릭한 tr의 자손들중 0번째에 위치한 자식의 textnode내용을 가져온다
							
							// 요청할 url?키=밸류&키=밸류&키=밸류
							// 물음표 뒤의 내용을 쿼리스트링이라고 부른다. => 직접 만들어서 넘겨야함.
							location.href = '<%=contextPath%>/noticeDetail?nno='+ nno;
						});
					})
	
	        </script>
        
        	<br>
       		<div align="center" class="paging-area">
	         <% if(currentPage != 1) {%>
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
					location.href = "<%=contextPath%>/noticeList?currentPage="+currentPage;
				}
			</script>
		</div>
        </div>





	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></scrip>
</body>
</html>