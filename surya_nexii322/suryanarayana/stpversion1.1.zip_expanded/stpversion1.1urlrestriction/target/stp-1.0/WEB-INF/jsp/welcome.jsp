<%@ page language="java" contentType="text/html;charset=UTF-8" session="false"%>
<%
HttpSession userSession = request.getSession(false);
System.out.print("before session check");
if(userSession != null ){
	System.out.print("session is not null");
%>
<html>
<head>
<title>Welcome page</title>
</head>
<body>
<table align="center">

<tr>
<td colspan=2><center><font size=4><b>welcome</b></font></center></td>
<td><input type="submit" onclick="logout();"  value="Logout"></td>
</tr>

<td>Welcome</td>
<td><h1 name="welcome"></h1>
</tr>
<tr>


</table>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
<script type="text/javascript" src="js/changeForgotLoginFunctions.js"></script>
<script type="text/javascript" src="js/urlgetter.js"></script>


</body>
</html>
<%
}else {
	System.out.print("session is null");
%>
<jsp:forward page="login.jsp"></jsp:forward>   
<%
} 
%>