<%@page import="db.WifiService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Delete</title>
</head>
<body>
	<%
		String id =  request.getParameter("id");
		
		WifiService wSvc = new WifiService();
		wSvc.deleteHistoryRow(id);
	%>
	
	<script>
		function init() {
			location.href="history.jsp";	
		}
		
		init();
	</script>
</body>
</html>