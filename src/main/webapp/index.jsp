<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.courses.model.*" %>
    <%@ page import="com.courses.dao.*" %>
    <%@ page import="com.courses.connection.*,java.util.*" %>
    <%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
     CourseDao pd=new CourseDao(DbCon.getConnection());
     List<Course> courses=pd.getAllCourses();
     ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
     if(cart_list!=null){
    	 request.setAttribute("cart_list",cart_list);
     }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Page</title>
<%@ include file="includes/Header.jsp" %>
<style>
.card {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
}

.card-img-top {
    height: 200px;
    object-fit: cover; 
}

.card-body {
    flex-grow: 1;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}
</style>
</head>
<body>
<%@ include file="includes/Navbar.jsp" %>

 <div class="container">
    <div class="card-header my-3">Courses List</div>
    <div class="row">
      <%
      if(!courses.isEmpty()){
		  for(Course c:courses){%>
             <div class="col-md-3 my-3">
               <div class="card w-100 " style="width:18rems;">
                 <img src="coursesImages/<%= c.getCourseimage() %>" alt="Card image cap" class="card-img-top">
                 <div class="card-body">
                   <h5 class="card-title text-center"><%= c.getCoursename() %></h5>
                   <h4 class="price">Price:<%= c.getCourseprice()%></h4>
                   
                    <div class="mt-3 d-flex justify-content-between">
                        <% if (auth != null) { %>
                        <a href="add-to-cart?id=<%= c.getId() %>" class="btn btn-dark">Add to Cart</a>
                        <a href="order-now?quantity=1&id=<%= c.getId() %>" class="btn btn-primary">Buy Now</a>
                        <% } else { %>
                        <h6 class="text-center">Please <span style="color:red">Login</span> to purchase this course.</h6>
                        <% } %>
                    </div>
                 </div>
                </div>
                </div>
		  <%}
	  }
      %>
    </div>
    
  
 
 </div>
 

    

<%@ include file="includes/Footer.jsp" %>
</body>
</html>