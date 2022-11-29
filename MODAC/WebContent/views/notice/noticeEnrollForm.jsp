<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모닥불 소식 글작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
        .content1{
           width: 20%;
		       height : 1000px;
		       padding: 50px 20px 10px;
		       background-color: antiquewhite;
           float: left; 
        }

        .content2{
          width: 80%;
		      padding: 50px 50px 20px;
		      float: left;
        }
        .form-control {
          margin: 5px;
        }
        

        .form-select{
          width: 150px;
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
		
		.list-area { 
 			width: 80%; 
 			margin: auto;
 			}
 			
 		.list-title{
      		color: rgb(74,57,51);
      		font-family: 'Hahmlet', serif;
      		font-size: 35px;	
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
</style>

</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
    <div class="content">
        <div class="content1">
            <nav class="flex-column">
                <a class="nav-link active sidemenu" aria-current="page" href="<%=contextPath %>/noticeList"><h3>공지사항</h3></a><br><br>
                <a class="nav-link sidemenu2" href="<%=contextPath %>/noticeList">모닥불 소식</a>
                <a class="nav-link sidemenu2" href="<%=contextPath%>/campTipList">캠핑 팁</a>
                <a class="nav-link sidemenu2" href="<%=contextPath %>/qaList">Q&A</a>
                <a class="nav-link sidemenu2" href="<%=contextPath %>/faqList">FAQ</a>
              </nav>
        </div>

        <div class="content2">
          <br>
          <div class="list-area">
          <h3 class="list-title">모닥불 소식</h3>
          <br>
			
		  <form id="enroll-form" action="<%=contextPath %>/noticeInsert" method="post" enctype="multipart/form-data" >
	        
	          <div class="foorm-control" id="form-control">
	          	  <input type="hidden" name="writer" value="<%= loginMember.getMemberNic()%>"> <!-- 관리자 닉네임 받아올것 -->
	              
	              <select class="form-select" aria-label="noticeSelect" name="categoryNo">
	                <option selected value="1">일반</option>
	                <option value="2">필독</option>
	              </select>
	
	              <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요." aria-label="title" required>
	              <input type="file" class="form-control" name="upfile1" value="1" onchange="loadImg(this, 1);">
	              <input type="file" class="form-control" name="upfile2" value="2" onchange="loadImg(this, 2);">
	              <input type="file" class="form-control" name="upfile3" value="3" onchange="loadImg(this, 3);">
	              <input type="file" class="form-control" name="upfile4" value="4" onchange="loadImg(this, 4);">
	              
	              
	              <img id="titleImg" width="212" height="180" style="border:1px solid white;">
	              <img id="contentImg1" width="212" height="180" style="border:1px solid white;">
	              <img id="contentImg2" width="212" height="180" style="border:1px solid white;">
	              <img id="contentImg3" width="212" height="180" style="border:1px solid white;">
	              
	              
 	             <textarea class="form-control" style="height:500px; resize:none;" name="content" required> </textarea>	         
			 </div>
	          
	          <br><br>
		        
		        <div align="center">
		                
		          <button type="button" class="btn text-white" style="background-color: #BDBDBD;" class="last1" onclick="history.back();">이전으로</button>
		
		          <button type="submit" class="btn text-white" style="background-color: orange;" class="last1">등록하기</button>
		        </div>
		  </form>

        </div>
        
		</div>
        
    </div>

	<script>
		$(function() {

			$("#titleImg").click(function() {
				$("#file1").click();
			});

			$("#contentImg1").click(function() {
				$("#file2").click();
			})

			$("#contentImg2").click(function() {
				$("#file3").click();
			})

			$("#contentImg3").click(function() {
				$("#file4").click();
			});

		});

		function loadImg(inputFile, num) {
			// inputFile : 현재 변화가 생긴 input type="file"요소 객체
			// num : 몇번째 input 요소인지 확인 후 해당 영역에 미리보기 하기 위한 하드코딩된 변수

			// console.log(inputFile.files , inputFile.files.length);
			/*
				파일 선택시 length = 1, 파일선택 취소시 length = 0이 출력됨 => 즉, 파일의 존재유무를 알 수 있다.
				files속성은 업로드된 파일의 정보들을 배열형식으로 여러개 묶어서 반환, length는 그 배열의 크기
			 */
			if (inputFile.files.length != 0) {
				// 선택된 파일이 존재할 경우
				// 선택된 파일들을 읽어들여서 그 영역에 맞는 곳에 미리보기

				// 파일을 읽어들일 FileReader 객체 생성
				let reader = new FileReader();

				// 파일을 읽어들이는 메소드 => 어느 파일을 읽어들일지 매개변수로 제시해야함
				// 0번 인덱스에 담긴 파일정보를 제시
				// => 해당 파일을 읽어들이는 순간 해당 파일만의 고유한 url이 부여된다.
				// => 해당 url을 이미지 src의 속성값으로 제시
				reader.readAsDataURL(inputFile.files[0]);

				// 파일 읽기가 완료되었을 때 실행할 함수 정의
				reader.onload = function(e) { // e. target.result에 reader의 고유 url담김
					// 각 영역에 맞춰서 이미지 미리보기
					switch (num) {
					case 1:
						$("#titleImg").attr("src", e.target.result);
						break;
					case 2:
						$("#contentImg1").attr("src", e.target.result);
						break;
					case 3:
						$("#contentImg2").attr("src", e.target.result);
						break;
					case 4:
						$("#contentImg3").attr("src", e.target.result);
						break;
					}

				}

			} else {
				// 선택된 파일이 사라졌을 경우 미리보기도 사라지게끔 작업
				switch (num) {
				case 1:
					$("#titleImg").attr("src", null);
					break;
				case 2:
					$("#contentImg1").attr("src", null);
					break;
				case 3:
					$("#contentImg2").attr("src", null);
					break;
				case 4:
					$("#contentImg3").attr("src", null);
					break;
				}
			}

		}
	</script>




	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>