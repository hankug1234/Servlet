<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align:center">MODIFY INFOMATION</h1>
	<form method="post" action="${contextPath}/member/modMember.do?id=${info.id}">
		<table>
			<tr align="center">
				<td width="200">ID</td>
				<td width="400">
					<input type="text" name="id" value="${info.id}" disabled/>
				</td>
			</tr>
			<tr align="center">
				<td width="200">PASSWROD</td>
				<td width="400">
					<input type="text" name="pwd" value="${info.pwd}"/>
				</td>
			</tr>
			<tr align="center">
				<td width="200">NAME</td>
				<td width="400">
					<input type="text" name="name" value="${info.name}"/>
				</td>
			</tr>
			<tr align="center">
				<td width="200">EMAIL</td>
				<td width="400">
					<input type="text" name="email" value="${info.email}"/>
				</td>
			</tr>
			<tr align="center">
				<td width="200">$nbsp</td>
				<td width="400">
					<input type="submit" value="submit"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>