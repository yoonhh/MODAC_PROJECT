<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	
    <style>
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
        div>#checkId{
            width: 110px;
            height: 30px;
            background-color: #F0A500;
            color: white;
            border: none;
            margin-top: 5px;
        }
        
        #insertCheck {
            margin-top: 20px;
            margin-left: 100px;
            width: 300px;
            height: 50px;
            border: none; 
        }
    </style>
</head>
<body>


    <%@ include file = "../common/menubar.jsp" %>


    <div id="wrapper">
        <h1>회원가입</h1>
        <hr>

        <form id="enroll-form"action ="<%=contextPath %>/insert.me"  method="post">
        <div id="insertId">
            <span>아이디</span>
            <input id="id" type="text" placeholder="아이디입력" name="memberId" required >

            <button type="button" class="btn btn-warning" id="checkId" onclick="return idCheck();">아이디 확인</button>
            <div id="reCheckId"></div>
            <p>영문자로 시작하는 5~15자 이내의 영문,숫자로 구성 가능</p>
        </div>
        <div>
            <span>비밀번호</span>
            <input id="password" type="password" placeholder="비밀번호" name="memberPwd" min="5" max="20" required >
            <p>5~20자 이내의 영문,숫자,특수문자(!@#$%^&*)로 구성가능</p>
            <div id="reCheckPwd"></div>
        </div>
        <div>
            <span>비밀번호 확인</span>
            <input id="checkpassword" type="password" placeholder="비밀번호 확인" required>
        </div>

        <div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
        <div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>


        <div>
            <span>이름</span>
            <input id="name"type="text" placeholder="이름" name="memberName" required >
        </div>
        <div>
            <span>이메일</span>
            <input id="email" type="email" placeholder="이메일" name="email" required >
        </div>
        <div id="checkemail"></div>


        <div>
            <span>닉네임</span>
            <input id="nickname" type="text" placeholder="닉네임" name="memberNic" required>
            
        </div>
            <input onclick="return validate();" type="submit" id="insertCheck" value="회원가입" name="insertCheck" disabled>
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
    /* $memberId.attr("readonly",true); */
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
    
    <!-- 비밀번호 체크 -->
    <script>
        $(function () {
            $("#password").keyup(function(){
                let pwd1=$("#password").val();
                let pwdCheck2 = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{5,15}$/gi;
                
                
                if(!pwdCheck2.test(pwd1)){
                    $("#reCheckPwd").html('사용할 수 없는 비밀번호입니다.').css('color','red')
                }else{
                    $("#reCheckPwd").html('사용할 수 있는 비밀번호입니다.').css('color','green');
                }
            })
        });
        
        $(function(){
            $("#alert-success").hide();
            $("#alert-danger").hide();
            $("input").keyup(function(){
                var pwd1=$("#password").val();
                var pwd2=$("#checkpassword").val();
                if(pwd1 != "" || pwd2 != ""){
                    if(pwd1 == pwd2){
                        $("#alert-success").show();
                        $("#alert-danger").hide();
                        $("#submit").removeAttr("disabled");
                    }else{
                        $("#alert-success").hide();
                        $("#alert-danger").show();
                        $("#submit").attr("disabled", "disabled");
                    }
                }
            });
        });
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
        function validate() {
            //변수에 담아주기
            /* let id = document.getElementById("id");
            let idCheck2 = /^[a-zA-Z0-9]{5,15}$/gi;
            if(!idCheck2.test(id.value)){
            alert("아이디는 영문자로 시작하는 5~15자 이내의 영문, 숫자로 구성 가능합니다.");
            return false;
            } */
            
            
            /* let pwd1=$("#password").val();
            let pwdCheck2 = /^[a-z\d!@#$%^&*]{5,15}$/gi;
            if (!pwdCheck2.test(pwd1)) {
            alert("비밀번호는 영문자+숫자+특수문자 조합으로 5~15자리 사용해야 합니다.");
            return false;
            }; */
            
            let regexp = /^[관리자]|[운영자]$/gi;
            let nicName = $("#nickname").val()
            if(regexp.test(nicName)){
                alert("'관리자' 또는 '운영자' 라는 닉네임은 사용할수없습니다.");
                return false;
            }
        
        };
    
    </script>


</body>
</html>