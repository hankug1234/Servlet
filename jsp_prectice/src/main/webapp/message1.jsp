<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <fmt:setLocale value="ko_RK"/> --%>
<fmt:setLocale value="en_US"/> 
<h1>
	members info<br>
	<fmt:bundle basename="source.member">
	name:<fmt:message key="mem.name"/><br>
	adre:<fmt:message key="mem.address"/><br>
	job :<fmt:message key="mem.job"/><br>
	</fmt:bundle>
	<c:set var="title1" value="hello word!"/>
	<c:set var="title2" value="hello"/>
	<c:set var="title3" value="java"/>
	${fn:length(title1)}<br>
	${fn:contains(title1,title2)}<br>
	${fn:toUpperCase(title1)}<br>
	
	
</h1>

</body>
</html>