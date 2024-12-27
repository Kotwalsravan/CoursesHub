<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.courses.connection.*,com.courses.dao.*,com.courses.model.*" %>
      <% User auth=(User) request.getSession().getAttribute("auth");
    List<Order> orders=null;
       if(auth!=null){
       request.setAttribute("auth",auth);
        orders=new OrderDao(DbCon.getConnection()).userOrders(auth.getId());
       
       }
       else{
    	 //  response.sendRedirect("login.jsp");
       }
        ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
     if(cart_list!=null){
    	 request.setAttribute("cart_list",cart_list);
     }%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders Page</title>

<%@include file="includes/Header.jsp" %>
</head>
<body>
<%@include file="includes/Navbar.jsp" %>
<div class="container">
<div class="card-header my-3">All Orders</div>
	<table class="table table-light">
			<thead>
				<tr>
				
					<th scope="col ">Name</th>
					<th scope="col ">Price</th>
					<th scope="col ">Quantity</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
			 if(orders!=null){
		       for(Order o:orders){
		    	   %>
		    	   <tr>
		    	   <td><%= o.getCoursename() %></td>
		    	   		    	   <td><%= o.getCourseprice() %></td>
		    	   		    	   <td><%= o.getQuantity() %></td>
		    	   		  <td><a href="cancel-order?id=<%= o.getId() %>" class="btn btn-sm btn-danger">Cancel</a></td>   	   
		    	   
		    </tr>   <% }
			 }
			%>
      </tbody>
      </table>
</div>
<%@include file="includes/Footer.jsp" %>
</body>
</html>