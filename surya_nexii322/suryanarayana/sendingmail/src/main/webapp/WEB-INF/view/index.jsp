<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resource/contact.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<center><h1 >Welcome Contact Form</h1></center>
  <div class="container">
  <form action="./sendMail" method="post">
    <div class="row">
      <div class="col-25">
        <label for="fname">To</label>
      </div>
      <div class="col-75">
        <input type="text" id="fname" name="to" placeholder="To Email address..">
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="lname">subject</label>
      </div>
      <div class="col-75">
        <input type="text" id="lname" name="subject" placeholder="subject..">
      </div>
    </div>
   
    </div>
    <div class="row">
      <div class="col-25">
        <label for="subject">Message</label>
      </div>
      <div class="col-75">
        <textarea id="subject" name="message" placeholder="Write something.." style="height:200px"></textarea>
      </div>
    </div>
    <div class="row">
      <input type="submit" value="Submit">
    </div>
  </form>
  <p style="color:green;font-size:20px">${msg}</p>
</div>

</body>
</html>