<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	
    <style>
    @import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
    	.title {
		text-align:center;
        font-family: 'Do Hyeon', sans-serif;
        color: #F0A500;
        font-size: 45px;
    	}
    	
        #wrapper{
            border: 10px solid #F0A500;
            width: 550px;
            height: 1000px;
            margin: auto;
            padding-top: 50px;
        }
        h1{
            text-align: center;
            color: #F0A500;
        }
         #wrapper div{
            padding: 20px;
            padding-left: 50px;
        }
        span{
            font-size: 20px;
            box-sizing: border-box;
        }
        div>#id{
            margin-left: 90px;
            height: 30px;
        }
        div>#checkId{
            margin-bottom: 5px;
        }
        div>#email, div>#nickname{
            margin-left: 90px;
            height: 30px;
            width: 260px;
        } 
        div>p{
            font-size: 2px;
            margin-top: 5px;
            color: gray;
        }

        div>#password{ 
            margin-left: 70px;
            height: 30px;
            width: 260px;
        }
        div>#checkpassword{
            margin-left: 20px;
            height: 30px;
            width: 260px;
        }
        div>#name{
            margin-left: 110px;
            height: 30px;
            width: 260px;
        }

        #inserCheck{
            margin-top: 20px;
            margin-left: 100px;
            width: 300px;
            height: 50px;
            border: none;
            <!-- background-color: #F0A500; -->
        }

    </style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
	<br> <br>
	
    <div id="wrapper">
        <h1 class="title">회원가입</h1>
        <hr>

        <form id="enroll-form"action ="<%=contextPath %>/insert.me"  method="post">
        <div id="insertId">
            <span>아이디</span>
            <input id="id" type="text" placeholder="아이디입력" name="memberId" required >
			
			<button type="button" class="btn btn-outline-warning" id="checkId" onclick="return idCheck();">아이디 확인</button>
			<div id="reCheckId"></div>
            <p>영문자로 시작하는 5~15자 이내의 영문,숫자로 구성 가능</p>
        </div>
        <div>
            <span>비밀번호</span>
            <input id="password" type="password" placeholder="비밀번호" name="memberPwd" min="5" max="20" required >
            <p>5~20자 이내의 영문,숫자,특수문자(!@#$%^&*)로 구성가능</p>
        	<div id = "reCheckPwd"></div>
        	
        </div>
        <div>
            <span>비밀번호 확인</span>
            <input id="checkpassword" type="password" placeholder="비밀번호 확인" required>
            <div id="checkPwd"></div>
        </div>
			
        <div>
            <span>이름</span>
            <input id="name"type="text" placeholder="이름" name="memberName" required >
        </div>
        <div>
            <span>이메일</span>
            <input id="email" type="email" placeholder="이메일" name="email" required >
            <div id="checkemail"></div>
        </div>
        <div>
            <span>닉네임</span>
            <input id="nickname" type="text" placeholder="닉네임" name="memberNic" required>
        </div>
            <input onclick="return validate();" type="submit" id="inserCheck" value="회원가입" name="insertCheck" disabled>
		</form>
    </div>
    
    
    <!-- 아이디 체크 -->
    <script>
    function idCheck(){
    	
        let id = document.getElementById("id");
        
        let idCheck2 = /^[a-zA-Z0-9]{5,15}$/gi;
        if(!idCheck2.test(id.value)){
            alert("아이디는 영문자로 시작하는 5~15자 이내의 영문, 숫자로 구성 가능합니다.");
            return false;
        }
        
        let $memberId = $("#enroll-form input[name=memberId]");
        $.ajax({
            url : "<%=request.getContextPath()%>/idCheck.me",
            data : {checkId : $memberId.val()},
            success : function(result){
                if(result=="NNNNN"){
                    alert("이미존재하는 아이디 입니다")
                    $memberId.focus();
                }else{
                    if(confirm("사용가능한 아이디 입니다. 사용하시겠습니까?")){
                        /* $("#inserCheck").removeAttr("disabled") */
                        $memberId.attr("disable",true);
                    } else {
                    	

                        }
                    }
                },
            error : function(req,err,gg){
                console.log(req,err,gg,"아이디 중복체크");
            }
        });
    }
    </script>
    
    <!-- 이메일체크 -->
    <script>
        $('#email').focusout(function(){
            $.ajax({
                url : "emailCheck.me",
                type : "post",
                data : {
                    checkemail : $('#email').val()
                },
                success : function(result){
                    if(result == 1){
                        $("#checkemail").html('사용할 수 없는 이메일입니다.').css('color','red')
                    }else{
                        $("#checkemail").html('사용할 수 있는 이메일입니다.').css('color','green');
                        $("#email").attr("readonly");
                        $("#inserCheck").removeAttr("disabled").css("background-color","#F0A500");
                    } 
                },
                error : function(){
                    alert("서버요청실패")
                }
                
            });
        });
	  
    </script>
    <script>
    /* 닉네임금지 */
    function validate() {
    let regexp = /^[관리자]|[운영자][admin]$/gi;
    let nicName = $("#nickname").val()
	    if(regexp.test(nicName)){
	    	alert("'관리자' 또는 '운영자' 라는 닉네임은 사용할수없습니다.");
	    	return false;
	    	}
   
    

    
    }
    </script>
    
    <script>
    /* <!-- 비밀번호 체크 --> */
    $(function () {
    	$("#password").keyup(function(){
        	let pwd1=$("#password").val();
        	let pwdCheck2 = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{5,15}$/gi;
        	
	            if(!pwdCheck2.test(pwd1)){
	                $("#reCheckPwd").html('사용할 수 없는 비밀번호입니다.').css('color','red');
	                $("#password").focus();
	                
	            }else{
	                $("#reCheckPwd").html('사용할 수 있는 비밀번호입니다.').css('color','green');
	            }
    	})
	}); 
    </script>
    
    <script>
    /* <!-- 비밀번호 같은지 체크 --> */
    $(function () {
        $("#checkpassword").keyup(function(){
            let pwd1=$("#password").val();
            let pwd2=$("#checkpassword").val();	
                if(pwd1 != "" || pwd2 !=""){
                    if(pwd1 == pwd2){
                        $("#checkPwd").html('비밀번호가 일치합니다.').css('color','green')
                        
                    }else{
                        $("#checkPwd").html('비밀번호가 일치하지 않습니다.').css('color','red')
                        $("#checkpassword").focus();
                    }
                }
        });
    });
    </script>
    
    <script>
    	
    </script>
</body>
</html>
