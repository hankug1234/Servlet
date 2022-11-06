<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored = "false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<c:set var="totArticles" value="${articleMap.totArticles }"/>
<c:set var="section" value="${articleMap.section }"/>
<c:set var="pageNum" value="${articleWMp.pageNum }"/>
<c:set var="articleList" value="${articleWMp.articleList}"/>
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<style>
	.cls1{text-decoration:none;}
	.cls2{text-align: center; font-size:30px;}
	.sel-page{text-decoration:none;color:red;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" boarder="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>content NO</td>
			<td>author</td>
			<td>title</td>
			<td>write date</td>
		</tr>
		<c:choose>
			<c:when test="${empty articleList}">
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size:9pt;">no exist content</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${!empty articleList }">
				<c:forEach var="article" items="${articleList}" varStatus="articleNum">
					<tr align="center">
						<td width="5%">${articleNum.count}</td>
						<td width="10%">${article.id}</td>
						<td align ="left" width="35%">
							<span style="padding-right:30px"></span>
							<c:choose>
								<c:when test="${article.level > 1 }">
									<c:forEach begin="1" end="${article.level}" step="1">
										<span style="padding-left:20px"></span>
									</c:forEach>
									<span style="font-size:12px;">[answer]</span>
									<a class="cls1" href="${contextPath}/board/ViewArticle.do?
									articleNO=${article.articleNO}">${article.title }</a>
								</c:when>
								<c:otherwise>
									<a class="cls1" href="${contextPath}/board/ViewArticle.do?
										articleNO=${article.articleNO}">${article.title }</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td width="10%">
							<fmt:formatDate value="${article.writeDate}"/>
						</td>
					</tr>
				</c:forEach>
				<div class="text_center">
					<c:if test="${totArticle != null }">
						<c:choose>
							<c:when test="${totArticles > 100}">
								<c:forEach var="page" begin="1" end="10" step="1">
									<c:if test="${section >1 && page == 1 }">
										<a class="no-uline" href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=${(section-1)*10}">&nbsp; pre</a>
									</c:if>
									<a class="no-uline" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
									<c:if test="${page==10}">
										<a class="no-uline" href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=${section*10+1}">&nbsp; next</a>
									</c:if>
								</c:forEach>
								<c:when test="${totArticle==100}">
									<c:forEach var="page" begin="1" end="10" step="1">
										<a class="no-uline" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">page<</a>
									</c:forEach>
								</c:when>
								<c:when test="${totArticles<100}">
									<forEach var="page" begin="1" end="${totArticles/100 +1}" step="1">
										<a class="no-uline" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">page</a>
									</forEach>
								</c:when>
							</c:when>
						</c:choose>
					</c:if>
				</div>
			</c:when>
		</c:choose>
	</table>
	<a class="cls1" href="${contextPath}/board/articleForm.do">
		<p class="cls2">write content</p>
	</a>
</body>
</html>