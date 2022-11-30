<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.modac.circle.model.vo.*, com.modac.common.model.vo.*"%>
    <%
 	Circle c = (Circle)request.getAttribute("c");
 // 게시글번호, 카테고리명, 제목, 내용, 작성자아이디, 작성일
 
 	Attachment at = (Attachment)request.getAttribute("at");
 //파일번호, 원본명, 수정명, 저장경로
 %>
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
	
	
	.content2 {
		width: 100%;
		padding: 10px 50px 20px;
		float: left;
	}
	.form-control {
		margin: 5px;
	}
	.last1 {
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
		.fooorm-control {
		    display: block;
		    width: 100%;
		    padding: 0.375rem 0.75rem;
		    font-size: 1rem;
		    font-weight: 400;
		    line-height: 1.5;
		    color: #495057;
		    background-color: #fff;
		    background-clip: padding-box;
		    border: 1px solid white;
		    border-radius: 0.25rem;
		    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
		}
		.btn{
 		background-color: #BDBDBD;
       border: #BDBDBD;
       color: white;
 		}
</style>
</head>
<%@ include file="../common/menubar.jsp" %>
       <div class="content">
       
       
                      
           <div class="content2">
			 <br>
               <h3 style=" 
      
      color: rgb(74,57,51);
      font-family: 'Hahmlet', serif;
      font-size: 35px;">동아리 모집</h3>
             <br>

             <form id="enroll-form" action="<%=contextPath %>/cupdate.bo" method="post" enctype="multipart/form-data">
              <input type="hidden" name="postNo" value="<%=c.getPostNo() %>">
             
              	<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo()%>">
	              
	              <div class="foorm-control">
	                 <input type="text" class="form-control" value="<%=c.getPostTitle()%>" aria-label="title" name="title">
	                   <input type="file" class="form-control" name="upfile">
	                
	                  <textarea class="fooorm-control" style="height:500px;" name="content" ><%=c.getPostContent()%></textarea>
					<div class="input-group">
	                  <%if(at!=null){ %>
						<%= at.getOriginName() %>
						<!-- 원본파일의 파일번호, 수정명을 hidden으로 넘길것. -->
						<input type="hidden" name="originFileNo" value="<%=at.getPhotoNo()%>">
						<input type="hidden" name="originFileName" value="<%=at.getNewName()%>">
					<%} %>
					 
					</div>
	                  <div align="center">
                    <button type="submit" class="btn btn-secondary" class="last1" style="background-color: orange;">수정하기</button>

                     <button type="button" class="btn btn-secondary" class="last1"onclick="history.back();">이전으로</button>
	                  
	              </div>
                  
            </div>
	          </form>
			</div>
            </div>
            <br>
            
            
            <br>
		    <br>
          </div>
      </div>

 


 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>