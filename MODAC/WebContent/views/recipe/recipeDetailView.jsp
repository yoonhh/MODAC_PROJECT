<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.modac.recipe.model.vo.Recipe, com.modac.common.model.vo.*"%>
<%
	Recipe r = (Recipe)request.getAttribute("r");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head><script type="text/javascript" src="/___vscode_livepreview_injected_script"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
		@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
		.title {
        	margin-top: 40px;
        	color: rgb(74,57,51);
        	font-family: 'Hahmlet', serif;
        	font-size: 35px;
		}
		#button1 {
    		background-color: #BDBDBD;
        	border: #BDBDBD;
        	color: white;
    	}
    	#button2 {
    		background-color: orange;
        	border: orange;
        	color: white;
    	}
    	.sidemenu {
    		font-family: 'Do Hyeon', sans-serif;
        	color: #4a3933;
        	font-size: 30px;
    	}
    	.sidemenu2 {
        	font-family: 'Do Hyeon', sans-serif;
        	color: #4a3933;
        	font-size: 20px;
    	}
	.content1 {
		width: 20%;
		height: 1000px;
		padding: 50px 20px 10px;
		background-color: antiquewhite;
		float: left;
	}
	.content2 {
		width: 80%;
		padding: 10px 50px 20px;
		float: left;
	}
	.form-control {
		margin: 5px;
	}
	.last {
		margin: auto;
	}
	.date {
		float: right;
	}
	.insert-area {
		width: 80%;
		margin: auto;
	}
	.foorm-control {
	    display: block;
	    width: 100%;
	    padding: 0.375rem 0.75rem;
	    font-size: 1rem;
	    font-weight: 400;
	    line-height: 1.5;
	    color: #495057;
	    background-color: #fff;
	    background-clip: padding-box;
	    border: 1px solid #ced4da;
	    border-radius: 0.25rem;
	    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
		}
		div{
			word-break:break-all;
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
       
       table{
          width : 100%;
       }
       
       table tr{
          width : 100%;
          border-bottom : 1px solid antiquewhite;
       }
</style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
       
           <div class="content1">
			<nav class="flex-column">
				<a class="sidemenu" aria-current="page" href="#"><i class="bi bi-fire"></i> &nbsp;모닥불이야기</a><br><br> 
                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.cr">캠핑장 리뷰</a>
                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.r">캠핑 레시피</a>
                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.cs">캠핑스타그램</a>
			</nav>
           </div>
           
           <div class="content2">
			   <div class="insert-area">
			   <br>
               <h3 class="title">캠핑 레시피</h3>
               <br>
	               <div align="right">
	                 <% if(loginMember != null && loginMember.getMemberNic().equals(r.getMemberNic())) {%>
	                  	<a href="<%=contextPath %>/updateForm.r?rno=<%=r.getPostNo()%>" class="btn" id="button2">수정하기</a>
	                  	<a href="<%=contextPath %>/delete.r?rno=<%=r.getPostNo()%>" class="btn" id="button1">삭제하기</a>
	                 <% } %> 
	               </div>
				   <br>
				   
	               <div class="foorm-control">
	                  <br><br>
	                  <h3>&nbsp;<%=r.getPostTitle()%></h3>
	                 
	                  <br><br>
	                  
	                <span style="font-size: 20px;">&nbsp;<i class="bi bi-person-circle"></i>&nbsp;<%=r.getMemberNic() %></span>

					<span class="date" style="font-size: 16px;"><%=r.getCreateDate() %>&nbsp;</span>
	                 
	                  <br><br>
	                  <div class="foorm-control">
	                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-alarm" viewBox="0 0 16 16"> <path d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"/>
	                      <path d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"/> 
	                      </svg></label>소요시간 :<%=r.getTime() %>
	                  
	                      <br>
	                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart" viewBox="0 0 16 16" >
	                      <path d="M4 11H2v3h2v-3zm5-4H7v7h2V7zm5-5v12h-2V2h2zm-2-1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1h-2zM6 7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm-5 4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3z"/>
	                      </svg></label>난이도 :  <%=r.getDifficulty() %>
	            
	                      <br>
	                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-basket2" viewBox="0 0 16 16">
	                      <path d="M4 10a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 1 1 2 0v2a1 1 0 0 1-2 0v-2z" />
	                      <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15.5a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-.623l-1.844 6.456a.75.75 0 0 1-.722.544H3.69a.75.75 0 0 1-.722-.544L1.123 8H.5a.5.5 0 0 1-.5-.5v-1A.5.5 0 0 1 .5 6h1.717L5.07 1.243a.5.5 0 0 1 .686-.172zM2.163 8l1.714 6h8.246l1.714-6H2.163z"/> 
	                      </svg></label>주재료 :  <%=r.getMainIngre() %>
	                 
	                      <br>
	                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-basket2" viewBox="0 0 16 16">
	                      <path d="M4 10a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 1 1 2 0v2a1 1 0 0 1-2 0v-2z" />
	                      <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15.5a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-.623l-1.844 6.456a.75.75 0 0 1-.722.544H3.69a.75.75 0 0 1-.722-.544L1.123 8H.5a.5.5 0 0 1-.5-.5v-1A.5.5 0 0 1 .5 6h1.717L5.07 1.243a.5.5 0 0 1 .686-.172zM2.163 8l1.714 6h8.246l1.714-6H2.163z"/> 
	                      </svg></label>부재료 :  <%=r.getSubIngre() %>
	                  </div>
	                  <br>
		                <div class="form-control" style="height:100%;">
		                	<div style="text-align:center">
		                     <% if(r.getTitleImg() != null ) { %>
			               		 <img src="<%=contextPath%>/<%=r.getTitleImg()%>" width="80%" height="100%">
			                 <% } %>
			                 <br>
				             <% if(at==null) { %>
						 		<!--  첨부파일이 없는경우 -->
						  	 <% } else {%>
								<!-- 첨부파일이 있는경우 -->
								<!-- 브라우저에서 http://localhost:8001/jsp/resources/board_upfiles/xxx.jpg -->
								<a href ="<%=contextPath%>/<%= at.getPath() + at.getNewName() %>"
								download="<%=at.getOriginName() %>">
									<%=at.getOriginName() %>
								</a>
							 <% } %>
			                 </div>
			                  	 <br><br>
			                 
			                 	<p><%=r.getPostContent() %></p>
			                 
		                </div>
				        <hr>
			            <h5>댓글</h5>
			            <%if(loginMember!=null){ %>
			               <div class="reply-area">
			                  <div class="replyText">
			                     <textarea class="inputReply" id="replyContent"></textarea>               
			                  </div>
			                  <div class="replyBtn" onclick="insertReply();">
			                     <h5 style="color: white; text-align : center; margin-top : middle; line-height : 80px; cursor : pointer; " >댓글 등록</h5>
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
			            
			            <div class="replyList">
			               <table sytle="width : 100%;">
			
			
			               </table>
			            </div>
	            </div>
	            <script>
				      
				       $(function(){
				         selectReplyList();
				         setInterval(selectReplyList, 10000);// 괄호 붙이면 메소드가 되어서 한번실행되고 안됨
				         }); 
				       
				         function insertReply(){
				            $.ajax({
				               url:"replyinsert.r",
				               data : {
				                  replycontent : $("#replyContent").val(), 
				                  rno : ${r.postNo} },
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
				         
				          function selectReplyList(){
				            $.ajax({
				               url:"replylist.r",
				               data:{rno : ${r.postNo}},// 객체
				               success:(list)=>{
				                  let result = "";
				                  for(let i of list){
				                     
				                     result+="<tr>"
				                              +"<td>"+i.writer+"</td>"
				                              +"<td>"+i.replyContent+"</td>"
				                              +"<td style='float : right'>"+i.createDate+"</td>"
				                          +"</tr>"
				                  }
				                  $(".replyList>table").html(result); 
				               },
				               error:function(){
				                  console.log("댓글리스트 조회용 ajax통신 실패")
				               }
				            })
				            
				         }  
				         
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
				         	 } */
				      </script>
				      
			    </div>
	        </div>   
	        <br>
            <div align="center">
              <a href="<%=contextPath %>/list.r" class="btn" id="button2">목록으로</a>
            </div>
            <br>
		    <br>
          




 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>