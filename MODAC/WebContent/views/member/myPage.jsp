<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
    .content>div{

            float: left; 
        }

       .content1 {
		width: 15%;
		height: 1000px;
		padding: 50px 20px 10px;
		background-color: whitesmoke;
		float: left;
	}

        .content2 {
		width: 85%;
		padding: 10px 200px 20px;
		float: center;
	}
        .h5{
            text-align: center;
            margin-top: 50px;
        }
        .outer{
            margin-top: 20px;
            display: table;
            margin-left: auto;
            margin-right: auto;
        }
        .th{
            background-color: lightgray;
        }
        .dt{
            border: #000 solid 1px;
            width: 600px;
            height : 400px;


            margin-top: 70px;
            display: table;



        }
        .nav-link {
            text-decoration: none;
            color: black;
        }
         #navi{
           /* border : 1px solid blue;*/
            list-style-type: none;
            margin: 0;
            
                padding :0;
                height: 100%;
        }
        #navi >li{
            /*border: 1px solid blue;*/
            float: left;
           
            text-align: center;
        }
        
          #navi a:hover{
            font-size: 16px;
            color:  rgb(240,165,0);
        }
        #navi>li>ul{
            list-style-type: none;
            padding: 0;
            display: none;
        }
        #navi>li>ul>a{
           
         font-size: medium;
        font-weight: 600;

        }
        #navi>li>ul>a:hover{
             font-size: medium;
        font-weight: 600;

        }
        
        #navi>li>a:hover+ul{/*동위레벨 선택자
            평소에는 안보여지다가 마우스가 올라갈때만 효과를 부여*/
            display: block;
        }
        #navi>li>ul:hover{
            display: block;
        }
      
</style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
<%		
		String memberId = loginMember.getMemberId();
        String memberName = loginMember.getMemberName();
        String memberNic = loginMember.getMemberNic();
        String email = loginMember.getEmail();
        
      
       
    %>
    <br><br>
 <div class="content">
            <div class="content1">
				 <ul id="navi">
                    <li><a class="nav-link" aria-current="page" href="<%=contextPath%>/myPage.me" style="text-align: center;font-family: 'Do Hyeon', sans-serif;
       
        font-size: 30px; color:orange">개인정보 변경</a></li>
                  	<br><br><br><br>
                   <li>
             		<a href="" class="nav-link" style="text-align: center;font-family: 'Do Hyeon', sans-serif;
       
        font-size: 30px;">자기글 모음</a>
             		<ul>
                    <li><a class="nav-link" href="<%=contextPath%>/myPagecSelf.me"style="text-align: center;font-family: 'Do Hyeon', sans-serif;
       
        font-size: 20px;">동아리</a></li>
                  	<li><a class="nav-link" href="<%=contextPath%>/myPagecrSelf.me"style="text-align: center;font-family: 'Do Hyeon', sans-serif;
       
        font-size: 20px;">캠핑장 리뷰</a></li>
                    <li><a class="nav-link" href="<%=contextPath%>/myPagecpSelf.me"style="text-align: center;font-family: 'Do Hyeon', sans-serif;
        
        font-size: 20px;">캠핑 레시피</a></li>
                    <li><a class="nav-link" href="<%=contextPath%>/myPageupSelf.me"style="text-align: center;font-family: 'Do Hyeon', sans-serif;
        
        font-size: 20px;">중고장터</a></li>
                    </ul>
                    
                 </li>
                    
                   
                    
                    
 				</ul>



            </div>


            <div class="content2">
               


            <form class="dt" id="mypage-form" action="<%=contextPath %>/update.me" method="post">
                <h5 class="h5" style=" 
      
      color: rgb(74,57,51);
      font-family: 'Hahmlet', serif;
      font-size: 35px;">개인정보 변경</h5>
                <hr>
                <div class="form-floating mb-3">
                    <label for="floatingInput">ID</label>
                    <input type="text" class="form-control" name="memberId" id="floatingInput" value="<%=memberId %>" readonly>

                  </div>
                  <div class="form-floating mb-3">
                    <label for="floatingInput">NAME*</label>
                    <input type="text" class="form-control" name="memberName" id="floatingInput" value="<%=memberName %>">

                  </div>
                  <div class="form-floating mb-3">
                    <label for="floatingInput">NICKNAME*</label>
                    <input type="text" class="form-control" name="memberNic" id="floatingInput" value="<%=memberNic %>">

                  </div>
                  <div class="form-floating mb-3">
                    <label for="floatingInput">EMAIL*</label>
                    <input type="email" class="form-control" name="email" id="floatingInput" value="<%=email %>" readonly>

                  </div>

                <br>
                <div align="center">
                    <button type="submit" class="btn btn-secondary btn-sm">정보변경</button>
                    <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#updatePwdForm">비밀번호 변경</button>
                    <button type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                   </div>  
                   

            </form>
            </div>
            </div>
            
  <div id = "updatePwdForm" class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <h5 class="modal-title">비밀번호 변경</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <div class="modal-body" align="center">
      	<form action="<%=contextPath%>/updatePwd.me" method="post">
      		<input type = "hidden" name="userId" value="<%=memberId%>">
      		<table>
      			<tr>
      				<td>현재 비밀번호</td>
      				<td><input type="password" name="memberPwd" required></td>
      			</tr>
      			<tr>
      				<td>변경할 비밀번호</td>
      				<td><input type="password" name="updatePwd" id="updatePwd" required></td>
      			</tr>
      			
      			<tr>
      				<td>변경할 비밀번호 재입력</td>
      				<td><input type="password" name="checkedPwd" required></td>
      			</tr>
      		</table>
      		<br>
      		<button type = "submit" class = "btn btn-secondary btn-sm" onclick="return validatePwd();">비밀번호변경</button>	
      		
      	</form>
<!--       	<script>
        function validatePwd() {
            let pwd1=$("#updatePwd").val();
            let pwdCheck2 = /^[a-z\d!@#$%^&*]{5,15}$/;
            if (!pwdCheck2.test(pwd1)) {
            	
              alert("비밀번호는 영문자+숫자+특수문자 조합으로 5~15자리 사용해야 합니다.");
              return false;
            	};
            }	
      	</script> -->
      	
      	<script>
      		function validatePwd(){
      			let pwdCheck2 = /^[a-z\d!@#$%^&*]{5,15}$/;
      		if($("input[name=updatePwd]").val() != $("input[name=checkedPwd]").val()){
      			alert("비밀번호가 일치하지 않네요");
      			return false;
      			}
      		if(!($("input[name=updatePwd]").val().test(pwdCheck2))){
      			alert("비밀번호는 영문자+숫자+특수문자 조합으로 5~15자리 사용해야 합니다.");
      			return false;
      		}
      		}
      	</script>
      	     
      </div>
      
    </div>
  </div>
</div>

<div id = "deleteForm" class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <h5 class="modal-title">회원탈퇴</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

 <div class="modal-body" align="center">
 		<b>탈퇴 후 복구가 불가능합니다.<br>
 			정말로 탈퇴하시겠습니까?<br><br></b>
      	<form action="<%=contextPath%>/delete.me" method="post">
      		
      		<table>
      			
      			<tr>
      				
      				
      				<td>현재 비밀번호</td>
      				<td><input type="password" name="memberPwd" required></td>
      			</tr>
      			
      		</table>
      		<br>
      		<button type = "submit" class = "btn btn-danger btn-sm">탈퇴하기</button>	
      		
      	</form>
      	
      	
      	
      
      	
      
       
        
      </div>
      
    </div>
  </div>
</div>


</body>
</html>