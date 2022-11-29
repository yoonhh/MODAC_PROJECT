<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 	import="java.util.ArrayList , com.modac.camStagram.model.vo.*, com.modac.common.model.vo.*"%>
<%
	ArrayList<CamStagram> list = (ArrayList<CamStagram>)request.getAttribute("list");
    Attachment at = (Attachment)request.getAttribute("at");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/___vscode_livepreview_injected_script"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<link rel=”stylesheet” href=”http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css“>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
       .title {
        	text-align:center;
        	margin-top: 40px;
        	color: rgb(74,57,51);
        	font-family: 'Do Hyeon', sans-serif;
        	font-size: 35px;
		}
		#button1 {
    		background-color: #BDBDBD;
        	border: #BDBDBD;
        	color: white;
    	}
    	#button2 {
    		background-color: orange;
        	border: orange;
        	color: white;
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
        .content1{
           width: 20%;
		   height : 1000px;
		   padding: 50px 20px 10px;
		   background-color: antiquewhite;
           float: left; 
        }

        .content2{
            width: 80%;
			padding: 10px 50px 20px;
            float: left; 
        }
		.navbar{
            width: 450px;
		}
		.navbar, .btn-group{
			margin-top: 20px;
            display: table;
            margin-left: auto;
            margin-right: auto;
		}
		.active{
			font-size: large;
			font-weight: 600;
		}
	    .date {
	        float : right;
	    }
	    .list-area {
	    	width: 80%;
			margin: auto;
			padding: 1%;
	    }
	    .image-box {
		    width:380px;
		    height:220px;
		    overflow:hidden;
		    margin:0 auto;
		}
		.image-thumbnail {
		    width:100%;
		    height:100%;
		    object-fit:cover;
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
	    div p {
		    display: inline-block;
		    width: 100%;
		    white-space: nowrap;
		    overflow: hidden;
		    text-overflow: ellipsis;
		
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
	   
</style>

</head>
<body>
<%@ include file="../common/menubar.jsp" %>
        <div class="content">
            <div class="content1">
				<nav class="flex-column">
					<a class="sidemenu" aria-current="page" href="#"><i class="bi bi-fire"></i> &nbsp;모닥불이야기</a><br><br> 
	                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.cr">캠핑장 리뷰</a>
	                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.r">캠핑 레시피</a>
	                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.cs">캠핑스타그램</a>
				</nav>
            </div>
            <div class="content2">
				<br>
                <h3 class="title">캠핑 스타그램</h3>
                <br>
                
                <nav class="navbar">
					<form class="container-fluid">
                         <div class="input-group">
                            <select class="form-select" name ="f" aria-label="Default select example" style="width:25%;">
                                <option  ${(param.f == "POST_CONTENT")? "selected":""} value="POST_CONTENT">내용</option>
                                <option  ${(param.f == "MEMBER_NIC")? "selected":""} value="MEMBER_NIC">작성자</option>
                               </select>
                            <input type="text" name ="q" class="form-control" placeholder="검색어를 입력하세요" aria-label="Username" aria-describedby="basic-addon1" style="width: 60%;" value="${param.q}">
                            <input type="submit" class="input-group-text" id="basic-addon1" value="검색">
                        </div>
                        </form>
					<br>
				</nav>
                
	            <br>				
				<div class="list-area">
					<% if(loginMember != null) { %>
						<div align="right">
			                <a href="<%=contextPath %>/enrollForm.cs" class="btn" id="button2">글쓰기</a> 
		            	</div>
		            <% } %>
		            <br>

					<div class="detail">
					<% if(!list.isEmpty()) {%>
						<% for(CamStagram cs : list) {%>
						<div class="card" style="width: 45%; margin:10px; display:inline-block!important;">
							<input type="hidden" name="csno" id="csno" value="<%=cs.getPostNo()%>"> 
						 
							<div class="image-box" style="width: 100%;">
							<img src="<%=contextPath%>/<%=cs.getTitleImg()%>" class="image-thumbnail" width="200px" height="150px">
							</div>
							
							<div class="card-body">
								<span style="font-size: 16px;"><b><%=cs.getMemberNic()%></b></span>
								<span class="date" style="font-size: 14px;"><%=cs.getCreateDate()%></span>
								<br>
								<br>
								<p>
								<%=cs.getPostContent()%>
								</p>
								
								<i class="bi bi-suit-heart"></i>  <span><%=cs.getLikeCount() %></span> &nbsp; 
	
								<i class="bi bi-chat-dots"></i>  <span><%=cs.getReplyCount()%></span>
	
							</div>
						</div>
						<% } %>
					<% } else { %>
						등록된 게시글이 없습니다.
					<% } %>
					</div>

					<script>
						$(function(){
							$(".card").click(function(){
								let csno = $(this).children().eq(0).val();
								location.href = '<%=contextPath%>/detail.cs?csno='+csno;
							})
						})
					</script>
			    </div>
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
		              <button class="moveBtn" onclick="doPageClick(<%=currentPage +1 %>)">다음&gt;</button>
		           <% } %>
		        </div>
		        
		        <script>
				   function doPageClick(currentPage){
					  location.href = "<%=contextPath%>/list.cs?currentPage="+currentPage;
				   }
			    </script>
			 </div>
        </div>
        
       
 


 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>