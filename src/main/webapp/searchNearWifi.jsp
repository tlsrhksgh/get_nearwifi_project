<%@page import="db.WifiService"%>
<%@page import="java.util.*"%>
<%@page import="java.util.stream.Stream" %>
<%@page import="dto.WifiInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근처 와이파이 조회</title>

<link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
		<%
			double lat = Double.parseDouble(request.getParameter("latitude"));		
			double lnt = Double.parseDouble(request.getParameter("longitude"));
			int selectRowLimit = -1;
			
			List<WifiInfo> wifiList = new ArrayList<WifiInfo>();

			
			// 내 위치에서 가장 가까운 와이파이 조회 20건 
			WifiService wSvc = new WifiService();
			wifiList = wSvc.searchWifi(lat, lnt);
			
			// 객체 끼리 비교 후 거리 순으로 정렬
			Collections.sort(wifiList, new Comparator<WifiInfo>() {
	            @Override
	            public int compare(WifiInfo o1, WifiInfo o2) {
	                if (o1.getWifiDistance() < o2.getWifiDistance()) {
	                    return -1;
	                } else if (o1.getWifiDistance() > o2.getWifiDistance()) {
	                    return 1;
	                }
	                return 0;
	            }
	        });
			
			
			// 현재 내 x,y 좌표 값 추출 후 히스토리 테이블 insert 
			wSvc.insertHistory(lat, lnt);

		%>
		
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
			</thead>	
			<tbody>
				<tr>
					<%
						for(WifiInfo wifi : wifiList) {			
							selectRowLimit++; 
							if(selectRowLimit == 20) {
								break;
							}
					%>									
						<tr>
							<td><%=wifi.getWifiDistance() %></td>
							<td><%=wifi.getWifiMgrNo() %></td>
							<td><%=wifi.getWifiWard() %></td>
							<td><%=wifi.getWifiName() %></td>
							<td><%=wifi.getWifiAddr1() %></td>
							<td><%=wifi.getWifiAddr2() %></td>
							<td><%=wifi.getWifiInstlFloor() %></td>
							<td><%=wifi.getWifiInstlType() %></td>
							<td><%=wifi.getWifiInstlInstt() %></td>
							<td><%=wifi.getWifiSvcSection() %></td>
							<td><%=wifi.getWifiNetType() %></td>
							<td><%=wifi.getWifiInstlYear() %></td>
							<td><%=wifi.getWifiInOutDoor() %></td>
							<td><%=wifi.getWifiConnEnv() %></td>
							<td><%=wifi.getWifiCoordX() %></td>
							<td><%=wifi.getWifiCoordY() %></td>
							<td><%=wifi.getWifiWorkDate() %></td>
						<tr>										
					<%
						}
					%>
				</tr>		
			</tbody>
		</table>
		
		
		<script src="./js/index.js"></script>
</body>
</html>