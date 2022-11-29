<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.ArrayList, com.modac.usedProduct.model.vo.*"%>
<%
	Market m = (Market)request.getAttribute("m");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
	#content{
        /*border: 3px solid mistyrose;*/
        box-sizing: border-box;
        width: 1200px;
        margin: auto;
		margin-top: -15px;
    }
    .detailView{
        border: 1.5px solid gainsboro;
        box-sizing: border-box;
        width: 800px;
        margin: auto;
        margin-top: 50px;
        border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    }
    .detailView>p{
        font-size: 12px; 
        margin-top: 1px; 
        margin-right: 5px; 
        text-align: right;
        font-weight: 500px;
    }
    .title{
    	margin-left: 240px; 
    	margin-top: 40px;
		color: rgb(74,57,51);
		font-family: 'Hahmlet', serif;
		font-size: 35px;
    }
    .titleInfo{
    	margin-left: 40px;
    }
	#sale{
		color: orange; 
		font-size: 17px; 
		font-family: 'Poor Story', cursive;
	}
    #updateDate{
    	background-color: orange;
    	border: orange;
    	color: white;
    	font-size: 13px;
    	border-radius: 10%;
    	margin-right: 10px;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    	width: 100px;
    	height: 26px;
		font-family: 'Do Hyeon', sans-serif;
    }
    #end{
    	background-color: #BDBDBD;
    	border: #BDBDBD;
    	color: white;
    	font-size: 13px;
    	border-radius: 10%;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    	width: 100px;
    	height: 26px;
		font-family: 'Do Hyeon', sans-serif;
    }
    #endDateBtn{
    	background-color: #BDBDBD;
    	border: #BDBDBD;
    	color: white;
    	font-size: 13px;
    	border-radius: 10%;
    	margin-right: 10px;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    	width: 100px;
    	height: 26px;
		font-family: 'Do Hyeon', sans-serif;
    }
    #endSaleBtn{
    	background-color: #BDBDBD;
    	border: #BDBDBD;
    	color: white;
    	font-size: 13px;
    	border-radius: 10%;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    	width: 100px;
    	height: 26px;
		font-family: 'Do Hyeon', sans-serif;
    }
    .userInfo{
        margin-left: 30px;
        margin-top: 20px;
        height: 10px;
        margin-bottom: -10px;
    }
    .status, .update{
        float: right;
    	margin-right: 2.5%;
    }
    .status{
    	margin-top: 10px;
    	height: 40px;
    	width: 280px;
    	text-align: right;
    }
    .update{
    	margin-top: -10px;
    	color: #ff0707;
    }
    .content{
        /*border: 1px solid mistyrose;*/
        box-sizing: border-box;
        width: 780px;
		height: 250px;
        margin:auto;
    }
    a{
    	color: gray;
    	font-size: 5px;
    	display: inline-box;
    	width: 80px;
    }
    a:hover{
    	text-decoration : none !important
    }
    a:link { 
    	color: #BDBDBD;
    }
    a:visited { 
    	color: #BDBDBD; 
    }
    .list{
    	color: white;
    	background-color: rgb(74,57,51);
    	border : none;
    	width: 80px;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    }
    #listBtn{
    	color: white;
    	background-color: orange;
    	border : none;
    	width: 100px;
		height: 30px;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
    	margin: auto;
        margin-top: 30px;
        margin-left: 550px;
		font-family: 'Do Hyeon', sans-serif;
		font-size: 18px;
    }
    .imgView{
    	margin-left: 10px; 
    	border: 1px solid white;
    }
    p{
    	font-family: 'Hahmlet', serif;
		float: left;
		padding-bottom: -10px;
    }
    .reply-area{
          width : 100%;
          height : 80px;
          margin : auto;
     }
     
     .replyText{
        width : 85%;
        height : 100%;
        border : 1px solid rgb(240, 165, 0);
        float : left;
     }
     
     .replyBtn{
        width : 15%;
        height : 100%;
        background-color : rgb(240, 165, 0);
        float : right;
     }
     
     .inputReply{
        width : 100%;
        height : 100%;
        resize : none;
        border: none;
        outline: none;
     }
     
     .replyList{
        width : 100%;
     }
     #rep{
         width : 100%;
      }
     
     #rep tr{
         width : 100%;
         border-bottom : 1px solid antiquewhite;
      }
       
       
           /*-------------신고하기---------------*/
        /*모달 창 뒤*/
        #modal.modal-overlay { 
            width: 100%;
            height: 100%;
            position: absolute;
            left: 0;
            top: 0;

            display: none;

            flex-direction: column;
            align-items: center;
            justify-content: center;
            background: rgba(255, 255, 255, 0.25);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            backdrop-filter: blur(1.5px);
            -webkit-backdrop-filter: blur(1.5px);
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.18);
        }
        /*모달창*/
        #modal .modal-window {
            background: rgba( 69, 139, 197, 0.70 );
            box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
            backdrop-filter: blur( 13.5px );
            -webkit-backdrop-filter: blur( 13.5px );
            border-radius: 10px;
            border: 1px solid rgba( 255, 255, 255, 0.18 );
            width: 400px;
            height: 500px;
