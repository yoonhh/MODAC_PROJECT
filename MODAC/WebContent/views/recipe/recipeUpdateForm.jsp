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
        .content1{
           width: 20%;
		   height : 1000px;
		   padding: 50px 20px 10px;
		   background-color: antiquewhite;
            float: left; 
        }

        .content2{
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
</style>

</head>
<body>
<%@ include file="../common/menubar.jsp" %>
       <div class="content">
           <div class="content1">
				<nav class="flex-column">
					<a class="sidemenu" aria-current="page" href="#"><i class="bi bi-fire"></i> &nbsp;모닥불이야기</a><br><br> 
	                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.cr">캠핑장 리뷰</a>
	                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.r">캠핑 레시피</a>
	                <a class="nav-link sidemenu2" href="<%=contextPath%>/list.cs">캠핑스타그램</a>
				</nav>
           </div>
           
           <div class="content2">

           <form id="enroll-form" class="insert-area" action="<%=contextPath %>/update.r" method="post" enctype="multipart/form-data">
              <input type="hidden" name="postNo" value="<%=r.getPostNo() %>">
              <br>
              <h3 class="title">캠핑 레시피</h3>
              <br>
			
              <div class="foorm-control">
              
                <input type="hidden" name="memberNo" value="<%=r.getMemberNo() %>">
           		<input type="text" class="form-control" placeholder="제목을 입력해주세요." aria-label="title" name="title" value="<%=r.getPostTitle() %>">
                <input type="file" name="upfile" onchange="loadImg(this, 1);"><br>
					<% if(at != null){ %>
						<%= at.getOriginName() %>
						<!-- 원본파일의 파일번호, 수정명을 hidden으로 넘길것. -->
						<input type="hidden" name="originFileNo" value="<%=at.getPhotoNo() %>">
						<input type="hidden" name="originFileName" value="<%=at.getNewName() %>">
					<% } %>

                  <div class="mb-10 row">
                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-alarm" viewBox="0 0 16 16"> <path d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"/>
                      <path d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"/> 
                      </svg> 소요시간 : </label>
	                  <div class="col-sm-4">
	                    <input type="text" class="form-control" placeholder="(예 : 10-15분)" name="time" value="<%=r.getTime() %>">
	                  </div>

                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart" viewBox="0 0 16 16" >
                      <path d="M4 11H2v3h2v-3zm5-4H7v7h2V7zm5-5v12h-2V2h2zm-2-1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1h-2zM6 7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm-5 4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3z"/>
                      </svg> 난이도 : </label>
	                  <div class="col-sm-4">
	                    <input type="text" class="form-control" placeholder="(예 : 상, 중, 하)" name="difficulty" value="<%=r.getDifficulty() %>">
	                  </div>
                  </div>
 
                  <div class="mb-10 row">
                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-basket2" viewBox="0 0 16 16">
                      <path d="M4 10a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 1 1 2 0v2a1 1 0 0 1-2 0v-2z" />
                      <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15.5a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-.623l-1.844 6.456a.75.75 0 0 1-.722.544H3.69a.75.75 0 0 1-.722-.544L1.123 8H.5a.5.5 0 0 1-.5-.5v-1A.5.5 0 0 1 .5 6h1.717L5.07 1.243a.5.5 0 0 1 .686-.172zM2.163 8l1.714 6h8.246l1.714-6H2.163z"/> 
                      </svg> 주재료 : </label>
	                  <div class="col-sm-10">
	                    <textarea class="form-control" name="mainIngre"><%=r.getMainIngre() %></textarea>
	                  </div>
                  </div>

                  <div class="mb-10 row">
                      <label class="col-sm-2 col-form-label"> &nbsp;&nbsp; <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-basket2" viewBox="0 0 16 16">
                      <path d="M4 10a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 0 1 2 0v2a1 1 0 0 1-2 0v-2zm3 0a1 1 0 1 1 2 0v2a1 1 0 0 1-2 0v-2z" />
                      <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15.5a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-.623l-1.844 6.456a.75.75 0 0 1-.722.544H3.69a.75.75 0 0 1-.722-.544L1.123 8H.5a.5.5 0 0 1-.5-.5v-1A.5.5 0 0 1 .5 6h1.717L5.07 1.243a.5.5 0 0 1 .686-.172zM2.163 8l1.714 6h8.246l1.714-6H2.163z"/> 
                      </svg> 부재료 : </label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="subIngre" value="<%=r.getSubIngre() %>">
                      </div>
                  </div>
       
                  <div class="form-control" style="height:100%;">
                	  <div style="text-align:center">
                         <% if(r.getTitleImg() != null ) { %>
	               		    <img src="<%=contextPath%>/<%=r.getTitleImg()%>" value="1" width="600px"style="display:inline;" height="100%" id="titleImg">
	               		    <button type="button" id="delete" class="btn-close" aria-label="Close" style="vertical-align: bottom; display:inline;" onclick="deleteAttachment();"></button>
	                     <% } else { %>
	                	    <img value="1" width="600px"style="display:inline;" height="100%" id="titleImg">
	               		    <button type="button" id="delete" class="btn-close" aria-label="Close" style="vertical-align: bottom; display:none;" onclick="deleteAttachment();"></button>
	                     <% } %>
	                  </div>
	                  <br>
	                  <textarea class="fooorm-control" name="content" style="height:100%;"><%=r.getPostContent()%></textarea>
                   </div>
              </div>
              <br>
              <div align="center">
                <button type="button" class="btn" id="button1" onclick="history.back();">이전으로</button>
                <button type="submit" class="btn" id="button2" class="last1">수정하기</button>
              </div>
           </form>
           <br><br>
           </div>
       </div>
       
       <script>
	      function loadImg(inputFile, num){
        	  $("#titleImg").attr("src",null);
        	  
	          if(inputFile.files.length != 0){

	              let reader = new FileReader();
	              reader.readAsDataURL(inputFile.files[0]);
	              
	              // 파일 읽기가 완료되었을때 실행할 함수 정의
	              reader.onload = function(e){
	                  $("#titleImg").attr("src",e.target.result);
	            	  $("#titleImg").css("display","inline");
	              }
	         	 $("#delete").css("display","inline");
	          }
	       }
	      
      
	      function deleteAttachment(fileNo){
        	 $("#titleImg").css("display","none");
        	 $("#delete").css("display","none");
	      }
      </script>


 


 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>