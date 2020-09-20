<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Emp-Form</title>

 <jsp:include page="header/header_links.jsp" />
 
</head>
<body>
<jsp:include page="header/navbar.jsp" />
<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h2>Welcome to EmpLyoee </h2>
				</div>
			</div>
			<c:if test="${not empty sucessMsg }">
				<div class="alert alert-success fade in">
					<c:out value="${sucessMsg }"></c:out>
				</div>
			</c:if>

</body>
</html>