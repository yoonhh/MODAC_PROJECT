<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="com.modac.usedProduct.model.vo.Market, 
    com.modac.usedProduct.model.vo.Attachment,
    java.util.ArrayList"%>
<% 
	Market m = (Market)request.getAttribute("m"); 
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
	String[] filePath = {"","","","",""};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
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
		margin-bottom: ;
		color: rgb(197, 196, 196);
		text-align: center;
		border-radius: 0.5%;
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
	}
	#backBtn{
		float: left;
		margin-left: 98px;
		box-sizing: border-box;
		background-color: #BDBDBD;
		border: #BDBDBD;
		width: 100px;
		border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
		font-family: 'Do Hyeon', sans-serif;
		font-size: 18px;
	}
	#updateBtn{
		float: right;
		margin-left: 10px;
		margin-right: 98px;
		background-color: orange;
		border: orange;
		box-sizing: border-box;
		width: 100px;
		height: 35px;
		border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
		font-family: 'Do Hyeon', sans-serif;
		font-size: 18px;
	}
	#deleteBtn{
		float: right;
		margin-right: 98px;
		box-sizing: border-box;
		background-color: rgb(206, 205, 205);
		border: rgb(206, 205, 205);
		width: 100px;
		border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px;
		font-family: 'Do Hyeon', sans-serif;
		font-size: 18px;
	}
	.test{
		border: 1px solid purple;
	}
	.btn-close {
    box-sizing: content-box;
    width: 0.2em;
    height: 1em;
    padding: 0.25em 0.25em;
    color: #000;
    background: transparent url(data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='%23000'%3e%3cpath d='M.293.293a1 1 0 0 1 1.414 0L8 6.586 14.293.293a1 1 0 1 1 1.414 1.414L9.414 8l6.293 6.293a1 1 0 0 1-1.414 1.414L8 9.414l-6.293 6.293a1 1 0 0 1-1.414-1.414L6.586 8 .293 1.707a1 1 0 0 1 0-1.414z'/%3e%3c/svg%3e) center/1em auto no-repeat;
    border: 0;
    border-radius: 0.375rem;
    opacity: .5;
    float: right;
	}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div id="enroll">
		<h3><b>게시글 수정</b></h3>
		<br>

		<form id="enroll-form" action="<%=contextPath%>/update.mk" method="post" enctype="multipart/form-data">
			<!-- 게시글 번호를 hidden으로 넘겨줌 -->
			<input type="hidden" name="mno" value="<%=m.getPostNo()%>" >
	
			<div id="enroll-area" >
				<input type="text" class="title" name="title" value="<%=m.getPostTitle()%>" size="95" required>
				<br>
				
				<!-- 사진 미리보기 -->
				<div class="imgAtt" style="text-align: left;">
					<% if(!list.isEmpty()){
					   	  int i = 0; 
					   	  
						  for(Attachment at : list) {
							 int index = at.getFileLevel();
							 filePath[index] = contextPath+"/"+at.getPath()+at.getNewName(); %>
		
							<!-- 원본파일의 파일번호, 수정명을 hidden으로 넘김 -->
							<input type="hidden" name="originPhotoNo<%=i %>" value="<%=at.getPhotoNo()%>">
							<input type="hidden" name="newPhotoName<%=i %>" value="<%=at.getNewName() %>">
						 <% i++; } 
					   } %>		
					
					<table>
<%-- 						<% for(int i =0 ; i < list.size(); i++) { %> --%>
<%-- 						<% if(!list.get(i).getOriginName().equals("logo.png")){ %> --%>
<!-- 	                   	<th> -->
<%-- 							<input type="button" class="btn-close" id="deleteBtn<%=i %>" aria-label="Close"> --%>
<!-- 	                    </th> -->
<%-- 	                    <%} %> --%>
<%-- 						<%} %> --%>
	                   	<th>
							<input type="button" class="btn-close" id="deleteBtn0" aria-label="Close">
	                    </th>
	                    <th>
							<input type="button" class="btn-close" id="deleteBtn1" aria-label="Close">
	                    </th>
	                    <th>
							<input type="button" class="btn-close" id="deleteBtn2" aria-label="Close">
	                    </th>
	                    <th>
							<input type="button" class="btn-close" id="deleteBtn3" aria-label="Close">
	                    </th>
	                    <tr>
