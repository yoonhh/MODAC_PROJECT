<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.modac.camp.model.vo.Camp, java.util.ArrayList"%>
    
<%
	Camp c = (Camp) request.getAttribute("c");
	ArrayList<Camp> n1 = (ArrayList<Camp>) request.getAttribute("n1");
	ArrayList<Camp> n2 = (ArrayList<Camp>) request.getAttribute("n2");
	ArrayList<Camp> n3 = (ArrayList<Camp>) request.getAttribute("n3");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세부 정보</title>


<style>

	.total-area {
		margin: auto;
		width: 1200px;
	}
	#info-area {
		border: 1px solid lightgray;
		padding-top: 30px;
		padding-bottom: 30px;
	}
	#detail-area {
		border: 1px solid lightgray;
		padding-top: 30px;
		padding-bottom: 30px;
	}


	table, tr, td {
		/* border: 1px solid green; */
	}


	#info-table {
		margin: auto;
		width: 1000px;
	}
	#info-table td {
		padding: 20px;
	}
	.campInfo {
		text-align: center;
		font-weight: bold;
	}




	#them-table {
		margin: auto;
		width: 1000px;
	}
	#them-table td {
		padding-left: 30px;
		padding-right: 30px;
		padding-top: 10px;
		padding-bottom: 10px;
	}
	.them-title {
		font-weight: bold;
		font-size: large;
	}



	#themImg {
		text-align: center;
	}



	#btnback {
		margin-top: 40px;
		background-color: gray;
        color: white;
        border: none;
		width: 100px;
		height: 30px;
	}

	
	.option {
		width: 130px;
		text-align: center;
	}
	
	#camp-img {
		margin: auto;
		padding: 0px;
		/* text-align: center; */
		width: 300px;
		height: 200px;
	}



</style>

</head>
<body>

	<%@ include file = "../common/menubar.jsp" %>

	<div style="margin-bottom: 50px; margin: auto; width: 1200px; padding-bottom: 50px;">
		<div class="total-area">
			<div id="info-area">
				<table id="info-table">
					<tr>
						<% if(c.getAreaImg() != null ) { %>
							<td rowspan="4" style="width: 300px; height: 200px; text-align: center; padding: 3px;"><img id="camp-img" src="<%=contextPath%>/resources/campImg/<%=c.getAreaImg() %>.jpg" alt="로딩실패"></td> <!-- 이미지 부분 -->
						<% } else { %>
							<td rowspan="4" style="width: 300px; height: 200px; text-align: center; padding: 3px;"><img id="camp-img" src="<%=contextPath%>/resources/최종로고_1.png" alt="로딩실패"></td>
						<% } %>
						<td colspan="2" style="font-weight: bolder;"><h4><%=c.getCampName() %></h4></td>
					</tr>
					<tr>
						<td class="campInfo" style="width: 130px;">위치</td>
						<td><%=c.getAddress() %></td>
					</tr>
					<tr>
						<td class="campInfo">웹페이지</td>
						<% if(c.getCampWeb() != null) { %>
							<td><a href="<%=c.getCampWeb() %>"><%=c.getCampWeb() %></a></td>
						<% } else {%>
							<td>(정보 없음)</td>
						<% }%>
					</tr>
					<tr>
						<td class="campInfo">전화번호</td>
						<td><%=c.getCampCall() %></td>
					</tr>
				</table>
			</div>
			<div id="detail-area">
				<table id="them-table">
					<tr class="them-title">
						<td colspan="6">자연경관</td>
					</tr>
					<tr>
						<% for(Camp c1 : n1) { %>
							<td id="themImg"><img class="option" src="<%=contextPath%>/resources/optionImg/<%=c1.getCampImg() %>.png" alt="로딩 실패"></td>
						<% } %>
					</tr>
					<tr>
						<td class="them-title" colspan="6">지형</td>
					</tr>
					<tr class="them-con">
						<% for(Camp c2 : n2) { %>
							<td id="themImg"><img class="option" src="<%=contextPath%>/resources/optionImg/<%=c2.getCampImg() %>.png" alt="로딩 실패"></td>
						<% } %>
					</tr>
					<tr>
						<td class="them-title" colspan="6">편의시설</td>
					</tr>
					<tr class="them-con">
						<% for(Camp c3 : n3) { %>
							<td id="themImg"><img class="option" src="<%=contextPath%>/resources/optionImg/<%=c3.getCampImg() %>.png" alt="로딩 실패"></td>
						<% } %>
					</tr>
					<tr>
						<td class="them-title" colspan="6">상세내용</td>
					</tr>
					<tr class="them-con">
						<td colspan="6"><%=c.getCampContent() %></td>
					</tr>
				</table>
			</div>
		</div>

		<input type="button" class="btn" id="btnback" value="목록" onClick="history.back()"/>
	</div>

	<%@ include file="../common/footerbar.jsp" %>





</body>
</html>