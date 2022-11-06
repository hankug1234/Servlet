<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, mvc_test.mvc1.*"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
 %>
 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<c:choose>
		<c:when test='${msg=="add" }'>
			<script>
				window.onload = function(){ alert("add new Membber");};
			</script>
		</c:when>
		<c:when test='${msg=="mod"}'>
			<script>
				window.onload = function(){alert("modify Memberinfo");};
			</script>
		</c:when>
		<c:when test='${msg == "del"}'>
			<script>
				window.onload = function(){alert("del Memberinfo");};
			</script>
		</c:when>
	</c:choose>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.cls1{font-size:40px; text-align: center;}
	.cls2{font-size:20px; text-align: center;}
</style>
</head>
<body>
	<p class="cls1">MEMBER INFORMATION</p>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%">ID</td>
			<td width="7%">PASSWORD</td>
			<td width="7%">NAME</td>
			<td width="7%">EMAIL</td>
			<td width="7%">JOINDATE</td>
			<td width="7%">delete</td>
			<td width="7%">modify</td>
		</tr>
		<c:choose>
			<c:when test="${ empty memberList }">
				<tr align="center">
					<td colspan="5">
						<b>no regist information</b>
					</td>
				</tr>
			</c:when>
			<c:when test="${!empty memberList }">
				<c:forEach var="mem" items="${memberList}">
					<tr align="center">
						<td width="7%">${mem.id}</td>
						<td width="7%">${mem.pwd}</td>
						<td width="7%">${mem.name}</td>
						<td width="7%">${mem.email}</td>
						<td width="7%">${mem.joinDate}</td>
						<td width="7%"><a href="${contextPath}/member/delMember.do?id=${mem.id}">delete</a></td>
						<td width="7%"><a href="${contextPath}/member/memberModify.do?id=${mem.id}">modify</a></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr align="center">
			<td colspan="7">
				<a href="${contextPath}/member/memberForm.do">register</a>
			</td>
		</tr>
	</table>
</body>
</html>