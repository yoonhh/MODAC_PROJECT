<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><script type="text/javascript" src="/___vscode_livepreview_injected_script"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');

        

        .content2{
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
		.btn{
 		background-color: #BDBDBD;
       border: #BDBDBD;
       color: white;
 		}

</style>

</head>
<body>
<%@ include file="../common/menubar.jsp" %>
        <div class="content">
            <div class="content1">
               
            </div>

            <div class="content2">
				<br>
                <h3 style=" 
      
      color: rgb(74,57,51);
      font-family: 'Hahmlet', serif;
      font-size: 35px;">동아리 모집</h3>
              <br>
              
              <form id="enroll-form" action="<%=contextPath %>/cinsert.bo" method="post" enctype="multipart/form-data">
              
              	<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo()%>">
	              
	              <div class="foorm-control">
	                  <input type="text" class="form-control" placeholder="제목을 입력해주세요." aria-label="title" name="title">
	                  <input type="file" class="form-control" name="upfile">
	                   
	                
	                  <textarea class="fooorm-control" style="height:500px;" placeholder="내용을 입력해 주세요" name="content"></textarea>
	
	                  <div align="center">
                    <button type="submit" class="btn btn-secondary" class="last1" style="background-color: orange" >등록하기</button>

                     <button type="button" class="btn btn-secondary" class="last1"onclick="history.back();">이전으로</button>
	                  
	              </div>
                  
            </div>
	          </form>
	           
            </div>
            </div>

			
       
 


 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
 
</body>
</html>