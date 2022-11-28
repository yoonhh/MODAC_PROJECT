<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>errorPage</title>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<style>

		.container {
			margin-top: 65px;
		}

		.btn{
            display: inline-block;
            border: 1px solid #F0A500;
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

		.found-login{
            text-align: center;
            padding-top: 20px;
            padding-bottom: 40px;
        }


	</style>


<body>

	<%@ include file="../common/menubar.jsp" %>


	<div class = "container">
        <div class = "found-fail" style="border: 2px solid #F0A500;">
            <h4>  <%=errorMsg %> </h4>
            <div class = "found-login">
                <input type="button" class="btn" id="btnback" value="다시찾기" onClick="history.back()"/>
                <input type="button" class="btn" id="enroll" value="회원가입" onClick="location.href='<%=contextPath %>/enrollFrom.me'"/>
            </div>
        </div>
    </div>

	
</body>
</html>