<%-- 	                    <% for(int i = 0 ; i <list.size(); i++) { %> --%>
<!-- 	                       <td> -->
<%-- 	                    	<% if(!list.get(i).getPath().isEmpty()) { %> --%>
<%-- 	                      		<img id="contentImg<%=i %>" src="<%=filePath[i+1] %>" width="180" height="130" value="<%=i+1 %>" >                        --%>
<%-- 	                       <% } %>  --%>
<!-- 	                       </td> -->
<%-- 	                     <% } %> --%>
	                       <td>
	                      		<img id="contentImg0" src="<%=filePath[1] %>" width="180" height="130" value="1">                        
	                       </td>
	                       <td>
								<img id="contentImg1" <%if(!filePath[2].equals("")){ %>src="<%=filePath[2] %>" <%} %> width="180" height="130" value="2">
	                       </td>
	                       <td>
	                          	<img id="contentImg2" <%if(!filePath[3].equals("")){ %>src="<%=filePath[3] %>" <%} %> width="180" height="130" value="3">
	                       </td>
	                       <td>
	                          	<img id="contentImg3" <%if(!filePath[4].equals("")){ %>src="<%=filePath[4] %>" <%} %> width="180" height="130" value="4"> <br>
	                       </td>
	                    </tr>
	                 </table>
					
					<input type="hidden" name="postNo" value='<%=m.getPostNo() %>'>
				</div>
				
				<!-- 첨부파일 등록 -->
				<div id="file-area">
					<input type="file" id="file1" name="file1" onchange="loadImg(this, 1);" accept="image/*">
					<input type="file" id="file2" name="file2" onchange="loadImg(this, 2);" accept="image/*">
					<input type="file" id="file3" name="file3" onchange="loadImg(this, 3);" accept="image/*">
					<input type="file" id="file4" name="file4" onchange="loadImg(this, 4);" accept="image/*">
					<!-- onchange :  input태그의 내용물이 변경될 시 발생하는 이벤트 -->
				</div>
				
				<textarea class="content" name="content" cols="100" rows="15" required><%=m.getPostContent() %></textarea>
				<br><br>
			</div>
			<br>
			
			<script>
				//삭제 버튼 반복문 ver.
				$(function(){
               for(let i = 0; i < 4; i++) {
                  $("#deleteBtn"+i).click(function(){
                     let   newName = $("[name=newPhotoName"+i+"]").val();
                     let photoNo = $("[name=originPhotoNo"+i+"]").val();
                     $.ajax({
                           url : "<%= request.getContextPath() %>/delete.at",
                           data : {newName,
                                 photoNo},
                           success : function(result){
                              //삭제성공시
                              if(result == "NNNNY"){
                                 alert("삭제에 성공했습니다");
                                 //기본로고 재등록
                                 
                                 $("#contentImg"+i).attr("src","resources/modacLogo/logo.png")
                              }else{
                                 alert("삭제에 실패했습니다.");
                           }
                           }
                        })
                     })   
               } 
            })
				
				
// 				$(function(){
// 					$("#deleteBtn0").click(function(photoNo){
// 						$.ajax({
<%-- 				            url : "<%= request.getContextPath() %>/delete.at", --%>
<%-- 				            data : {newName : "<%=list.get(0).getNewName()%>", --%>
<%-- 				            		photoNo : <%=list.get(0).getPhotoNo() %>}, --%>
// 				            success : function(result){
// 				               //삭제성공시
// 				               if(result == "NNNNY"){
// 				                  alert("삭제에 성공했습니다");
// 				                  $("#contentImg").attr("src","resources/modacLogo/logo.png");
// 				                  //location.reload();
// 				               }else{
// 				                  alert("삭제에 실패했습니다.");
// 				               }
// 				            }
// 				         });
// 					})
// 				})
				
// 				$(function(){
// 					$("#deleteBtn1").click(function(photoNo){
// 						$.ajax({
<%-- 				            url : "<%= request.getContextPath() %>/delete.at", --%>
<%-- 				            data : {newName : "<%=list.get(1).getNewName()%>", --%>
<%-- 			            			photoNo : <%=list.get(1).getPhotoNo() %>}, --%>
// 				            success : function(result){
// 				               //삭제성공시
// 				               if(result == "NNNNY"){
// 				                  alert("삭제에 성공했습니다");
// 								  $("#contentImg1").attr("src","resources/modacLogo/logo.png")
// 				                  //location.reload();
// 				               }else{
// 				                  alert("삭제에 실패했습니다.");
// 				               }
// 				            }
// 				         })
// 					})
// 				})
				
