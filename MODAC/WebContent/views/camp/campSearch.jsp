<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.modac.camp.model.vo.Camp, com.modac.common.model.vo.PageInfo"%>
<%
	ArrayList<Camp> list = (ArrayList<Camp>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");

	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>캠핑장 검색</title>

<style>

    #search-area {
        /* border: 1px solid black; */
        background-color: rgb(230, 213, 184);
        width: 1000px;
        margin:  auto;
        padding: 50px 50px;
    }
    
    span {
        color: rgb(74,57,51);
        font-size: 20px;
        /* font-weight: bold; */
    }


    #loacation-s {
        margin-top: 30px;
        margin-bottom: 30px;
        
    }
    #loacation-s>span {
        margin-left: 40px;
    }
    #loc1 {
        width: 200px;
        height: 30px;
        margin-left: 80px;
    }
    #loc2 {
        width: 300px;
        height: 30px;
        margin-left: 30px;
    }



    #facility-s>span {
        margin-left: 10px;
    }

    #facility-s table {
        margin-left: 30px;
        margin-top: 20px;        
    }

    

    .them {
        font-size: 19px;
        /* font-weight: bold; */
        color: rgb(74,57,51);
        padding-right: 25px;
        border-right: 1px solid darkgray;
        border-spacing: 15px;
        font-family: 'Do Hyeon', sans-serif;
    }
    #check td {
        padding-bottom: 15px;
        padding-left: 25px;
    }

  



    #btn-area>input {
        background-color: rgb(240, 165, 0);
        border: none;
        color: white;
        width: 300px;
        height: 40px;
        font-family: 'Do Hyeon', sans-serif;
        font-size: large;
    }




    #result {
        border: 1px solid lightgray;
        width: 1200px;
        margin: auto;
        margin-bottom: 50px;
    }


	.list-area{
		border: 3px solid lightgray;
		text-align: center;
        width: 1150px;
    }

    .list-area td {
        border-bottom: 1px solid lightgray;
    }

    .list-area>tbody:hover {
        cursor: pointer;
        background-color: rgba(211, 211, 211, 0.344);
    }

    .list-area div {
        background-color: rgb(240, 165, 0, 70%);
        color: rgb(74,57,51);
        height: 30px;
        text-align: center;
        font-weight: bold;
    }

    .info {
        font-weight: bold;
        font-size: medium;
        color: rgb(74,57,51);
        border-right: 1px solid lightgray;
        border-bottom: 1px solid lightgray;
    }

    .areaImage {
        border-right: 1px solid lightgray;
    }
    
    
    
    .paging-area {
    	margin: 50px;
    }
    
    .moveBtn{
    	color: white;
    	background-color: rgb(74,57,51);
    	border : none;
    	width: 80px;
    	border-radius: 10px 10px 10px 10px / 10px 10px 10px 10px
    }
    .pageBtn{
    	color: black;
    	background-color: gainsboro;
    	border-radius: 50%;
    	border: gainsboro;
    	width: 30px;
    	height: 30px;
    }
    .pageBtn:hover{
    	width: 30px;
    	height: 30px;
    	color: white;
    	background-color: orange;
    }


    #img-camp {
        margin: auto;
        padding: 0px;
        width: 300px;
        height: 200px;
    }


</style>

