<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
 
</head>
<body>
<h2>Hello World!</h2>
<form  action="./emailForm" method="post">
Enter email id:<input type="text" id="email" name="email"/><br/>
<span id="availability"></span>
<input type="submit" class="btn btn-primary" id="register" disabled value="submit"/>
</form>
</body>
</html>
<script>  
 $(document).ready(function(){  
   $('#email').blur(function(){

     var email = $(this).val();

     $.ajax({
      url:'emailPage.jsp',
      method:"POST",
      data:{email:email},
      success:function(data)
      {
       if(data != '0')
       {
        $('#availability').html('<span class="text-danger">Username not available</span>');
        $('#register').attr("disabled", true);
       }
       else
       {
        $('#availability').html('<span class="text-success">Username Available</span>');
        $('#register').attr("disabled", false);
       }
      }
     })

  });
 });  
</script>
