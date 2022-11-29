<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Hahmlet&family=Poor+Story&family=Do+Hyeon&display=swap');
	h3{
		font-family: 'Hahmlet', serif;
		margin: auto;
		margin-left: 13%; 
		color: rgb(74,57,51);
	}
	#enroll{
		/*border: 1.5px solid gainsboro;*/
		box-sizing: border-box;
		width: 1000px;
		color: white;
		margin: auto;
		margin-left: 12.5%;
		display: inline-block;
	}
	#enroll-area{
		border: 1.5px solid;
		box-sizing: border-box;
		width: 800px;
		margin: auto;
		margin-top: 20;
		color: rgb(197, 196, 196);
		text-align: center;
		border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
	}
	.title{
		margin-top: 3%;
		margin-left: 2%;
		margin-right: 2%;
		margin-bottom: 10px;
	}
	.content{
		margin-top: 1%; 
		margin-bottom: 10; 
		resize: none;
		border: none;
	}
	.content:focus {
  		outline: 1px solid rgb(175, 174, 174);
		border-radius: 0.5%;
		outline: none;
	}
	.imgAtt{
		border: 1px solid white;
		width: 93%;
		margin: auto;
		margin-top: 10;
		text-align: left;
	}
	#backBtn{
		float: left;
		background-color: #BDBDBD;
		border: #BDBDBD;
		box-sizing: border-box;
		width: 100px;
		margin-left: 98px;
		border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
		font-family: 'Do Hyeon', sans-serif;
		font-size: 18px;
	}
	#enrollBtn{
		float: right;
		background-color: orange;
		border: orange;
		box-sizing: border-box;
		width: 100px;
		margin-right: 98px;
		border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
		font-family: 'Do Hyeon', sans-serif;
		font-size: 18px;
	}
	.test{
		border: 1px solid purple;
	}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	<br>
	<div id="enroll">
		<h3><b>게시글 작성</b></h3>
		<br>
		
		<form id="enroll-form" action="<%=contextPath%>/insert.mk" method="post" enctype="multipart/form-data">
		 
			<input type="hidden" name="memberNic" value="<%=loginMember.getMemberNic()%>">
			<!-- hidden: 이용자에게 보이지 않으면서 값을 넘겨준다. --> 
			
			<div id="enroll-area" >
				<input type="text" class="title" name="title" placeholder="제목을 입력해주세요." size="95" required>
				<br>
				
				<!-- 첨부파일 미리보기 -->
				<div class="imgAtt">
					<img id="contentImg0"  width="180" height="130">
					<img id="contentImg1" width="180" height="130">
					<img id="contentImg2" width="180" height="130">
					<img id="contentImg3" width="180" height="130">
				</div>
				
				<textarea class="content" name="content" placeholder="내용을 입력해주세요." cols="100" rows="15" required></textarea>
				<br><br>
			</div>
			<br>
			
			<!-- 첨부파일 등록 -->
			<div id="file-area">
				<input type="file" id="file1" name="file1" onchange="loadImg(this, 1);" accept="image/*">
				<input type="file" id="file2" name="file2" onchange="loadImg(this, 2);" accept="image/*">
				<input type="file" id="file3" name="file3" onchange="loadImg(this, 3);" accept="image/*">
				<input type="file" id="file4" name="file4" onchange="loadImg(this, 4);" accept="image/*">
				<!-- onchange :  input태그의 내용물이 변경될 시 발생하는 이벤트 -->
			</div>
			
			<script>			
				$(function(){
					//input 태그 숨김
					$("#file-area").hide();
					
					//titleImg클릭시 file1클릭 -> input태그 발동
					$("#contentImg0").click(function(){
						$("#file1").click();
					});
					
					$("#contentImg1").click(function(){
						$("#file2").click();
					});
					
					$("#contentImg2").click(function(){
						$("#file3").click();
					});
					
					$("#contentImg3").click(function(){
						$("#file4").click();
					});
					
				});
				
				//미리보기
				function loadImg(inputFile, num){
					//파일을 첨부했을 경우
					if(inputFile.files.length != 0){
						
						let reader = new FileReader();
						
						reader.readAsDataURL(inputFile.files[0]);
						
						reader.onload = function(e){ // e.target.result에 reader의 고유 url이 담김
							//각 영역에 맞춰서 이미지 미리보기
							switch(num){
							case 1 : $("#contentImg0").attr("src", e.target.result); break;
							case 2 : $("#contentImg1").attr("src", e.target.result); break;
							case 3 : $("#contentImg2").attr("src", e.target.result); break;
							case 4 : $("#contentImg3").attr("src", e.target.result); break;
							}
						}
					}else{
						//선택된 파일이 사라졌을 경우 미리보기도 사라지게 작업
						switch(num){
						case 1 : $("#contentImg0").attr("src", null); break;
						case 2 : $("#contentImg1").attr("src", null); break;
						case 3 : $("#contentImg2").attr("src", null); break;
						case 4 : $("#contentImg3").attr("src", null); break;
						}
					}
				}
				
				//기본 썸네일 추가-실패				
// 				$(function(){
// 					let file = $("#file1").prop("files")[0];
// 					if(file == null){
// 						$("#titleImg").attr("src", "resources/modacLogo/logo.png");
// 					}
// 				});
			</script>
	
			<input class="btn btn-secondary" id="backBtn" onclick="history.back()" value="이전으로"> 
			<input type="submit" class="btn btn-secondary" id="enrollBtn" value="등록하기">
		</form>
	</div>	
	<br>
	

<br><br><br><br><br>
</body>
</html>