</head>
<body>

    <%@ include file = "../common/menubar.jsp" %>

    <br> <br>

    <h1 style="color: rgb(74,57,51); font-family: 'Do Hyeon', sans-serif;" align="center">캠핑장 검색</h1>

	
    <div id="search-area">
    	<form name="searchForm" action="campSearch.ca">
	        <div id="loacation-s">
	            <span style="font-family: 'Do Hyeon', sans-serif;">지역 검색</span>
	            <select name="loc1" id="loc1" onchange="changeLocation(this)">
	                <option value="z">전체/도/시</option>
	                <option value="a">강원도</option>
	                <option value="b">경기도</option>
	                <option value="c">경상남도</option>
	                <option value="d">경상북도</option>
	                <option value="e">전라남도</option>
	                <option value="f">전라북도</option>
	                <option value="g">충청남도</option>
	                <option value="h">충청북도</option>
	                <option value="i">제주도</option>
	                <option value="j">광주시</option>
	                <option value="k">서울시</option>
	                <option value="l">세종시</option>
	                <option value="m">대전시</option>
	                <option value="n">인천시</option>
	                <option value="o">대구시</option>
	                <option value="p">울산시</option>
	                <option value="q">부산시</option>
	            </select>
	
	            <select name="loc2" id="loc2">
	                <option>전체/시/군/기타</option>
	            </select>
	        </div>
	
	        <br>
            <div id="facility-s">
                <span style="font-family: 'Do Hyeon', sans-serif;">테마 및 편의 시설</span>
                <table id="check">
                    <tr>
                    <td class="them">자연경관</td>
                    <td><input type="checkbox" name="item1" id="valley" value="계곡"><label for="valley"> 계곡</label></td>
                    <td><input type="checkbox" name="item1" id="ocean" value="바다"><label for="ocean"> 바다</label></td>
                    <td><input type="checkbox" name="item1" id="mountain" value="산"><label for="mountain"> 산</label></td>
                    <td><input type="checkbox" name="item1" id="river" value="강"><label for="river"> 강</label></td>
                    </tr>
                    <tr>
                        <td class="them">지형</td>
                        <td><input type="checkbox" name="item1" id="grass" value="잔디"><label for="grass"> 잔디</label></td>
                        <td><input type="checkbox" name="item1" id="deck" value="데크"><label for="deck"> 데크</label></td>
                        <td><input type="checkbox" name="item1" id="rock" value="파쇄석"><label for="rock"> 파쇄석</label></td>
                        <td><input type="checkbox" name="item1" id="soil" value="맨흙"><label for="soil"> 맨흙</label></td>
                        <td><input type="checkbox" name="item1" id="etc" value="기타"><label for="etc"> 기타</label></td>
                    </tr>
                    <tr>
                        <td class="them">편의시설</td>
                        <td><input type="checkbox" name="item1" id="toilet" value="공용화장실"><label for="toilet"> 공용화장실</label></td>
                        <td><input type="checkbox" name="item1" id="shower" value="공용샤워실"><label for="shower"> 공용샤워실</label></td>
                        <td><input type="checkbox" name="item1" id="wifi" value="와이파이"><label for="wifi"> 와이파이</label></td>
                        <td><input type="checkbox" name="item1" id="cook" value="개수대(취사장)"><label for="cook"> 개수대(취사장)</label></td>
                        <td><input type="checkbox" name="item1" id="elec" value="전기"><label for="elec"> 전기</label></td>
                        <td><input type="checkbox" name="item1" id="store" value="매점"><label for="store"> 매점</label></td>
                    </tr>
                    <tr>
                        <td class="them">반려동물 동반</td>
                        <td><input type="checkbox" name="pet" id="pet-yes" value="가능" onclick="petCheck(this);"><label for="pet-yes"> 가능</label></td>
                        <td><input type="checkbox" name="pet" id="pet-no" value="불가능" onclick="petCheck(this);"><label for="pet-no"> 불가능</label></td>
                    </tr>
                </table>

                <br>

                <div id="btn-area" align="right">
                    <input type="submit" id="searchBtn" value="검색">
                </div>
            </div>

        </form>

    </div>

    <br> <br>

    <div id="result">

		<table class="list-area" align="center">

            <% if(list.isEmpty()) {%>
                <!-- 리스트가 비어있는 경우 -->
					<tr>
						<td colspan="7">해당하는 캠핑장이 없습니다.</td>
					</tr>
            <% } else {%>
                <% for(Camp c : list) { %>
                    <tbody>
                        <tr>
                            <% if(c.getAreaImg() != null) { %>
                                <td class="areaImage" rowspan="3" style="width: 300px; height: 200px; border-bottom: 3px solid lightgray; padding: 3px;"><img id="img-camp" src="<%=contextPath%>/resources/campImg/<%=c.getAreaImg() %>.jpg" alt="로딩실패"></td>
                            <% } else {%>
                                <td class="areaImage" rowspan="3" style="width: 300px; height: 200px; border-bottom: 3px solid lightgray;"><img id="img-camp" src="<%=contextPath%>/resources/최종로고_1.png" alt="로딩실패"></td>
                            <% } %>
                            <td colspan="2" style="width: 200px; font-weight: bolder;" class="campname"><%= c.getCampName() %></td>
                            <td style="width: 60px;"><div>지역</div></td>
                            <td style="width: 250px;"><%= c.getLocation1() %></td>
                            <td style="width: 50px"></td>
                            <td width="60px"></td>
                        </tr>
                        <tr>
                            <td class="info">위치</td>
                            <td colspan="5"><%= c.getAddress() %></td>
                        </tr>
                        <tr>
                            <td class="info" style="width: 150px; border-bottom: 3px solid lightgray;">테마 및 편의 시설</td>
                            <td colspan="5" style="border-bottom: 3px solid lightgray;"><%= c.getNaturalAttri() %></td>
                        </tr>
                    </tbody>
                <% } %>
            <% } %>
		</table>
		
    </div>
    
    
    
    <!-- 페이징 처리 -->

	<div align="center" class="paging-area">
		<% if(currentPage != 1) {%>
			<button class="moveBtn" onclick="doPageClick(<%=currentPage -1 %>)">&lt;이전</button>
		<% } %>
			
		<% for(int i = startPage; i <= endPage; i++) { %>
			<%if(i != currentPage) {%>
				<button  class="pageBtn" onclick="doPageClick(<%=i%>)"><%=i %></button>
			<%} else {%>
				<button class="pageBtn" disabled style="background-color: orange; color:white"><%= i %></button>
			<%} %>
		<% } %>
			
		<% if(currentPage != maxPage) { %>
			<button class="moveBtn" onclick="doPageClick(<%=currentPage +1 %>)">&gt;다음</button>
		<% } %>
	</div>
    
    
    <%@ include file="../common/footerbar.jsp" %>
    
    

    <script>
        // 지역 검색
        function changeLocation(e) {
            var loc2_a = ['강릉시','동해시','원주시','양양군','영월군','인제군','철원군','평창군','홍천군','횡성군'];
            var loc2_b = ['남양주시','안산시','양주시','용인시','화성시','포천시','가평군','양평군','연천군'];
            var loc2_c = ['김해시','밀양시','사천시','남해군','함안군','합천군'];
            var loc2_d = ['구미시','영천시','포항시','고령군','영덕군','울진군','청도군','칠곡군'];
            var loc2_e = ['광양시','목포시','여수시','고흥군','곡성군','구례군','담양군','무안군','영암군','장성군'];
            var loc2_f = ['군산시','남원시','익산시','정읍시','고창군','부안군','순창군','완주군','임실군','장수군'];
            var loc2_g = ['논산시','당진시','서산시','아산시','부여군','청양군','태안군','홍성군'];
            var loc2_h = ['제천시','청주시','충주시','보은군','옥천군','음성군','진천군'];
            var loc2_i = ['제주시'];
            var loc2_j = ['남구'];
            var loc2_k = ['마포구','은평구'];
            var loc2_l = ['세종시'];
            var loc2_m = ['서구','유성구'];
            var loc2_n = ['계양구','남동구','강화군'];
            var loc2_o = ['동구','달성군'];
            var loc2_p = ['중구','울주군'];
            var loc2_q = ['기장군'];
            var loc2_z = ['전체/시/군/기타']
            var target = document.getElementById("loc2");

            var d;

            switch(e.value) {
                case 'a' : d = loc2_a; break;
                case 'b' : d = loc2_b; break;
                case 'c' : d = loc2_c; break;
                case 'd' : d = loc2_d; break;
                case 'e' : d = loc2_e; break;
                case 'f' : d = loc2_f; break;
                case 'g' : d = loc2_g; break;
                case 'h' : d = loc2_h; break;
                case 'i' : d = loc2_i; break;
                case 'j' : d = loc2_j; break;
                case 'k' : d = loc2_k; break;
                case 'l' : d = loc2_l; break;
                case 'm' : d = loc2_m; break;
                case 'n' : d = loc2_n; break;
                case 'o' : d = loc2_o; break;
                case 'p' : d = loc2_p; break;
                case 'q' : d = loc2_q; break;
                case 'z' : d = loc2_z; break;
            }

            target.options.length = 0;

            for(x in d) {
                var opt = document.createElement("option");
                opt.value = d[x];
                opt.innerHTML = d[x];
                target.appendChild(opt);
            }
        }

        
        // 반려동물 동반 하나만 체크
        function petCheck(element) {
            var checkboxes = document.getElementsByName("pet");
            checkboxes.forEach((e) => {
                e.checked = false;
            })
            element.checked = true;
        } 



        // 세부페이지 이동
        $(function() {
            $(".list-area tbody").click(function() {
				
                // 현재 내가 클릭한 tr의 자손들 중 0번 째에 위치한 자식의 textnode 내용을 가져온다.
                
                //let cName = $(this).children().find('.campname').eq(1).text();
				let cName = this.querySelector(".campname").innerText;
                location.href = '<%=contextPath %>/campInfo.ca?cName='+cName;

            });
        })
        
        
        
        // 페이징
        function doPageClick(currentPage){
			location.href ="<%=contextPath%>/list.ca?currentPage="+currentPage;
		}


    </script>
