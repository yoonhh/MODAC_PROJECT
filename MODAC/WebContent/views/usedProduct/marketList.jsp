<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.ArrayList, com.modac.usedProduct.model.vo.Market, 
    com.modac.common.model.vo.PageInfo, java.io.File, com.modac.usedProduct.model.vo.Attachment"  
%>
<% ArrayList<Market> list = (ArrayList<Market>)request.getAttribute("list"); 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	Attachment at = (Attachment)request.getAttribute("at");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고장터</title>
<!--jquery-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!--부트스트랩 그리드-->
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Poor+Story&display=swap');
    #content{
        border: 1px solid white;
        box-sizing: border-box;
        width: 1200px;
        margin: auto;
    }
    #content{
        border: 1px solid white;
        box-sizing: border-box;
    }
    .postSelect{
        margin-left: 360px;
        border: white;
        width: 100px;
    }
    .search{
        border: 1px solid white;
        box-sizing: border-box;
        width: 800px;
        height: 100px;
        text-align: center;
        display: inline-block;
        margin-left: 200px;
        padding-left : 120px;
    }
     #check{
        width: 550px;
        border : 1px solid white;
        /*padding-left : 160px;*/
    }
     .navbar{
    	width: 700px;
    	border : 1px solid white;
    	margin-top : -5px;
    	margin-right: -25px;
    }
    .form-select{
    	height: 38px;
    }
    .sort{
    	margin-top: 10px;
        font-size: 13px;
    }
    option{
        font-size: 13px;
    }
    .postList{
        border: 1px solid;
        box-sizing: border-box;
        width: 87%;
        color: white;
        margin-top: 30px;
        margin-left: 80px;
        margin-right: 80px;
        margin-bottom: 30px;
    }
    table {
        height: 300px;
        width: 200px;
        float: left;
        margin-left: 40px;
        margin-bottom: 50px;
    }
    table, tr, td {
        /*border: 1px solid skyblue;*/
    }
    span {
        font-size: 0.75em;
    }
    #enrollBtn{
        float: right;
        background-color: orange;
        border: orange;
        border-radius: 5%;
        width: 100px;
        margin-top: 4%;
        margin-right: 15px;
        border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
        font-family: 'Do Hyeon', sans-serif;
    }
    #enrollBtn:hover{
    	color: black;
    	background-color: gainsboro;
    	border: gainsboro;
    }
    .sale{
    	font-size: 13px;
    	color: orange;
        font-family: 'Poor Story', cursive;
    }
    .right{
        text-align: right;
        color: gray;
        font-size: 12px;
    }
    .day{
    	text-align: right;
        color: gray;
        font-size: 12px;
    }
    .postNum{
        color: black;
        height: 5px;
        font-size: 3px;
    }
    .contentTitle{
        text-align: center;
        color: #4a3933;
        font-size: 15px;
    }
    .nic{
        color: rgb(128, 59, 30);
        font-size: 13px;
    }
    div[id=check]{
        float: left;
    }
    select{
        width: 70px;
        margin-top: 0.6%;
        font-size: 0.8em;
        height: 27px;
        margin-left: -3%;
    }
    .moveBtn{
    	color: white;
    	background-color: rgb(74,57,51);
    	border : none;
    	width: 80px;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
        font-family: 'Do Hyeon', sans-serif;
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
    	border: orange;
    }
    .title{
    	text-align:center;
    	font-family: 'Do Hyeon', sans-serif;
        color: #4a3933;
        font-size: 45px;
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>

    <div id="content">
		<br>
        <h1 class="title">중고장터</h1>
        <br>
		
		<!-- 판매완료글 제외: 새로운 페이지 불러오기 방식 -->
		<form action="saleView.mk?">
			<div class="postSelect">
	           	<input type="checkbox" id="saleView" name="saleView" value="판매중" onclick="saleView()"><span>판매완료 제외</span>
	       		<input type="submit" class="checkboxSub">
	       	</div>
	    </form>
			
		
        <div class="search">
        	<!-- 검색기능 -->
            <div class="put" style="text-align:center;">
                <div class="input-group input-group-sm mb-3" id="check">
                  <nav class="navbar">
                     <form class="container-fluid">
                         <div class="input-group">
                            <select class="form-select" name ="f" aria-label="Default select example" style="width:6%;">
                             	<option  ${(param.f == "POST_TITLE")? "selected":""} value="POST_TITLE">제목</option>
                             	<option  ${(param.f == "MEMBER_NIC")? "selected":""} value="POST_CONTENT">내용</option>
                            </select>
                         	<input type="text" name ="q" class="form-control" placeholder="검색어를 입력하세요" aria-label="Username" aria-describedby="basic-addon1" style="width: 60%;" value="${param.q}">
                         	<input type="submit" class="input-group-text" id="basic-addon1" value="검색">
                        </div>
                       </form><br>
               		</nav>
                </div>
                
                <!-- 게시글 정렬: 새로운 페이지 불러오기 방식 -->
	    		<form action="list.mk">  
               		<select class="sort" name="sort" id="sort" onchange="this.form.submit()">
	                    <option name="sort" value="sortOfDate" id="sortOfDate"  ${(param.sort == "sortOfDate")? "selected":""} >최신순</option>
	                    <option name="sort" value="sortOfCount" id="sortOfCount" ${(param.sort == "sortOfCount")? "selected":""}>조회순</option>
	                </select>
	            </form>    
            </div>
                
            <!--게시글 작성 버튼: 로그인 시 게시글 작성 버튼 표시 -->    
            <% if(loginMember != null) {%>
            	<a href="<%=contextPath%>/enroll.mk" class="btn btn-secondary btn-sm" id="enrollBtn">게시글 작성
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
						<path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
					</svg>
                </a>
            <% } %>
        </div>
       
       <!-- 게시글 목록조회 -->
        <div class="postList" style="display: inline-block;">
        	<!-- 게시글이 있을 경우 게시글 표 뜸 -->
        	<% if(!list.isEmpty()) { %>
        		<% for(Market m : list) { %>
		            <table class="postTable">
                        <thead>
                            <tr height="10px">
		                        <td class="sale">
		                        	<!-- 판매상태 별 표시 변환 -->
		                        	<% if(m.getSale().equals("Y")) { %>
				            			<b>판매중</b>
				            		<% } else { %>
				            			<p style="color:#BDBDBD; margin-bottom: -4px;"><b>판매완료</b></p>
				            		<% } %> 	
		                        </td>
		                        <td class="right">조회수&nbsp;&nbsp;<%=m.getReadCount()%></td>
		                    </tr>
                        </thead>
		               <tbody>
                            <tr>
		                        <td class="thumbnail" colspan="2">
			                        <img src="<%=contextPath %>/<%=m.getTitleImg()%>" width="200px" height="200px">
			                        <!-- 폴더에 titleImg 사진이 없을 경우 로고로 띄우기 -->
<%-- 			                        <% File file = new File("/resources/market_upfiles/"+at.getNewName()); %> --%>
<%-- 			                        <%if(!file.exists()) {%> --%>
<!-- 			                        	<img alt="" src="/resources/modacLogo/logo.png"> -->
<%-- 			                        <% } %> --%>
		                        </td>
		                    </tr>
		                    <tr height="16px">
		                        <td class="contentTitle" colspan="2"><b><%=m.getPostTitle()%></b></td>
		                    </tr> 
                       </tbody>
		               <tfoot>
                            <tr height="10px">
                                <td class="nic"><b>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-emoji-kiss" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M12.493 13.368a7 7 0 1 1 2.489-4.858c.344.033.68.147.975.328a8 8 0 1 0-2.654 5.152 8.58 8.58 0 0 1-.81-.622Zm-3.731-3.22a13 13 0 0 0-1.107.318.5.5 0 1 1-.31-.95c.38-.125.802-.254 1.192-.343.37-.086.78-.153 1.103-.108.16.022.394.085.561.286.188.226.187.497.131.705a1.892 1.892 0 0 1-.31.593c-.077.107-.168.22-.275.343.107.124.199.24.276.347.142.197.256.397.31.595.055.208.056.479-.132.706-.168.2-.404.262-.563.284-.323.043-.733-.027-1.102-.113a14.87 14.87 0 0 1-1.191-.345.5.5 0 1 1 .31-.95c.371.12.761.24 1.109.321.176.041.325.069.446.084a5.609 5.609 0 0 0-.502-.584.5.5 0 0 1 .002-.695 5.52 5.52 0 0 0 .5-.577 4.465 4.465 0 0 0-.448.082Zm.766-.087-.003-.001-.003-.001c.004 0 .006.002.006.002Zm.002 1.867-.006.001a.038.038 0 0 1 .006-.002ZM6 8c.552 0 1-.672 1-1.5S6.552 5 6 5s-1 .672-1 1.5S5.448 8 6 8Zm2.757-.563a.5.5 0 0 0 .68-.194.934.934 0 0 1 .813-.493c.339 0 .645.19.813.493a.5.5 0 0 0 .874-.486A1.934 1.934 0 0 0 10.25 5.75c-.73 0-1.356.412-1.687 1.007a.5.5 0 0 0 .194.68ZM14 9.828c1.11-1.14 3.884.856 0 3.422-3.884-2.566-1.11-4.562 0-3.421Z"/>
                                    </svg>&nbsp;<%=m.getMemberNic()%></b></td>
                                <td class="day"><%=m.getCreateDate()%></td>
		                    </tr>
		                    <tr>
		                    	<td class="postNum" colspan="2"><%=m.getPostNo()%></td>
		                    </tr>
                       </tfoot> 
		            </table>
            	<% } %>
            <% }else { %>
            		등록된 게시물이 없습니다. 게시물을 작성해보세요!
            <% } %>
        </div>
        
        
        <!-- 페이징바 -->
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
    </div>
    
    <!-- 게시글 정렬시 페이징뷰 적용 페이지 -->
    <script>
		function doPageClick(currentPage){
			location.href ="<%=contextPath%>/list.mk?currentPage="+currentPage+"&sort=${sort}";
		}
	</script>
    
    <script>
    	//게시글 번호
	    $(function(){
            $(".postTable>tbody").click(function(){
            	let mno = $(this).parent().find('tfoot').find('td').eq(2).text();
                location.href= '<%=contextPath%>/detail.mk?mno='+mno;
            });
        });
        
   		//판매완료 제외 페이지
   		$(function(){
   			//input 태그 숨김
			$(".checkboxSub").hide();
			
			//titleImg클릭시 file1클릭 -> input태그 발동
			$("#saleView").click(function(){
				$(".checkboxSub").click();
			});
   		})
   		
    	//셀렉박스 
//    		$(function() {
//     		$("select[name=sort]").val((("${market.sort}" == '') ? "" : "${market.sort}")).prop("selected", true); 
// 	    });
    </script>	 
	    

    	
    
    
    
    
<br><br><br><br>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>