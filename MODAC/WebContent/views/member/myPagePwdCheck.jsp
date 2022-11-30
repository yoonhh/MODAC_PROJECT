<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
    @import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
        #self{
            border:2px solid black;
            width: 500px;
            height: 400px;
            margin-top: 30px;
            display: table;
            margin-left: auto;
            margin-right: auto;
        }
        #se{
            text-align: center;
            margin-top: 30px;
           
        }
        #pwdIn{
            margin-top: 50px;
            text-align: center;
        }
        .pwd{
            margin-top: 30px;
            display: table;
            margin-left: auto;
            margin-right: auto;
        }
        .btn {
            margin-top: 30px;
            display: table;
            margin-left: 115px;
            margin-right: auto;
            width: 270px;
        }
       


    </style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
<form id="self" action="<%=contextPath%>/myPagecheck.me" method=post>

    <h2 id="se" style="color: rgb(74,57,51);
      font-family: 'Hahmlet', serif;">본인확인</h2><br>
    <hr style="background: black;  border-width: 2px;">
    <div id="pwdIn"style="color: rgb(74,57,51);
      font-family: 'Hahmlet', serif;">비밀번호를 입력해주세요</div>

    <input type = "password" name = "memberPwd" placeholder="비밀번호"  class="pwd" size="30">
    <input type="submit" class="btn btn-secondary" value="확인">
</form>
</body>
</html>