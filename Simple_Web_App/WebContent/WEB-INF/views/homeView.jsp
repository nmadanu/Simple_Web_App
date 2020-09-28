<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3>Home Page</h3>
	
	WEB Application using jsp, servlet and JDBC <br><br>
	<b>Functionalities</b>
	<ul>
	<li>Login</li>
	<li>Store User info in Cookies</li>
	<li>Product List</li>
	<li>Create a Product</li>
	<li>Edit a Product</li>
	<li>Delete a Product</li>
	</ul>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>