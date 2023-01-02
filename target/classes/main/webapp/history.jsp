<%@page import="db.WifiService"%>
<%@page import="dto.WifiHistory"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>와이파이 정보 구하기</title>
	<link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h2>위치 히스토리 목록</h2>
	
	<div id="navigation">
		<a href="/"> 홈</a> |
		<a href="/history.jsp"> 위치 히스토리 목록 </a>  |  
		<a href="/loadWifiList.jsp"> Open API 와이파이 정보 가져오기</a>
	</div>
	
	<%
		List<WifiHistory> historyList = new ArrayList();
	
		WifiService wSvc = new WifiService();
		historyList = wSvc.searchWifiHistory();
	%>
	
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>				
		</thead>	
		<tbody>
			<tr>
				<%
					for(WifiHistory wh : historyList) {							
				%>
					<tr>
						<td><%=wh.getId() %></td>
						<td><%=wh.getCoordX() %></td>
						<td><%=wh.getCoordY() %></td>
						<td><%=wh.getDate() %></td>
						<td><%=wh.getNote() %></td>
					</tr>
					
				<%
					}
				%>					
			</tr>		
		</tbody>
	</table>
	
	<script>
		const delBtn = document.querySelectorAll(".delBtn");		
		
		const sendDeleteRowId = (event) => {
			let td = event.path[2];			
			
			const id = td.cells[0].firstChild.data;
			
			location.href="delete.jsp?id="+id;				
		}
						
		delBtn.forEach(button => button.addEventListener("click", sendDeleteRowId));

	</script>
</body>
</html>