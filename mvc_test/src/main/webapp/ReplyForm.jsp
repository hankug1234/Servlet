<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
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
				reader.onload = function (e){
					$("#preview").attr("src",e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		
		function returnView(){
			history.go(-1);
		}
	</script>
</head>
<body>
	<h1 style="text-aling:center">WRITE ANSWER</h1>
	<form name="frmReply" method="post" action="${contextPath}/board/AddReply.do" enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td align="right">author:&nbsp</td>
				<td><input type="text" size="5" value="kime" disabled/></td>
			</tr>
			<tr>
				<td align="right">title:&nbsp</td>
				<td><input type="text" size="67" maxlength="100" name="title"/></td>
			</tr>
			<tr>
				<td align="right" valign="top">content:&nbsp</td>
				<td><textarea rows="10" cols="65" maxlength="4000" name="content"></textarea></td>
			</tr>
			<tr>
				<td align="right">image file attach:</td>
				<td><input type="file" name="imageFileName" onchange="readURL(this)"/></td>
			</tr>
			<tr>
				<td><img id="preview" src="#" width=200 height=200/></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td>
					<input type="submit" value="submit"/>
					<input type="button" value="cancle" onClick="returnView()"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>