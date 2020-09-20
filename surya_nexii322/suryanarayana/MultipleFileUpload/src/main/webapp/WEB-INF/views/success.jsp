<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Add New Product</h2>

	<table border="1">
		<tr>
			<td>Id</td>
			<td>${product.id }</td>
		</tr>
		<tr>
			<td>name</td>
			<td>${product.name }</td>
		</tr>
		<tr>
			<td>price</td>
			<td>${product.price }</td>
		</tr>
		<tr>
			<td>Upload File</td>
			<td><c:forEach var="photo" items="${product.photos}">
			<img src="${pageContext.request.contextPath }/resources/images/${photo}" width="50px"/>
				</c:forEach></td>
		</tr>

	</table>

</body>
</html>