<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<%@include file="includes/Header.jsp" %>
</head>
<body>
<%@include file="includes/Navbar.jsp" %>
    
	<div class="container">

		<div class="card w-50 mx-auto my-5">
      <div class="card-header text-center ">Login to Enroll Courses</div>
      <div class="card-body">
        <form action="user-login" method="post">
        <div class="form-group">
            <label >Name</label>
            <input class="form-control" name="login-name" type="text" required placeholder="Enter Name"/>
          </div>
          <div class="form-group">
            <label >Email Address</label>
            <input class="form-control" name="login-email" type="email" required placeholder="Enter Email"/>
          </div>
          <div class="form-group">
            <label >Password</label>
            <input class="form-control" name="login-password" type="password" required placeholder="******"/>
          </div>
          <div class="text-center">
          <button type="submit" class="btn btn-primary">Login</button>
          </div>
        </form>
       </div>
     </div>
</div>
<% String errorMessage=request.getParameter("error"); %>
<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
    <div class="error-message">
        <%= errorMessage %>
    </div>
<% } %>
<%@include file="includes/Footer.jsp" %>
</body>
</html>