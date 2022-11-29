<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.ArrayList, com.modac.usedProduct.model.vo.Market, 
    com.modac.common.model.vo.PageInfo"  
%>
<% ArrayList<Market> list = (ArrayList<Market>)request.getAttribute("list"); 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
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
        border: 1px solid skyblue;
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
        border: 1px solid;
        box-sizing: border-box;
        width: 800px;
        color: white;
        text-align: center;
        display: inline-block;
        margin-left: 200px;
        padding-left : 120px;
    }
    #check{
        width: 550px;
        border : 1px solid white;
    }
    .navbar{
    	width: 600px;
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
        border-color: orange;
        border-radius: 5%;
        width: 100;
        margin-top: 4%;
        margin-right: 15px;
        border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
        font-family: 'Do Hyeon', sans-serif;
    }
    #enrollBtn:hover{
    	color: black;
    	background-color: gainsboro;
    	border-color: gainsboro;
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
        font-size: 14px;
        font-weight: bolder;
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

        <h1 class="title">중고장터</h1>
        <br>
		
		
		<div class="postSelect" > 
            <input type="checkbox" id="saleView" name="saleView" value="saleView1" checked><span>판매완료 제외</span>
        </div>
		

        <div class="search">
            <div class="put" style="text-align:center;">
                <div class="input-group input-group-sm mb-3" id="check">
                    <nav class="navbar">
                     <form class="container-fluid">
                         <div class="input-group">
                            <select class="form-select" name ="f" aria-label="Default select example" style="width:10%;">
                             	<option  ${(param.f == "POST_TITLE")? "selected":""} value="POST_TITLE">제목</option>
                             	<option  ${(param.f == "MEMBER_NIC")? "selected":""} value="POST_CONTENT">내용</option>
                            </select>
                         <input type="text" name ="q" class="form-control" placeholder="검색어를 입력하세요" aria-label="Username" aria-describedby="basic-addon1" style="width: 60%;" value="${param.q}">
                         <input type="submit" class="input-group-text" id="basic-addon1" value="검색">
                        </div>
                       </form>
                     	<br>
               		</nav>
                </div>
               
	               <form action="saleView.mk">
	               		<select class="sort" name="sort" id="sort" onchange="this.form.submit()">
		                    <option name="sort" value="sortOfDate" id="sortOfDate" ${(param.sort == "sortOfDate")? "selected":""}>최신순</option>
		                    <option name="sort" value="sortOfCount" id="sortOfCount" ${(param.sort == "sortOfCount")? "selected":""}>조회순</option>
		                </select>
	               </form>
            </div>    
            
            
            <% if(loginMember != null) {%>
            	<a href="<%=contextPath%>/enroll.mk" class="btn btn-secondary btn-sm" id="enrollBtn">게시글 작성
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
						<path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
					</svg>
                </a>
			<% } %>
            
        </div>
       
        <div class="postList" style="display: inline-block;">
        	<% if(!list.isEmpty()) { %>
        		<% for(Market m : list) { %>
		            <table class="postTable">
                        <thead>
                            <tr height="10px">
		                        <td class="sale">
				            			<b>판매중</b>	
		                        </td>
		                        <td class="right">조회수&nbsp;&nbsp;<%=m.getReadCount()%></td>
		                    </tr>
                        </thead>
		               <tbody>
                            <tr>
		                        <td class="thumbnail" colspan="2">
			                        <img src="<%=contextPath %>/<%=m.getTitleImg()%>" width="200px" height="200px">
		                        </td>
		                    </tr>
		                    <tr height="16px">
		                        <td class="contentTitle" colspan="2"><b><%=m.getPostTitle()%></b></td>
		                    </tr> 
                       </tbody>
		               <tfoot>
                            <tr height="10px">
                                <td class="nic"><b>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-emoji-sunglasses-fill" viewBox="0 0 16 16">
                                    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zM2.31 5.243A1 1 0 0 1 3.28 4H6a1 1 0 0 1 1 1v.116A4.22 4.22 0 0 1 8 5c.35 0 .69.04 1 .116V5a1 1 0 0 1 1-1h2.72a1 1 0 0 1 .97 1.243l-.311 1.242A2 2 0 0 1 11.439 8H11a2 2 0 0 1-1.994-1.839A2.99 2.99 0 0 0 8 6c-.393 0-.74.064-1.006.161A2 2 0 0 1 5 8h-.438a2 2 0 0 1-1.94-1.515L2.31 5.243zM4.969 9.75A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .866-.5z"/>
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
        
	    <script>
		    $(document).ready(function(){
		        $("#saleView").change(function(){
		            if($("#saleView").is(":checked")){
		            	
		            }else{
		            	location.href='<%=contextPath%>/list.mk';
		            }
		        });
		    });
	    
	    	//게시글 번호
		    $(function(){
	            $(".postTable>tbody").click(function(){
	            	let mno = $(this).parent().find('tfoot').find('td').eq(2).text(); //게시글 번호
	                location.href= '<%=contextPath%>/detail.mk?mno='+mno;
	            });
	        });
	        
     		//판매중 체크박스 
//     		function onlySaleView(){
//     			if($('#saleView').is(':checked')){
<%--     				location.href='<%=contextPath%>/list.mk'; --%>
//     			}
//     		};
    		
    		//체크박스 value값 data에 저장
//     		let DATA;
//     		$("#saleView").click(function(){
//     			$("input:checkbox[name=saleView]").each(function(){
// 	    			if($(this).is("checked")){
// 	    				DATA = $(this).val();
// 	    			}
//     			});
//     		})
    		
    		// 서버에서 받아온 데이터 체크하기
//    			$("#saleView").click(function(){
//    				let DATA = "${data['saleView']}";
//    		         console.log(DATA);
// 	    		$("input:checkbox[name=saleView][value='saleView1']").attr("checked", true);
//     		});
    		
    		
//      		//셀렉박스 
//      		$(function() {
// 		    	$("select[name=sort]").val((("${market.sort}" == '') ? "" : "${market.sort}")).prop("selected", true); 
// 		    });
	    </script>	    
	    <br><br>
	    
    	<!-- 페이징바 -->
    	<div align="center" class="paging-area">
			<% if(currentPage != 1) {%>
				<button class="moveBtn" onclick="doPageClick(<%=currentPage -1 %>)">&lt;이전</button>
			<% } %>
			
			<% for(int i = startPage; i <= endPage; i++) { %>
				<%if(i != currentPage) {%>
					<button  class="pageBtn" onclick="doPageClick(<%=i%>)"><%=i %></button>
				<%} else {%>
					<button class="pageBtn" disabled><%= i %></button>
				<%} %>
			<% } %>
			
			<% if(currentPage != maxPage) { %>
				<button class="moveBtn" onclick="doPageClick(<%=currentPage +1 %>)">&gt;다음</button>
			<% } %>
		</div>
    
    </div>
     	<script> -->
 			function doPageClick(currentPage){
 				location.href ="<%=contextPath%>/saleView.mk?currentPage="+currentPage+"&sort=${sort}"; 
 			}
 		</script>
    	
    
    
    
    
<br><br><br><br>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>