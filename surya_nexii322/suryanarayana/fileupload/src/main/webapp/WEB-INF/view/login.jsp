<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login-Form</title>

 <jsp:include page="header/header_links.jsp" />
 
</head>
<body>
<jsp:include page="header/navbar.jsp" />
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h3>Emp-Form</h3>
			<form name="form" method="post" action="login-user" >


				<div class="control-group form-group">
					<div class="controls">
						<label>Enter Employee name:</label> <input type="text" class="form-control"
							id="username" name="username">
					</div>
				</div>
			
				<div class="control-group form-group">
					<div class="controls">
						<label>Password:</label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
				</div>


				<!-- For success/fail messages -->
				<button type="submit" class="btn btn-primary" id="sendMessageButton">Login</button>
			</form><br/>
			
		</div>
	

	</div>

</body>
</html>