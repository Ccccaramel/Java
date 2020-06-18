<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="jQuery/jquery-3.3.1.js"></script>
<script type="text/javascript">
function go() {
	window.location.href="user.html";
}
</script>
</head>
<body>
this is 1.jsp!<br>
<%=request.getAttribute("list") %>
<button onclick="go()">user</button>
</body>
</html>