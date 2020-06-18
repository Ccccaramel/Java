<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="jQuery/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(function() {
	$.ajax({
	    type: 'POST',
	    url: "${pageContext.request.contextPath}/UC",
	    dataType: 'json',
	    success: function (json) {
	        console.log(json);
	    },
	    error: function () {
	        alert("数据加载失败");
	    }
	});
});

</script>
</head>
123

<body>
</body>
</html>