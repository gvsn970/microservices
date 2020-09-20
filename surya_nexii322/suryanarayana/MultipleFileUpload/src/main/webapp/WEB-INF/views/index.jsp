<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Add New Product</h2>
	<s:form method="post" action="${pageContext.request.contextPath }/save"
		enctype="multipart/form-data" modelAttribute="product">
		<table>
			<tr>
				<td>Id</td>
				<td><s:input path="id" /></td>
			</tr>
			<tr>
				<td>name</td>
				<td><s:input path="name" /></td>
			</tr>
			<tr>
				<td>price</td>
				<td><s:input path="price" /></td>
			</tr>
			<tr>
				<td>Upload File</td>
				<td><input type="file" multiple="multiple" name="photos" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="save" /></td>
			</tr>
		</table>
	</s:form>
</body>
</html>