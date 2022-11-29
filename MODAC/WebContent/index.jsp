<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>모닥불 : 모두의 캠핑정보 공유 사이트</title>


<style>




#main-img{
	text-align: center;
	margin-left: 0px;
	margin-bottom: 100px;
	margin-top: 50px;
}

#where{
        background-color: rgb(230, 213, 184);
        text-align: center;
        width: 500px;
        height: 150px;
        margin: auto;
        width: 50%;
        line-height: 150px;
		color: rgb(74, 57, 51);
        font-size: 30px;
		border: 3px dotted rgb(74, 57, 51);
}

#buttons {
	text-align: center;
}

#btn-campInfo {
	background-color: rgb(240, 165, 0);
	color:white;
	font-size: 17px;
	width: 300px;
	height: 45px;
}
#btn-notice {
	background-color: gray;
	color: white;
	font-size: 17px;
	width: 300px;
	height: 45px;
	
}
#buttons button {
	border: 0;
	outline: 0;
	border-radius: 4px;
}


#gall-title{
	border-top: 2px solid rgb(240, 165, 0);
	border-bottom: 2px solid rgb(240, 165, 0);
	color: rgb(74, 57, 51);
	font-size: 25px;
	font-weight: bold;
	margin-left: 7%;
	margin-right: 7%;
	padding: 15px 10px 15px 50px;

}

#gall {
	border: 1px solid rgb(240, 165, 0);
	margin-top: 10px;
	margin-left: 10%;
	margin-right: 10%;
}

</style>



</head>
<body>

	<%@ include file="/views/common/menubar.jsp" %>

	<div id="main-img">
		<img src="<%= request.getContextPath() %>/resources/메인 홈페이지 배너.png" alt="이미지 로딩 실패" width="1500px">
	</div>


	<!-- 사이트 소개 이미지 -->

    <!-- <div id="where">
        " 오늘은 어디로 갈까? "
    </div> -->



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
  <script>
    $('.carousel').carousel({
      interval: 2000 //기본 5초
    })
  </script>
<div class="container"></div>
  <div style="text-align: center; margin: auto;">
		<div id="demo" class="carousel slide" data-ride="carousel" >

			<div class="carousel-inner" style="width: 1500px; height: 500px; text-align: center;">
			<!-- 슬라이드 쇼 -->
			<div class="carousel-item active">
				<!--가로-->
				<img class="d-block w-100" 
				src="<%= request.getContextPath() %>/resources/메인화면1.png"
				alt="First slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100"
				src="<%= request.getContextPath() %>/resources/메인화면2.png"
				alt="Second slide">
			</div>
			
			<!-- / 슬라이드 쇼 끝 -->

			<!-- 왼쪽 오른쪽 화살표 버튼 -->
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<!-- <span>Previous</span> -->
			</a>
			<a class="carousel-control-next" href="#demo" data-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<!-- <span>Next</span> -->
			</a>
			<!-- / 화살표 버튼 끝 -->
			
			<!-- 인디케이터 -->
			<ul class="carousel-indicators">
			<li data-target="#demo" data-slide-to="0" class="active"></li> <!--0번부터시작-->
			<li data-target="#demo" data-slide-to="1"></li>
			</ul>
			<!-- 인디케이터 끝 -->
		</div>
		</div>
</div>


	<br> <br>

	<div id="buttons">
		<button id="btn-campInfo" onclick="location.href=''">캠핑장 검색으로</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button id="btn-notice" onclick="location.href=''">모닥불 이야기로</button>
	</div>


	<br> <br> <br>

	<div id="gall-title">
		인기 갤러리
	</div>
	
	<br>

	<div id="gall">
		
	</div>

<br clear="both">

	<%@ include file="/views/common/footerbar.jsp" %>

</body>
<script>
	$.ajax({
		url : "<%=request.getContextPath()%>/best.be",
		success : function (data) {
			$("#gall").html(data);
		}
	})
</script>
</html>

