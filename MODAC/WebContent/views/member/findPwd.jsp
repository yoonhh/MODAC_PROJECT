<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
        String contextPath = request.getContextPath();
	    String alertMsg = (String) session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <title>비밀번호 찾기</title>
    <style>
        
        a{ text-decoration: none; }
        button{ cursor: pointer; }
        #wrapper{
            border: 5px solid #F0A500;
            margin:0 auto;
            width: 500px;
            height: 500px;
        }
        .clicked{
            background-color: white;
        }

        #fineid{
            float: left;
            /* border: 1px solid black; */
            width: 250px;
            text-align : center;
            padding : 20px 0;
        }
        #fineid>button{
            width: 250px;
            height: 70px;
            /* background-color: #F0A500; */
        }
        #finepwd{
            
            text-align : center;
            padding : 20px 0;
        }
        #finepwd>button{
            width: 247px;
            height: 70px;
        }

        #divid{
            text-align : center;
            margin-top: 50px;
        }
        #divid>input{
            margin-left: 30px;
            width: 200px;
            height: 30px;
        }

        #divname{
            text-align : center;
            margin-top: 50px;
        }
        #divname>input{
            margin-left: 40px;
            width: 200px;
            height: 30px;
        }

        #divemail{
            text-align : center;
            margin-top: 40px;
        }
        #divemail>input{
            margin-left: 20px;
            width: 200px;
            height: 30px;
        }

        #msg {
            /* border: 1px solid black; */
            text-align: center;
            margin-top: 30px;
            margin-bottom: 0px ;
        }

       #btn-area {
            text-align : center;
            padding : 20px 0;
            width: 300px;
            background-color: #F0A500;
            margin: auto;
            margin-top: 5px;
            padding: 0%;
       }

        #btn{
           background-color: #F0A500;
           border: none;
           color: white;
           font-weight: 400px;
           width: 300px;
           height: 50px;
           margin: 0px 0px 0px 0px;
           font-weight: bolder;
           
        }
    </style>
    
</head>
<body>
	

	<form action="<%=contextPath %>/findPw.me" method="post" class="content">
	    <div id="wrapper">
	        <div id="fine">
	            <div id="fineid">
	                <button class="button" onclick="location.href ='<%=contextPath %>/views/member/fineId.jsp'">아이디찾기</button>
	            </div>
	            <div id="finepwd">
	                <button class="button" onclick="location.href = '<%=contextPath%>/views/member/findPwd.jsp'">비밀번호찾기</button>
	            </div> 
	        </div>
	        <div id="divid">
	            <span>아이디</span><input name="memberId" id="memberId" class="form-control" type="text" placeholder="아아디를 입력해주세요" required>
	        </div>
	        <div id="divname">
	            <span>이름</span><input name="memberName" id="memberName" class="form-control" type="text" placeholder="이름을 입력해주세요" required>
	        </div>
	        <div id="divemail">
	            <span>이메일</span><input name="email" id="email" class="form-control" type="text" placeholder="이메일을 입력해주세요" required>
	        </div>
            <div id="msg">
                <p class="checks" id="check"></p>
            </div>
	        <div id="btn-area">
	            <button id="btn" class="button" type="submit">비밀번호찾기</button>
	        </div>
	    </div>
    </form>


    <script>

        // var idJ = /^[a-zA-Z][a-zA-Z0-9]{4,14}$/;

        $("#memberID").focusout(function() {
            if($("#memberId").val()=="") {
                $("#checks").text('아이디를 입력해주세요.');
                $("#checks").css('color', 'red');
            }
        });

        

        $("#memberName").focusout(function() {
            if($("#memberName").val=="") {
                $("#checks").text('이름을 입력해주세요.');
                $("#checks").css('color', 'red');
            }
        });

        $("#email").focusout(function() {
            if($("#email").val()=="") {
                $("#checks").text('이메일을 입력해주세요.');
                $("#checks").css('color', 'red');
            }
        });


    </script>


    

</body>
</html>