/*             position: relative; */
            top: -100px;
            padding: 10px;
/*             margin-top: 160px;    */
        }
        /*모달 제목*/
        #modal .title {
            
            padding-left: 10px;
            display: inline;
            text-shadow: 1px 1px 2px gray;
            color: white;
            
        }
        #container #btn-modal{
            float: right;
        }

        /*모달 제목 글씨*/
        #modal .title h2 {
            display: inline;
            float: left;

        }
        /*모달창 내용*/
        #modal .content {
            
            margin-top: 20px;
            padding: 0px 10px;
            text-shadow: 1px 1px 2px gray;
            color: white;
        }
        #reportTitle{
            width: 310px;
        }
        #reportContent{
            
        }
        #modal .content{
            width: 350px;
            height: 30px;
        }
        #modal .content {
            width: 350px;
            height: 150px;
        }
        #allbutton{
            padding-top: 140px;
            float: right;
        }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<br>
    <div id="content">
        <h1 class="title"><b>중고장터</b></h1>
        
        <div class="detailView">
        	<!-- 글작성자일 경우 끌어올리기/판매완료 버튼 보임 -->
        	<% if(loginMember != null && loginMember.getMemberNo().equals(m.getMemberNo())){ %>
				<div class="status">
				<%if(m.getSale().equals("Y")) {%>
                	<a href="<%=contextPath%>/updateDate.mk?mno=<%=m.getPostNo()%>" class="btn btn-secondary btn-sm" id="updateDate">끌어올리기</a>
                	<a href="<%=contextPath%>/changeSale.mk?mno=<%=m.getPostNo()%>" class="btn btn-secondary btn-sm" id="end">판매완료</a>
                <% } else {%>
                	<button id="endDateBtn">끌어올리기</button>
                	<button id="endSaleBtn">판매완료</button>
                <% } %>
            	</div>
			<% }
        	
        	if (loginMember != null && !(loginMember.getMemberNo().equals(m.getMemberNo()))){ %>
            <!-- ============================= 신고하기 ============================== -->
		    <div id="container">
		        <button id="btn-modal"  class="btn btn-outline-danger">신고하기</button>
		        <div id="lorem-ipsum"></div>
		    </div>

			<div id="modal" class="modal-overlay">
				<form action="<%=contextPath %>/report.ri" method="post">
					<div class="modal-window">
						<div class="title">
							<h2>신고하기</h2>
							
						</div>
						<hr>
						<div class="content">

							<input type="hidden" name="postNo" 
								value="<%=m.getPostNo()%>">
							
							<p>신고 제목</p><br><br>
							<input type="text" id="reportTitle" name="reportTitle"
								placeholder="신고 제목 입력">
                                <br><br>
							<p>신고 내용</p><br><br>
							<textarea id="reportContent" name="reportContent"
								style="resize: none;" cols="40" rows="5"></textarea>
						</div>
						<hr>
						<div id="allbutton">
							<input id="cancel" type="button" value="취소"
								class="btn btn-secondary">
							<button id="report" type="submit" class="btn btn-danger">신고하기</button>
						</div>
					</div>
				</form>
			</div>
        	<% }%>
    		<script>
        // 모달창열기
        const modal = document.getElementById("modal")
        const btnModal = document.getElementById("btn-modal")
        btnModal.addEventListener("click", e => {
            modal.style.display = "flex"
        })
        
        // 취소버튼으로 닫기
        const closeCancel = modal.querySelector("#cancel")
        closeCancel.addEventListener("click", e => {
            modal.style.display = "none"
        })
   
    </script>
            
            <br>
            <table class="titleInfo">
            	<tr>
            		<td id="sale">
	            		<% if(m.getSale().equals("Y")) { %>
	            			<b>판매중</b>
	            		<% } else if(m.getSale().equals("N")) { %>
	            			<b>판매완료</b>
	            		<% } %> 
            		</td>
            		<td style="font-size:25px;">&nbsp;&nbsp;&nbsp;<b><%=m.getPostTitle()%></b></td>
            	</tr>
            </table>

            <table class="userInfo">
                <tr>
                    <td style="font-size:13px; color: rgb(128, 59, 30);"><b><%=m.getMemberNic()%>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-emoji-dizzy" viewBox="0 0 16 16">
						  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
						  <path d="M9.146 5.146a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708zm-5 0a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 1 1 .708.708l-.647.646.647.646a.5.5 0 1 1-.708.708L5.5 7.207l-.646.647a.5.5 0 1 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 0-.708zM10 11a2 2 0 1 1-4 0 2 2 0 0 1 4 0z"/>
						</svg></b></td>
                    <td style="font-size:4px; color: #BDBDBD;">&nbsp;&nbsp;<%=m.getCreateDate()%></td>
                </tr>
            </table>
            <!-- 글 작성자일 겨우 수정하기/삭제하기 버튼이 보인다. -->
            <% if(loginMember != null && loginMember.getMemberNo().equals(m.getMemberNo())){ %>
	            <div class="update">
	                <a href="<%=contextPath%>/updateForm.mk?mno=<%=m.getPostNo()%>" style="font-size: 0.8em;">수정하기
	                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
						<path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
					</svg>
					</a>&nbsp;
					<a href="<%=contextPath%>/delete.mk?mno=<%=m.getPostNo()%>" style="font-size: 0.8em;">삭제하기
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
							<path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
						</svg>
					</a>
	            </div>
	        <% } %>
            
           <hr>
           
           	<div class="imgView">
