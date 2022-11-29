<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.modac.QA.model.vo.Qa"%>
    
<%
	//Qa q = (Qa)request.getAttribute("q");	
	int qno = Integer.parseInt(request.getParameter("qno"));
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
	
        .content1{
           width: 20%;
		       height : 1000px;
		       padding: 50px 20px 10px;
		       background-color: antiquewhite;
           float: left; 
        }

        .content2{
          width: 80%;
		      padding: 50px 50px 20px;
		      float: left;
        }
       
		.pwd-box{
			border: 5px solid #F0A500;
            margin:0 auto;
            width: 500px;
            height: 250px;
		}

		#checkPwd{
			padding-left: 70px;
			padding-right: 70px;
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

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="content">

		<div class="content1">
			<nav class="flex-column">
				<a class="nav-link active sidemenu" aria-current="page" href="<%=contextPath %>/noticeList"><h3>공지사항</h3></a><br><br> 
				<a class="nav-link sidemenu2" href="<%=contextPath %>/noticeList">모닥불 소식</a> 
				<a class="nav-link sidemenu2" href="<%=contextPath%>/campTipList">캠핑 팁</a> 
				<a class="nav-link sidemenu2" href="<%=contextPath %>/qaList">Q&A</a>
				<a class="nav-link sidemenu2" href="<%=contextPath %>/faqList">FAQ</a>
			</nav>
		</div>


		<div class="content2">
			<br>
			<h3 class="list-title">Q&A</h3>
			
			<br>
			
 			<form class="pwd-area" action="<%=contextPath %>/qaCheckPwd" method="post">
				<div class="pwd-box">
					<br><br>
					<h3 style="text-align: center;">비밀번호를 입력해 주세요</h3>
					<input type="hidden" name="qno" value="<%=qno%>">
					<br><br>
						
						<div class="input-group mb-3" id="checkPwd">
							<input type="password" name="postPwd" class="form-control" placeholder="숫자만 입력" aria-label="숫자만 입력" aria-describedby="button-addon2" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
							<button class="btn text-white" style="background-color:orange;" type="submit" id="button-addon2">확인</button>
						</div>
				</div>
			</form>
		</div>


	</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
</body>
</html>