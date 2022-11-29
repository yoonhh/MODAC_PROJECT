<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.modac.campReview.model.vo.*, com.modac.common.model.vo.Attachment"%>
<%
	CampReview cr = (CampReview)request.getAttribute("cr");
	ReviewTag rt = (ReviewTag)request.getAttribute("rt");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head><script type="text/javascript" src="/___vscode_livepreview_injected_script"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<link rel=”stylesheet” href=”http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css“>
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
			 
             <div class="insert-area" style="height:100%">
			 <br>
             <h3 class="title">캠핑장 리뷰</h3>
             <br>
            	<div align="right">
                  <% if(loginMember != null && loginMember.getMemberNic().equals(cr.getMemberNic())) {%>
                    	<a href="<%=contextPath %>/updateForm.cr?crno=<%=cr.getPostNo()%>" class="btn" id="button2">수정하기</a>
                    	<a href="<%=contextPath %>/delete.cr?crno=<%=cr.getPostNo()%>" class="btn" id="button1">삭제하기</a>
                  <% } %> 
                </div>
			    <br>
				    
				<div class="foorm-control">
	                <br><br>
	                <h3>&nbsp;<%=cr.getPostTitle()%></h3>
	                 
	                <br> 
	                
	                <span style="font-size: 20px;"><b>&nbsp;<i class="bi bi-person-circle"></i>&nbsp;<%=cr.getMemberNic() %></b></span>

					<span class="date" style="font-size: 16px;"><%=cr.getCreateDate() %>&nbsp;</span>
	                <br>
	                <div class="form-control" style="height:100%;">
	                	<div style="text-align:center">
	                     <% if(cr.getTitleImg() != null ) { %>
		               		 <img src="<%=contextPath%>/<%=cr.getTitleImg()%>" width="80%" height="100%">
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
		               		 <div style="padding:5px;"><%=cr.getPostContent() %></div>
	                </div>
	
	                <div class=" btn-group-sm foorm-control" role="group" aria-label="Basic checkbox toggle button group">
	                  <input type="checkbox" class="btn-check" name="tag" value="1" id="btncheck1" autocomplete="off">
	                  <label class="btn btn-outline-primary" for="btncheck1">#사장님이 친절해요</label>
	                  
	                  <input type="checkbox" class="btn-check" name="tag" value="2" id="btncheck2" autocomplete="off">
	                  <label class="btn btn-outline-primary" for="btncheck2">#시설이 깔끔해요</label>
	                  
	                  <input type="checkbox" class="btn-check" name="tag" value="3" id="btncheck3" autocomplete="off">
	                  <label class="btn btn-outline-primary" for="btncheck3">#주변 경관이 좋아요</label>
	                 
	                  <input type="checkbox" class="btn-check" name="tag" value="4" id="btncheck4" autocomplete="off">
	                  <label class="btn btn-outline-primary" for="btncheck4">#매너시간이 잘 지켜져요</label>
	                  
	                  <input type="checkbox" class="btn-check" name="tag" value="5" id="btncheck5" autocomplete="off">
	                  <label class="btn btn-outline-primary" for="btncheck5">#사이트 공간이 넓어요</label>
	                  
	                  <input type="checkbox" class="btn-check" name="tag" value="6" id="btncheck6" autocomplete="off">
	                  <label class="btn btn-outline-primary" for="btncheck6">#주변 볼거리가 많아요</label>
	               </div>
	            
	            <hr>
	            <h5>댓글</h5>
	            <%if(loginMember!=null){ %>
	               <div class="reply-area">
	                  <div class="replyText">
	                     <textarea class="inputReply" id="replyContent"></textarea>               
	                  </div>
	                  <div class="replyBtn" onclick="insertReply();">
	                     <h5 style="color: white; text-align : center; margin-top : middle; line-height : 80px;">댓글 등록</h5>
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
	      </div>
	            
	            <script>
			       $(function(){
			         selectReplyList();
			         
			         setInterval(selectReplyList, 10000);// 괄호 붙이면 메소드가 되어서 한번실행되고 안됨
			      }); 
			      
			         
			         function insertReply(){
			            $.ajax({
			               url:"replyinsert.cr",
			               data : {
			                  replycontent : $("#replyContent").val(), 
			                  crno : ${cr.postNo}
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
			         
			          function selectReplyList(){
			            $.ajax({
			               
			               url:"replylist.cr",
			               data:{crno : ${cr.postNo}},// 객체
			               success:(list)=>{
			                  
			                  let result = "";
			                  for(let i of list){
			                     
			                     
			                     result+="<tr>"
			                              +"<td>"+i.writer+"</td>"
			                              +"<td>"+i.replyContent+"</td>"
			                              +"<td>"+i.createDate+"</td>"
			                              
			                          +"</tr>"
			                          
			                     
			                  }
			                  $(".replyList>table").html(result); 
			                  
			               },
			               error:function(){
			                  console.log("댓글리스트 조회용 ajax통신 실패")
			               }
			            })
			            
			         }  


			    <% if(rt != null) {%>
		            $(function(){
		                let tag = "<%= rt.getTagNo()%>"
		                $("input[type=checkbox]").each(function(){
		                    // 순차적으로 접근한 input 요소의 value값이 interest에 포함될경우
		                    // -> 해당 input 요소에 checked 속성을 부여할것.
		                    if(tag.search($(this).val()) != -1){ 
		                        // interest문자열로부터 현재 체크박스의 value가 포함되어있지 않다면 -1을 반환
		                        $(this).attr("checked", true);
		                    }
		                })
		             })
	            <% }%>
		        </script>
	            <br>
	            
	            <div align="center">
	              <a href="<%=contextPath %>/list.cr" class="btn" id="button2">목록으로</a>
	            </div>
	            <br>
			    <br>
		     </div>



 


 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>