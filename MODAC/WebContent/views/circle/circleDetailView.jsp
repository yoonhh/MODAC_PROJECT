<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.ArrayList,com.modac.circle.model.vo.*, com.modac.common.model.vo.*"%>
    <%
 	Circle c = (Circle)request.getAttribute("c");
 // 게시글번호, 카테고리명, 제목, 내용, 작성자아이디, 작성일
 
 	Attachment at = (Attachment)request.getAttribute("at");
 

 
 ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
 

 	
 	
 //파일번호, 원본명, 수정명, 저장경로
 %>
<!DOCTYPE html>
<html>
<head><script type="text/javascript" src="/___vscode_livepreview_injected_script"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
	
	.content2 {
		width: 100%;
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
	hr{
 			border: 0;
    		height: 1px;
    		background: #ccc;"
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
          cursor : pointer;
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
 		.btn{
 		background-color: #BDBDBD;
       border: #BDBDBD;
       color: white;
 		}
 		
	
</style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
     	 
     		
       
                      
           <div class="content2">
			 <br>
               <h3 style=" 
      
      color: rgb(74,57,51);
      font-family: 'Hahmlet', serif;
      font-size: 35px;">동아리 모집</h3>
             <br>
             <div class="insert-area" style="height:100%">
             <%if(loginMember != null && loginMember.getMemberNic().equals(c.getMemberNic())){ %>
			<div align="right">
			<!-- 현재 로그인한 사용자가 해당 글을 작성한 작성자일 경우에만 보여진다. -->
			<a href = "<%=contextPath %>/cupdateForm.bo?bno=<%=c.getPostNo() %>" class = "btn btn-secondary"style="background-color: orange;border: #BDBDBD;">수정하기</a>
			<a href = "<%=contextPath %>/cdelete.bo?bno=<%=c.getPostNo()%>" class = "btn btn-secondary" style="border: #BDBDBD;">삭제하기</a>
			<%} %>
			</div>

             
			<br><br>
             <div class="foorm-control">
                <br><br>
                <h3>&nbsp;<%=c.getPostTitle()%></h3>
                 
                <br>
                <span>&nbsp; 작성자 : <%=c.getMemberNic() %></span>
                 
                <span class="date">작성일 : <%=c.getCreateDate() %></span>
                 
                <br>
                <br>
                
                 
                
                
                  
             
                
                
               
                <div class="form-control" style="height:100%;">
               
               <div style="padding:5px ;height: 500px;"><%=c.getPostContent() %></div>
                
              
                
                
			
            </div>
            <div style="text-align:left">
                <%if(at==null){ %>
						<!-- 첨부파일이 없는경우 -->
						첨부파일이 없습니다.
						<%} else {%>
							<!-- 첨부파일이 있는경우 -->
							<!-- 브라우저에서 http://localhost:8001/jsp/resources/board_upfiles/xxx.jpg -->
							<a href="<%=contextPath %>/<%=at.getPath()+at.getNewName() %>"
							download="<%=at.getOriginName() %>">
								<%=at.getOriginName() %>
							</a>
						<%} %>
					</div>	
            <br>
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
           <br>
            <div align="right">
				<a href="<%=contextPath %>/clist.bo?currentPage=1" class="btn btn-secondary last1"style="background-color: orange;border: #BDBDBD;">목록으로</a>
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
					url:"crinsert.bo",
					data : {
						replycontent : $("#replyContent").val(), 
						bno : ${c.postNo}
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
					
					url:"crlist.bo",
					data:{bno : ${c.postNo}},// 객체
					success:(list)=>{
						
						let result = "";
						for(let i of list){
							
							
							result+="<tr>"
										+"<td>"+i.writer+"</td>"
										+"<td>"+i.replyContent+"</td>"
										+"<td>"+i.createDate+"</td>"
										+"<td><button onclick='repylDel("+i.replyNo+")'>삭제</button></td>"
								  +"</tr>"
								  
							
						}
						$(".replyList>table").html(result);
						
					},
					error:function(){
						console.log("댓글리스트 조회용 ajax통신 실패")
					}
				})
				
			}  
			
			function repylDel(replyNo) {
		       
		        
		        $.ajax({
		            url  : "replyDel.bo",
		            type : "post",
		            data : {replyNo : replyNo},
		            success : function(data) {
		                  alert("댓글이 삭제 되었습니다.");
		                  location.reload();
		            },
		            error : function() {
		                console.log("댓글이 삭제되지 않았습니다.");
		            }
		        })
		    } 
 
		
		
		
		</script>
      

 


 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>