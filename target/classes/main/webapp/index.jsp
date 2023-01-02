<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Wifi List</title>
	<link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h2>와이파이 정보 구하기</h2>
	
	<div id="navigation">
		<a href="/"> 홈</a> |
		<a href="/history.jsp"> 위치 히스토리 목록 </a> |
		 
		<a href="/loadWifiList.jsp"> Open API 와이파이 정보 가져오기</a>
	</div>
	
	<form action="searchNearWifi.jsp">
		<label for="latitude">LAT</label>
		<input type="text" id="latitude" name="latitude" value="0.0"/> ,
		<label for="longitude">LNT</label>
		<input type="text" id="longitude" name="longitude" value="0.0"/>
		<input type="button" value="내 위치 가져오기" onclick="geoFindMe();">
		<input type="submit" value="근처 wifi 정보 보기">
	</form>
	
	<table>
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WiFi접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>				
			<tr>
				<td colspan="17">
					<h6>위치 정보를 입력한 후에 조회해 주세요.</h6>
				</td>
			</tr>
		</thead>	
		<tbody>
			<tr>
														
			</tr>		
		</tbody>
	</table>
	
	<script src="./js/index.js"></script>
	
</body>
</html>