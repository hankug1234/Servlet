<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
 %>
 <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN</title>
</head>
<body>
	<h1 style="text-align:center">LOGIN</h1>
	<form method="post" action="${contextPath }/member/addmember.do">
		<table align="center">
			<tr>
				<td width="200">
					<p align="right">ID</p>
				</td>
				<td width="400">
					<input type="text" name="id" width="200"/>
				</td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">PASSWORD</p>
				</td>
				<td width="400">
					<input type="password" name="pwd" width="200"/>
				</td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">NAME</p>
				</td>
				<td width="400">
					<input type="text" name="name" width="200"/>
				</td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">EMAIL</p>
				</td>
				<td width="400">
					<input type="text" name="email" width="200"/>
				</td>
			</tr>
			<tr>
				<td  width="200">
				 <p>&nbsp</p>
				</td>
				<td width="400">
					<input type="submit" value="submit"/>
					<input type="reset" value="reset"/>
				</td>
			</tr>
		</table>
		
		
	</form>
</body>
</html>