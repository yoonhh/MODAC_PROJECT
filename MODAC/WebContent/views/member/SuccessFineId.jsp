<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.modac.member.model.vo.Member"%>
    <%
    	Member fineId = (Member) session.getAttribute("fineId");
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>


<style>
    .found-success{
        border: 2px solid #F0A500;
    }
    .found-success h1{
        text-align: center;
        padding-top: 50px;
        padding-bottom: 30px;
    }
    .found-success hr{
        width: 80%;
        background-color : #F0A500; 
        height: 2px;
    }
    .found-login{
        text-align: center;
        padding-top: 20px;
        padding-bottom: 40px;
    }
    .btn{
        display: inline-block;
        border: 1px solid #F0A500;
    }
    #btnLogin{
        background-color: #F0A500;
        color: white;
        width: 250px;
    }
    #main{
        background-color: gray;
        color: white;
        width: 250px;
        border: none;
    }
    .found-id{
        text-align : center;
        padding : 100px 0;
    }
    
    .found-fail{
        width: 500px;
        margin: auto;
    }
    .found-fail h4{
        text-align: center;
        padding-top: 60px;
        padding-bottom: 50px;
    }
    .container {
        margin-top: 65px;
    }
    #btnback {
        background-color: gray;
        color: white;
        border: none;
    }
    #enroll {
        background-color: #F0A500;
        color: white;
        border: none;
    }
</style>



</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
    
    <form name="idsearch" method="post">
    <%
     if (fineId != null) {
    %>
    
    <div class = "container">
        <div class = "found-success">
            <h1> 아이디 찾기 </h1>
            <hr>
            <div class ="found-id">
                <span style="font-size: 25px; color : #F0A500;"><%= fineId.getMemberName()%></span> <span style="font-size: 20px;"> 님의 아이디는</span>
                <span style="font-size: 25px; color : #F0A500;"> <%=fineId.getMemberId() %></span> <span> 입니다 </span>
            </div>            
            <hr>

            <div class = "found-login">
                <input type="button" class="btn" id="btnLogin" value="로그인" onClick = "location.href = '<%=contextPath %>/MemberloginForm.me'"/>
                <input type="button" class="btn" id="main" value="비밀번호찾기" onClick = "location.href = '<%=contextPath%>/MemberFineIdPwd.me'"/>
            </div>

        </div>
    </div>
    <%
} else {
%>
    <div class = "container">
        <div class = "found-fail" style="border: 2px solid #F0A500;">
            <h4>  등록된 정보가 없습니다. </h4>
            <div class = "found-login">
                <input type="button" class="btn" id="btnback" value="다시찾기" onClick="history.back()"/>
                <input type="button" class="btn" id="enroll" value="회원가입" onClick='<%=contextPath %>/enrollFrom.me'/>
            </div>
        </div>
    </div>
     <%
}
%> 
    </form>
</body>
</html>