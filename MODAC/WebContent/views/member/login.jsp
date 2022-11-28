<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.modac.member.model.vo.Member"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<style>
li{ list-style: none; }
a{ text-decoration: none; }
button{ cursor: pointer; }

body{
     background-color: #ededed;
     margin-top: 100px;
     border: 1px solid black;
}

#login_form{
    width: 400px; height: 600px;
    margin: auto; /* 블록요소를 부모영역에서 가운데 처리 */
    border: 4px solid #F0A500;
}
h2{
    height: 80px;
    text-align: center;
    padding-top: 40px;
    font-size: 40px;
    color: #F0A500;
}
.login input[type = text], #loginbtn, input[type=password]{
    display: block;
    width: 300px;
    height: 40px;
    margin: 30px auto;
    padding: 0 20px;
    box-sizing: border-box;
    border: 1px solid black;
   
}
.login input[type=checkbox]{
    margin-left: 50px;
}
.login span{
    font-size: 15px;
    padding: 10px;
}


#login_form ul{
    width: 100%;
    text-align: center;
    margin-bottom: 50px;
}
#login_form li{
    display: inline-block;
    height: 13px; 
    line-height: 12px;
}
#login_form li:last-child{
    border-left: 1px solid #333;
    padding-left: 10px; /* 테두리 안쪽 여백 */
    margin-left: 3px; /* 테두리 바깥쪽 여백 */
}
#login_form a{
    /* 글자관련은 보통 최종태그에 줘야 적용 */
    color: #333; 
    font-size: 12px;

    /* 현재 위치에서 상대적 이동 */
    position: relative; 
    top: -2px;
}
</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>

    <script>
        let msg = "<%= alertMsg%>"; // let msg = 성공적으로 로그인이 되었습니다.
        if(msg != "null"){
            alert(msg);
            // 알림창을 띄워준후 session에 담긴 해당메세지는 지워줘야함.
            // 안그러면 menubar.jsp가 로딩될때마다 매번 alert가 계속 뜰것
            <% session.removeAttribute("alertMsg");%>
        }
    </script>



		<div id="back">
     		<form action="<%=contextPath %>/login.me" method="post" id="loginform">
				<div class="login" id="login_form">
				<h2>로그인</h2>
				     <input class="inp" id="loginId" type="text" name="memberId" placeholder="아이디">
				     <input class="inp" id="loginPwd" type="password" name="memberPwd" placeholder="비밀번호">
				     <input type="checkbox" id="saveId"><span>아이디저장</span>
					<button id="loginbtn" type="button" class="btn btn-warning" onclick="submitLogin();">로그인</button>
			      <ul>
			          <li><a href="<%=contextPath%>/MemberFineIdPwd.me">아이디/비밀번호찾기</a></li>
			          <li><a href="<%=contextPath %>/enrollFrom.me">회원가입</a></li>
			      </ul>
  				</div>
 			</form>
		</div>


        <script>
            $(function(){
                getCookie();
            });


            function submitLogin(){
            
                let memberId = $("input[name=memberId]").val();
                
                if($("#saveId").is(":checked")){ // true 체크된 상태
                    document.cookie = "saveId="+memberId+"; path=/; max-age="+60*60*24*7; // 쿠키 최대시간 설정 60ms * 60min * 24hour * 7day
                    console.log("체크");
                } else{ // 체크 안된상태
                    document.cookie = "saveId="+memberId+"; path=/; max-age="+0; // 최대시간을 0으로 설정해서 해당쿠키를 제거.
                }
                let form = $("#loginform");
                form.submit();
            };


            function getCookie(){
                let value = "";
                if(document.cookie.length>0){
                    let index = document.cookie.indexOf("saveId="); // saveId = admin; path=/; max-age=0;
                    if(index != -1){
                        index += "saveId=".length;
                        let end = document.cookie.indexOf(";",index);
                        console.log(index, end);
                        if(end == -1){
                            value = document.cookie.substring(index);
                        }else{
                            value = document.cookie.substring(index, end);
                        }
                        $("input[name=memberId").val(value);
                        $("#saveId").attr("ckecked","true");
                    }
                }
            };
            </script>





</body>


</html>