// 				$(function(){
// 					$("#deleteBtn2").click(function(photoNo){
// 						$.ajax({
<%-- 				            url : "<%= request.getContextPath() %>/delete.at", --%>
<%-- 				            data : {newName : "<%=list.get(2).getNewName()%>", --%>
<%-- 			            		photoNo : <%=list.get(2).getPhotoNo() %>}, --%>
// 				            success : function(result){
// 				               //삭제성공시
// 				               if(result == "NNNNY"){
//  				                  alert("삭제에 성공했습니다");
//  				                  //location.reload();
// 								  $("#contentImg2").attr("src","resources/modacLogo/logo.png")
// 				               }else{
// 				                  alert("삭제에 실패했습니다.");
// 				               }
// 				            }
// 				         })
// 					})
// 				})
				
// 				$(function(){
// 					$("#deleteBtn3").click(function(photoNo){
// 						$.ajax({
<%-- 				            url : "<%= request.getContextPath() %>/delete.at", --%>
<%-- 				            data : {newName : "<%=list.get(3).getNewName()%>", --%>
<%-- 			            			photoNo : <%=list.get(3).getPhotoNo() %>}, --%>
// 				            success : function(result){
// 				               //삭제성공시
// 				               if(result == "NNNNY"){
//  				                  alert("삭제에 성공했습니다");
//  				                  //location.reload();
// 				                  $("#contentImg3").attr("src","resources/modacLogo/logo.png")
// 				               }else{
// 				                  alert("삭제에 실패했습니다.");
// 				               }
// 				            }
// 				         })
// 					})
// 				})
				
			
				<!-- 이미지 삭제버튼 -->
			//	$(function () {
			//	$("ccccccc").click(function () {
			<%-- 							url : "<%=contextPath%>/delete.at", --%>
			//		data : {
			//			for(int i = 0; i<=cccccccc i++){
			//				String newName = list.get(i).getNewName();
			// 			int photoNo = list.get(i).getPhotoNo();
			// 			}
			//			newName : newName,
			//			photoNo : photoNo
			//		},
			//     success : function(result){
			//            //삭제성공시
			//            if(result == "NNNNY"){
			//               alert("삭제에 성공했습니다");
			//               location.reload();
			//            }else{
			//               alert("삭제에 실패했습니다.");
			//            }
			//         }
			   
			//	}) 
			//})
			
			</script>
			
			

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
					//inputFile : 현재 변화가 생긴 input type="file"요소 객체
					//num : 몇 번쩨 input요소인지 확인 후 해당 영역에 미리보기 하기 위한 하드코딩된 변수
					
					//console.log(inputFile.files, inputFile.files.length);
				/*
					파일 선택시 length=1 , 파일 선택 취소시 length=0 이 출력된다.
					=> 파일의 존재유무를 알 수 있다.
					files속성은 업로드 된 파일의 정보들을 배열 형식으로 여러개 묶어서 반환, length는 그 배열의 크기
				*/
					if(inputFile.files.length != 0){
						//선택한 파일이 존재할 경우
						//선택된 파일을 읽어들여서 그 영역에 맞는 곳에 미리보기
						
						//파일을 읽어들일 FileReader객체 생성
						let reader = new FileReader();
						
						//파일을 읽어들이는 메소드 -> 어느 파일을 읽어들일지 매개변수로 제시해야한다.
						//0번 인덱스에 담긴 파일정보를 제시
						//=> 해당 파일을 익어들이는 순간 해당 파일만의 고유한 url이 부여된다.
						//=> 해당 url을 src의 속성값으로 제시
						reader.readAsDataURL(inputFile.files[0]);
						
						//파일 읽기가 완료되었을 때 실행할 함수 정의
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
			</script>
			
			<input class="btn btn-secondary" id="backBtn" onclick="history.back()" value="이전으로"> 
			<input type="submit" class="btn btn-secondary" id="updateBtn" value="수정">
		</form>
	</div>
	<br>
	

<br><br><br><br><br>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

</body>
</html>