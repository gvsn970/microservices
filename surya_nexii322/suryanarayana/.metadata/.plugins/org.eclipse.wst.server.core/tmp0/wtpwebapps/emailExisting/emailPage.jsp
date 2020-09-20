<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
Connection con=null;  
try{  
    Class.forName("com.mysql.jdbc.Driver");  
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
    String email=request.getParameter("email");
    String query = "SELECT * FROM email WHERE email = ?";
    PreparedStatement ps=con.prepareStatement(query);  
     
    ResultSet rs=ps.executeQuery();  
    
}catch(Exception e)
{System.out.println(e);
}  
  
%>  
</body>
</html>