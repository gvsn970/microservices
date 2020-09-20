<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registarion Form</title>
 <jsp:include page="header/header_links.jsp" />
</head>
<body>
<jsp:include page="header/navbar.jsp" />
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h3>Emp-Form</h3>
			<form name="form" method="post" action="reg-emp" enctype="multipart/form-data" >


				<div class="control-group form-group">
					<div class="controls">
						<label>Enter Employee name:</label> <input type="text" class="form-control"
							id="username" name="username">
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>Enter Employee email:</label> <input type="email" class="form-control"
							id="email" name="email">
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>Enter Employee Date Of Birth:</label> <input type="date" class="form-control"
							id="date" name="dob">
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label for="upload-photo">Upload Employee photo:</label>
						 <input type="file" name="photo" id="upload-photo" class="form-control">
					</div>
				</div>
				<div class="control-group form-group">
					<div class="controls">
						<label>Password:</label> <input type="password"
							class="form-control" id="password" name="password">
					</div>
				</div>


				<!-- For success/fail messages -->
				<button type="submit" class="btn btn-primary" id="sendMessageButton">Registar</button>
			</form><br/>
			
		</div>
	

	</div>
</body>
</html>