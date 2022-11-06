<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkJson").click(function(){
			var _jsonInfo = '{"name":"park","age":"25","gender":"man","nickName":"nick"}';
			$.ajax({
				type:"post",
				async:false,
				data:{jsonInfo: _jsonInfo},
				url:"http://localhost:8081/ajax/jsonServlet",
				success:function(data,textStatus){},
				error:function(data,textStatus){alert("error occure");}
			});
		});
	});
</script>
</head>
<body>
<input type="button" id="checkJson" value="check"/>
</body>
</html>