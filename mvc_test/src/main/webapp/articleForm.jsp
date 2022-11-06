<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored = "false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
 %>
 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$("#preview").attr('src',e.target.result);
			}
		    reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj){
		obj.action = "${contextPath}/board/listArticle.do";
		obj.submit();
	}
</script>
</head>
<body>
	<h1 style = "text-align:center">write new article</h1>
	<form name="articleForm" method="post" 
	action="${contextPath}/board/addArticle.do" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td align="right">title:</td>
				<td colspan="2">
					<input type="text" size="67" maxlength="500" name="title"/>
				</td>
			</tr>
			<tr>
				<td align="right" valign="top"> <br> content:</td>
				<td colspan="2">
					<textarea name="content" rows="10" cols="65" maxlength="4000" name="title"></textarea>
				</td>
			</tr>
			<tr>
				<td align = "right">attachment:</td>
				<td>
					<input type="file" name="imageFileName" onchange="readURL(this);"/>
				</td>
				<td>
					<img id="preview" src="#" width="200" height="200"/>
				</td>
			</tr>
			<tr>
				<td align ="right"></td>
				<td colspan="2">
					<input type = "submit" value="write"/>
					<input type = "button" value="list" onClick="backToList(this.form)"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>