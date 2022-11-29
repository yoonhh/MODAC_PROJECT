<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" import="java.util.ArrayList , com.modac.camStagram.model.vo.*, com.modac.common.model.vo.*"%>
    
    <%ArrayList<CamStagram> list = (ArrayList<CamStagram>)request.getAttribute("list"); 
     String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<link rel=”stylesheet” href=”http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css“>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>


	.detail{
		width: 100%;
		float: left;
	}
	.card{
		width: 17%; 
		margin: 1%; 
		display: inline-block !important;
		
	}
		#hi{
			padding-top: 40px;
			
		}
	    .image-box {
		    width:18%;
		    height:220px;
		    overflow:hidden;
		    margin:0 auto;
		}
		.image-thumbnail {
		    width:100%;
		    height:100%;
		    object-fit:cover;
			
		}
	    div p {
		    display: inline-block;
		    width: 100%;
		    white-space: nowrap;
		    overflow: hidden;
		    text-overflow: ellipsis;
			border: 1px solid black;
		
		    /* 여러 줄 자르기 추가 스타일 */
		    white-space: normal;
		    line-height: 1.2;
		    height: 3.6em;
		    text-align: left;
		    word-wrap: break-word;
		    display: -webkit-box;
		    -webkit-line-clamp: 3;
		    -webkit-box-orient: vertical;	
		}
		.date{
			float : right;
			font-size: 10px;
			text-align:center;
		}
		.card-b div {
	  		border : white;
	  		padding : 2px;
	  		font-size: 13px;
	  		
	  		display: inline-block;
		    width: 95%;
		    white-space: nowrap;
		    overflow: hidden;
		    text-overflow: ellipsis;
		
		    /* 여러 줄 자르기 추가 스타일 */
		    white-space: normal;
		    line-height: 1.5;
		    height: 3em;
		    text-align: left;
		    word-wrap: break-word;
		    display: -webkit-box;
		    -webkit-line-clamp: 2;
		    -webkit-box-orient: vertical;
		}

	   
</style>
</head>
<body>
<div class="content2" >
		<br>

		<div class="list-area">
			
			<div class="detail" >
				<% if(!list.isEmpty()) {%>
					<% for (CamStagram cs : list) { %>
						
				<div class="card" >
					<input type="hidden" name="csno" id="csno" value="<%=cs.getPostNo()%>">
					<div class="image-box" style="width: 100%;">
						<img src="<%=contextPath%>/<%=cs.getTitleImg()%>"
						class="image-thumbnail" width="200px" height="150px">
					</div>
					<div class="card-b">
						<span style="font-size: 14px;">&nbsp;<b><%=cs.getMemberNic()%></b></span> 
						<span class="date"><%=cs.getCreateDate()%></span>
						<br>
						<div>
							<%=cs.getPostContent()%>
						</div>
						&nbsp;
						<i class="bi bi-suit-heart" style="font-size: 13px;"></i>  <span style="font-size: 13px;"><%=cs.getLikeCount() %></span> &nbsp; 
	  					<i class="bi bi-chat-dots" style="font-size: 13px;"></i>  <span style="font-size: 13px;"><%=cs.getReplyCount()%></span>
					</div>
				</div>	
		
				<% } %>
				<% } else { %>
					등록된 게시글이 없습니다.
				<% } %>
			</div>
		</div>		
</div>

			
		

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
			crossorigin="anonymous">
		</script>
</body>
</html>