<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList , com.modac.circle.model.vo.Circle , com.modac.common.model.vo.PageInfo" %>
<%
	ArrayList<Circle> list = (ArrayList<Circle>)request.getAttribute("list");
	
PageInfo pi = (PageInfo) request.getAttribute("pi");
int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();

	
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/___vscode_livepreview_injected_script"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
	
	
        

        .content2 {
		width: 100%;
		padding: 10px 50px 20px;
		float: left;
	}
	
	.navbar {
		width: 450px;
	}
	
	.navbar, .btn-group {
		margin-top: 20px;
		display: table;
		margin-left: auto;
		margin-right: auto;
	}
	
	.active {
		font-size: large;
		font-weight: 600;
	}
	
	.write {
		float: right;
	}
	
	.list-area {
		width: 80%;
		margin: auto;
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
    	.btn{
 		background-color: #BDBDBD;
       border: #BDBDBD;
       color: white;
 		}
 		h3{
 		text-align:center;
       font-family: 'Do Hyeon', sans-serif;
        color: #4a3933;
        font-size: 45px;
 		}
</style>

</head>
<body>
<%@ include file="../common/menubar.jsp" %>
	<div class="content">
	  
	
		
		<div class="content2">
			<br>
			<h3>동아리 모집</h3>
			<br>
			<div>
				<nav class="navbar">
					<form class="container-fluid">
					
                        <div class="input-group">
                            <select class="form-select" name ="f" aria-label="Default select example" style="width:25%;">
                               
                               
                                <option  ${(param.f == "POST_TITLE")? "selected":""} value="POST_TITLE">제목</option>
                                <option  ${(param.f == "MEMBER_NIC")? "selected":""} value="MEMBER_NIC">작성자</option>
                               </select>
                            <input type="text" name ="q" class="form-control" placeholder="검색어를 입력하세요" aria-label="Username" aria-describedby="basic-addon1" style="width: 60%;" value="${param.q}">
                            <input type="submit" class="input-group-text" id="basic-addon1" value="검색">
                        </div>
                        </form>


					<br>
				</nav>
			</div>

			<div class="list-area">
				<div align="right" class="insert-area">
				<%if(loginMember != null){ %>
	               	<a href="<%=contextPath %>/cenrollForm.bo" class="btn btn-secondary last1" style="background-color: orange;border: #BDBDBD;">글작성</a> 
	            </div>
	            <%} %>
				<br>
				<table class="table table-hover list">
					<thead>
						<tr>
							<th scope="col" style="text-align: center;">글번호</th>
							<th scope="col" width="420px">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">등록일자</th>
							<th scope="col" style="text-align: center;">조회수</th>
						</tr>
					</thead>
					<tbody>
						<% if(list.isEmpty()) {%>
						<tr>
							<th colspan="5">존재하는 게시글이 없습니다.</th>
						</tr>
						<% } else { %>
							<% for(Circle c : list) {%>
								<tr>
									<th scope="row" style="text-align: center;"><%= c.getPostNo() %></th>
									<td><%= c.getPostTitle() %></td>
									<td><%= c.getMemberNic() %></td>
									<td><%= c.getCreateDate() %></td>
									<td style="text-align: center;"><%= c.getReadCount() %></td>
								</tr>
							<% } %>
						<% } %>
					</tbody>
				</table>
			</div>
			<script>
				$(function(){
					$(".list>tbody>tr").click(function(){
						let bno = $(this).children().eq(0).text();
								
						location.href= '<%=contextPath%>/cdetail.bo?bno='+ bno;
					});
				})
			</script>
			
			<br><br>
			
			
 	
 	<div align="center" class = "paging-area">
 		<%if(currentPage !=1){ %>
 		<button class="moveBtn"  onclick="doPageClick(<%=currentPage-1 %>)">&lt;이전</button>
 		
 		<%} %>
 		
 		<% for(int i = startPage; i<=endPage; i++){ %>
 			<%if(i != currentPage){ %>
 			<button class="pageBtn" onclick="doPageClick(<%=i %>)" ><%=i %></button>
 			
 			<%} else{ %>
 				<button class="pageBtn" disabled style="background-color: orange; color: white;"><%=i %></button>
 			<%} %>
 		<%} %>
 		
 		<%if(currentPage != maxPage){ %>
 		<button class="moveBtn" onclick="doPageClick(<%=currentPage+1 %>)">&gt;다음</button>
 		
 		<%}  %>
 		
 		
 	</div>
 			<script>
 				function doPageClick(currentPage){
 					location.href = "<%=contextPath%>/clist.bo?currentPage="+currentPage;
 				}
 			</script>
			

			
		</div>


	</div>
	

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>

</body>
</html>