<%-- 				<img src="<%=contextPath %>/<%=list.get(0).getPath()+list.get(0).getNewName() %>" width="190" height="150"> --%>
				
				<%for(int i = 0; i< list.size(); i++) { %>
					<img src="<%=contextPath %>/<%=list.get(i).getPath()+list.get(i).getNewName()%>" width="190" height="150" onError="this.src='<%=contextPath %>/resources/modacLogo/logo.png';">
				<% } %>
			</div>
            <br>
            
            <div class="content"><%=m.getPostContent()%></div>
            <br><br><br>
            
            <!--댓글기능 -->
            <p style="font-size:20px;">&nbsp;&nbsp;<b>댓글
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
				<path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
				<path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z"/>
			  </svg></b></p><br>
            <hr>
            
                       
            <%if(loginMember!=null){ %>
               <div class="reply-area">
                  <div class="replyText">
                     <textarea class="inputReply" id="replyContent"></textarea>               
                  </div>
                  <div class="replyBtn" onclick="insertReply();">
                     <h5 style="color: white; text-align : center; margin-top : middle; line-height : 80px; cursor : pointer;">댓글 등록</h5>
                  </div>
               </div>
            <%} else { %>
               <div class="reply-area">
                  <div class="replyText">
                     <textarea class="inputReply" id="replyContent" readonly>로그인 후 이용이 가능한 서비스 입니다.</textarea>               
                  </div>
                  <div class="replyBtn" onclick="insertReply();">
                     <h5 style="color: white; text-align : center; margin-top : middle; line-height : 80px;" disabled>댓글 등록</h5>
                  </div>
               </div>
            <% } %>
            <br>
            <!-- 댓글 조회 -->
            <div class="replyList">
               <table id="rep">
					

               </table>
            </div>

            
            
        </div>
        
        <div class="btn">
            <a href="<%=contextPath%>/list.mk" class="btn btn-secondary btn-sm" id="listBtn">목록으로</a>
        </div>
    </div>
    
    <script>
		
		 $(function(){
			selectReplyList();
			setInterval(selectReplyList, 10000);// 괄호 붙이면 메소드가 되어서 한번실행되고 안됨
		 }); 
		
		function selectReplyList(){
			$.ajax({
				url: "<%= request.getContextPath() %>/mrlist.mk",
				data:{mno : ${m.postNo}},// 객체
				success:(list)=>{
					let result = "";
					for(let i of list){
						result+="<tr>"
									+"<td style='width: 15%'>"+i.writer+"</td>"
									+"<td>"+i.replyContent+"</td>"
									+"<td style='float: right;'>"+i.createDate+"</td>"
							  +"</tr>"
					}
					$(".replyList>table").html(result); 
				},
				error:function(){
					console.log("댓글리스트 조회용 ajax통신 실패")
				}
			})
		};  
	 
		function insertReply(){
			$.ajax({
				url: "<%= request.getContextPath() %>/mrinsert.mk",
				data : {
					replycontent : $("#replyContent").val(), 
					mno : ${m.postNo}
				},
				type : "post",
				success : function(result){
					if(result > 0){// 댓글등록 성공 => 갱신된 댓글리스트 조회
						selectReplyList();
					$("#replyContent").val("");
					}
				},
				error : function(){
					console.log("댓글 작성용 ajax 통신실패")
				}
			})
		};
			
			/* function replyDel() {
		       
		        
		        $.ajax({
		            url  : "replyDel.bo",
		            type : "post",
		            data : {replyNo : replyNo},
		            success : function(data) {
		                   console.log("댓글이 삭제 되었습니다.");
		                  location.reload();
		            },
		            error : function() {
		                console.log("댓글이 삭제되지 않았습니다.");
		            }
		        })
		    } 
  */
		
		
		
		</script>
    

    
    <br><br><br><br><br>
</body>
</html>