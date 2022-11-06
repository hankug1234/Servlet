<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Print Result</h1>
	<%
		request.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		session.setAttribute("test","test1");
		
	%>
	<h3><%=user_id %></h3>
	<h3><%=user_pw %></h3>
</body>
</html>