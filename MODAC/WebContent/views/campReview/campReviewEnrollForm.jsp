<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		#enroll-form{
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
              
              <form id="enroll-form" action="<%=contextPath %>/insert.cr" method="post" enctype="multipart/form-data">
              	<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo() %>">
	              
	            <div class="fooorm-control">
	            	<br>
              		<h3 class="title">캠핑장 리뷰</h3>
              		<br>
	            	<input type="text" class="form-control" placeholder="제목을 입력해주세요." name="title" aria-label="title">
	                <input type="file" class="form-control" name="upfile" onchange="loadImg(this, 1);">
	
	               	<div class="form-control" style="height:100%;">
	                	<div style="text-align:center;" >
		               		<img id="titleImg" width="80%" height="100%">
		               		<button type="button" class="btn-close" id="delete" aria-label="Close" style="vertical-align: bottom; display:none;"  onclick="deleteAttachment();"></button>
		                </div>
	                  	<br>
	               		<textarea class="fooorm-control" name="content" style="height:500px;"></textarea>
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
	              </div>
	              
	              <br>
	              <div align="center">
                    <button type="button" class="btn" id="button1" class="last1" onclick="history.back();">목록으로</button>
                    <button type="submit" class="btn" id="button2" class="last1">등록하기</button>
                  </div>
	          </form>
	          
		      <script>
			      function loadImg(inputFile, num){
			          if(inputFile.files.length != 0){
		
			              let reader = new FileReader();
			              reader.readAsDataURL(inputFile.files[0]);
			              
			              // 파일 읽기가 완료되었을때 실행할 함수 정의
			              reader.onload = function(e){
			             	  $("#titleImg").css("display","inline");
				        	  $("#titleImg").attr("src",null);
			                  $("#titleImg").attr("src",e.target.result);
			              }
			         	 $("#delete").css("display","inline");
			          }
			       }
			      
		      
			      function deleteAttachment(fileNo){
		        	 $("#titleImg").css("display","none");
		        	 $("#delete").css("display","none");
			      }
		      </script>

		      
	          <br>
              <br>
           </div>
        </div>

 


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>