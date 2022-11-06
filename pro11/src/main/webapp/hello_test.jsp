<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! String name = "duke"; 
	public String getName(){return name;};%>
<%String age = request.getParameter("age"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=name %></h1>
	<h1><%=age %></h1>
</body>
</html>