<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("UTF-8"); %>

<c:set var="id" value="hong" scope="page" />
<c:set var="pwd" value="1234" scope="page" />
<c:set var="name" value="hong" scope="page" />
<c:set var="age" value="${22}" scope="page" />
<c:set var="height" value="${177}" scope="page" />
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<table align="center" border=1>
	<tr align="center" bgcolor="lightgreen">
		<td width="7%">ID</td>
		<td width="7%">PWD</td>
		<td width="7%">NAME</td>
		<td width="7%">AGE</td>
		<td width="7%">HEIGHT</td>
	</tr>
	 
	<tr align="center">
		<td width="7%">${id}</td>
		<td width="7%">${pwd}</td>
		<td width="7%">${name}</td>
		<td width="7%">${age}</td>
		<td width="7%">${height}</td>
	</tr> 
	<tr align="center">
		<td colspan="5"><a href="${context}/login.html">login</a></td>
	</tr>
</table>
</head>
<body>

</body>
</html>