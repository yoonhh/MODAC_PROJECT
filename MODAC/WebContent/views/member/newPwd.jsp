<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>비밀번호 재설정</title>
    <style>
        
        a{ text-decoration: none; }
        button{ cursor: pointer; }
        #wrapper{
            border: 5px solid #F0A500;
            margin: auto;
            width: 600px;
            height: 650px;
        }
        .clicked{
            background-color: white;
        }

        #find {
            /* border: 1px solid black; */
            text-align: center;
            margin: 0px;
            box-sizing: border-box;
            margin-left: -5px;
        }
        #find button {
            width: 295px;
            height: 80px;
            margin-right: 0px;
        }

        #idbtn {
            border: none;
            color: white;
            font-size: large;
            font-weight: bold;
            background-color: #F0A500;
            margin: 0px;
        }
        #pwdbtn {
            border: none;
            color: rgb(74, 57, 51);
            font-size: large;
            font-weight: bold;
            background-color: white;
            margin: 0px;
        }

        .content {
            text-align: center;
            /* border: 1px solid black; */
        
        }

        #newPwd-content{
            /* border: 1px solid black; */
            margin-top: 80px;
            /* text-align: center; */
            font-size: 20px
        }

        #divUpdatePwd{
            margin-bottom: 60px;
        }
        #divUpdatePwd>input{
            margin-left: 45px;
            width: 300px;
            height: 50px;
        }

        #divCheckPwd>input{
            margin-left: 15px;
            width: 300px;
            height: 50px;
        }

        #btn-area {
            margin-top: 40px;
            /* border: 1px solid red; */
        }
        
        #check {
            margin-bottom: 10px;
            font-size: 15px;
            padding: 0px;
        }

        #submit {
            text-align: center;
            margin-top: 0px;
            width: 300px;
            height: 60px;
            background-color: #F0A500;
            border: none;
            color: white;
            font-weight: bold;
        }

        
        #hidden-value {
            display: none;
        }
        #nwCheckPwd{
        	margin-top: 30px;
            font-size: 20px;
        }
        #equalPwd{
            margin-bottom: 40px;
            font-size: 20px;
        }
        #submit:hover{
        	background-color: rgb(74, 57, 51);
            cursor: pointer;
        }

    </style>
    
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
	
	<br> <br>

    <script>
		let msg = "<%=alertMsg%>";
		if(msg != "null") {
			alert(msg);
			<% session.removeAttribute("alertMsg"); %>
		}
	</script>
	

	    <div id="wrapper">
	        <div id="find">
	                <button id="idbtn" class="button" onclick="location.href ='<%=contextPath %>/MemberFineIdPwd.me'">아이디찾기</button>
	                <button id="pwdbtn" class="button" onclick="location.href = '<%=contextPath%>/MemberFineIdPwd.me'">비밀번호찾기</button>
	        </div>

            <div class="content">
                <form action="<%= contextPath%>/fineupdatePw.me" method="post">
                    
                    <div id="hidden-value">
                        <input type="hidden" value="${memberId }" name="memberId"/>
                        <input type="hidden" value="${memberName }" name="memberName"/>
                        <input type="hidden" value="${email }" name="email"/>
                        <%-- ${memberId },${memberName },${email }<br> --%>
                    </div>
                    
                    <div id="newPwd-content">
                        <div id="divUpdatePwd">
                            <span>새 비밀번호</span>
                            <input name="updatePwd" id="updatePwd" type="password" placeholder="내용을 입력해주세요" required>
                        	<div id ="nwCheckPwd"></div>
                        </div>
                        <div id="divCheckPwd">
                            <span>비밀번호 재입력</span>
                            <input name="checkPwd" id="checkPwd" type="password" placeholder="내용을 재입력해주세요" required>
                        </div>
                    </div>
                    <div id="btn-area">
                    </div>
                    	<div id="equalPwd"></div>
                        <input id="submit" type="submit" onclick="validate();"disabled value="비밀번호 재설정">
                </form>
            </div>
	    </div>
    <script>
    $(function () {
    	$("#updatePwd").keyup(function(){
        	let pwd1=$("#updatePwd").val();
        	let pwdCheck2 = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{5,15}$/gi;
        	
	            if(!pwdCheck2.test(pwd1)){
	                $("#nwCheckPwd").html('사용할 수 없는 비밀번호입니다.').css('color','red')
	            }else{
	                $("#nwCheckPwd").html('사용할 수 있는 비밀번호입니다.').css('color','green');
	            }
    	})
	});    
    </script>
    
        <script>
    <!-- 비밀번호 같은지 체크 -->
    $(function () {
        $("#checkPwd").keyup(function(){
            let pwd1=$("#updatePwd").val();
            let pwd2=$("#checkPwd").val();	
                if(pwd1 != "" || pwd2 !=""){
                    if(pwd1 == pwd2){
                        $("#equalPwd").html('비밀번호가 일치합니다.').css('color','green');
                        $("#submit").removeAttr("disabled");
                        
                    }else{
                        $("#equalPwd").html('비밀번호가 일치하지 않습니다.').css('color','red');
                    }
                }
        });
    });
    </script>
    



    

</body>
</html>
