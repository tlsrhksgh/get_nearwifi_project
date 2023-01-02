<%@page import="wifi.GetWifiInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
		text-align: center;
	}
</style>
<meta charset="UTF-8">
<title>와이파이 리스트</title>
</head>
<body>
	<%
	GetWifiInfo wifiTest = new GetWifiInfo();
			int listCount = wifiTest.getWifiList();
	%>		
		<h2><%=listCount %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h2>
		<a href="/index.jsp">홈 으로 가기</a>
</body>
</html>