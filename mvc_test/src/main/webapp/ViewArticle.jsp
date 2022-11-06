<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<head>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		function returnList(){
			location.href = "${contextPath}/board/listArticle.do";
		}
		
		function fn_enable(){
			document.getElementById("i_title").disabled=false;
			document.getElementById("i_content").disabled=false;
			document.getElementById("i_imageFileName").disabled = false;
			document.getElementById("tr_btn_modify").style.display = "block";
			document.getElementById("tr_btn").style.display="none";
		}
		
		function fn_modify_article(obj){
			obj.action="${contextPath}/board/ModifyArticle.do";
			obj.method = "post";
			obj.submit();
		}
		
		function readURL(input){
			if(input.files && input.files[0]){
				var reader = new FileReader()
				reader.onload = function(e){
					$("#preview").attr("src",e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			
			}
		}
		function fn_remove_article(url,articleNO){
			var form = document.createElement("form");
			form.setAttribute("method","post");
			form.setAttribute("action",url);
			var input = document.createElement("input");
			input.setAttribute("type","hidden");
			input.setAttribute("name","articleNO")
			input.setAttribute("value",articleNO);
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();
		}
		function fn_reply_form(url,parentNO){
			var form = document.createElement("form");
			form.setAttribute("method","post");
			form.setAttribute("action",url);
			var input = document.createElement("input");
			input.setAttribute("type","hidden");
			input.setAttribute("name","parentNO");
			input.setAttribute("value",parentNO);
			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();
		}
	</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form enctype="multipart/form-data">
	<table>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">ARITCLE NO</td>
			<td>
				<input type="text" value="${articleVO.articleNO}" disabled/>
				<input type="hidden" name="articleNO" value="${articleVO.articleNO }"/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">ID</td>
			<td>
				<input type="text" value="${articleVO.id}" name="id" id="i_id" disabled/>
			</td>
		</tr>		
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">TITLE</td>
			<td>
				<input type="text" value="${articleVO.title}" name="title" id="i_title" disabled/>
			</td>
		</tr>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">CONTENT</td>
			<td>
				<textArea rows="20" cols="60" name="content" id="i_content" disabled>
					${articleVO.content}
				</textArea>
			</td>
		</tr>
		<c:if test="${not empty articleVO.imageFileName && articleVO.imageFileName != 'null'}">
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">IMAGE</td>
			<td>
				<input type="hidden" name="originalFileName" value="${articleVO.imageFileName }"/>
				<img id="preview" src="${contextPath}/download.do?imageFileName=${articleVO.imageFileName}&articleNO=${articleVO.articleNO}"/>
				<br>
			</td>
		</tr>
		<tr>
			<td>
				<input type="file" name="imageFileNmae" id="i_imageFileName" disabled onchange="readURL(this);"/>
			</td>
		</tr>
		</c:if>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">WRITEDATE	</td>
			<td>
				<input type="text" name="writeDate" id="i_writeDate" value="${articleVO.writeDate}" disabled/>
			</td>
		</tr>
		<tr id="tr_btn_modify" style="display:none">
			<td width="30%">
			</td>
			<td width="30%">
				<input type="button" value="reflect modification" onClick="fn_modify_article(this.form)"/>
				<input type="button" value="cancle" onClick="returnList()"/>
			</td>
		</tr>
		<tr id="tr_btn">
			<td width="30%">
			</td>
			<td>
				<input type="button" value="MODIFY" onClick="fn_enable()"/>
				<input type="button" value="DELETE" onClick="fn_remove_article('${contextPath}/board/RemoveArticle.do',${articleVO.articleNO})"/>
				<input type="button" value="RETURN" onClick="returnList()"/>
				<input type="button" value="ANSER" onClick="fn_reply_form('${contextPath}/board/ReplyForm.do',${articleVO.articleNO})"/>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>