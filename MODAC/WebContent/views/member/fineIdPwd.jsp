<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <title>아이디·비밀번호 찾기</title>
        <style>
        
        button {
            cursor: pointer;
        }

        #displayId, #displayPwd {
            /* border: 1px solid black; */
            margin-bottom: 50px;
        }


        .wrapper{
            border: 5px solid #F0A500;
            margin: auto;
            width: 600px;
            height: 500px;
        }


        #fine1 {
            /* border: 1px solid black; */
            text-align: center;
            margin: 0px;
            box-sizing: border-box;
            margin-right: -5px;
        }
        #fine1 button {
            width: 295px;
            height: 80px;
            margin-right: 0px;
        }

        #fine2 {
            /* border: 1px solid black; */
            text-align: center;
            margin: 0px;
            box-sizing: border-box;
            margin-left: -5px;
        }
        #fine2 button {
            width: 295px;
            height: 80px;
            margin-right: 0px;
        }

        #idbtn1 {
            border: none;
            color: rgb(74, 57, 51);
            font-size: large;
            font-weight: bold;
            background-color: white;
            margin: 0px;
        }
        #pwdbtn1 {
            border: none;
            color: white;
            font-size: large;
            font-weight: bold;
            background-color: #F0A500;
            margin: 0px;
        }

        #idbtn2 {
            border: none;
            color: white;
            font-size: large;
            font-weight: bold;
            background-color: #F0A500;
            margin: 0px;
        }
        #pwdbtn2 {
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


        #id-content {
            /* border: 1px solid black; */
            margin-top: 80px;
            /* text-align: center; */
            font-size: 20px
        }

        #pwd-content {
            /* border: 1px solid black; */
            margin-top: 60px;
            /* text-align: center; */
            font-size: 20px
        }


        #divname1 {
            margin-bottom: 60px;
        }
        #divname1>input {
            margin-left: 30px;
            width: 300px;
            height: 50px;
        }
        #divemail1>input {
            margin-left: 15px;
            width: 300px;
            height: 50px;
        }


        #divid {
            margin-bottom: 30px;
        }
        #divid>input {
            margin-left: 15px;
            width: 300px;
            height: 50px;
        }
        #divname2 {
            margin-bottom: 30px;
        }
        #divname2>input {
            margin-left: 30px;
            width: 300px;
            height: 50px;
        }
        #divemail2>input {
            margin-left: 15px;
            width: 300px;
            height: 50px;
        }


        #btn-area1 {
            margin-top: 40px;
            /* border: 1px solid red; */
        }
        #btn-area2 {
            margin-top: 15px;
            /* border: 1px solid red; */
        }
        #check {
            margin-bottom: 0px;
            font-size: 15px;
            padding: 0px;
        }
        #checks {
            margin-bottom: 0px;
            font-size: 15px;
            padding: 0px;
        }
        #btn {
            text-align: center;
            margin-top: 0px;
            width: 300px;
            height: 60px;
            background-color: #F0A500;
            border: none;
            color: white;
            font-weight: bold;
        }
        
       

    </style>
    <%@ include file="../common/menubar.jsp" %>
</head>

<body>
    <div id="displayId">
        <div  class="wrapper">
            <div id="fine1">
                <button id="idbtn1" disabled>아이디 찾기</button>
                <button id="pwdbtn1" onclick="toggleDisplayPWD()">비밀번호 찾기</button>
            </div>

            <div class="content">
                <form id="fineidform" action="<%=contextPath %>/fineId.me" method="post" >
                    <div id="id-content">
                        <div id="divname1">
                            <span>이름</span><input name="memberName" id="memberName" type="text" placeholder="이름을 입력해주세요">
                        </div>
                        <div id="divemail1">
                            <span>이메일</span><input name="email" id="email" type="text" placeholder="이메일을 입력해주세요">
                        </div>
                    </div>
                    <div id="btn-area1">
                        <p class="check" id="check">${check}</p><br/>
                        <button class="" id="btn" type="submit" onclick="id_search();">아이디 찾기</button>
                    </div>
                </form>
            </div>

        </div>
    </div>



    <div id="displayPwd">
        <div class="wrapper">
            <div id="fine2">
                <button id="idbtn2" onclick="toggleDisplayID()">아이디 찾기</button>
                <button id="pwdbtn2" disabled>비밀번호 찾기</button> 
            </div>

            <div class="content">
                <form action="<%=contextPath %>/findPw.me" method="post" class="content">
                    <div id="pwd-content">
                        <div id="divid">
                            <span>아이디</span><input name="memberId" id="memberId"  type="text" placeholder="아아디를 입력해주세요" required>
                        </div>
                        <div id="divname2">
                            <span>이름</span><input name="memberName" id="memberName"  type="text" placeholder="이름을 입력해주세요" required>
                        </div>
                        <div id="divemail2">
                            <span>이메일</span><input name="email" id="email"  type="text" placeholder="이메일을 입력해주세요" required>
                        </div>
                    </div>
                    <div id="btn-area2">
                        <p class="checks" id="check">${check}</p><br>
                        <button id="btn" class="button" type="submit">비밀번호 찾기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    
    <script>
        $("#displayPwd").hide();
        
        const toggleDisplayPWD = () => {
            $("#displayPwd").show(); // display 속성을 block 으로 바꾼다.
            $("#displayId").hide(); // display 속성을 none 으로 바꾼다.
            }
        const toggleDisplayID = () => {
            
            $("#displayId").show(); // display 속성을 block 으로 바꾼다.
            $("#displayPwd").hide();
            }
    </script>
</body>
</html>