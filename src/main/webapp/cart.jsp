<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.courses.connection.*,com.courses.model.*,com.courses.dao.*" %>
    <%@ page import="java.util.*" %>
    <%
     User auth=(User) request.getSession().getAttribute("auth");
      if(auth!=null){
    		request.setAttribute("auth", auth);

      }
      ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
      List<Cart> cartProduct=null;
      if(cart_list!=null){
      	CourseDao cDao=new CourseDao(DbCon.getConnection());
      	cartProduct=cDao.getCartCourses(cart_list);
      	    double total=cDao.getTotalCartPrice(cart_list);
      	request.setAttribute("cart_list",cart_list);
      	request.setAttribute("total",total);
      }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Courses Cart List</title>
<%@ include file="includes/Header.jsp" %>
<style type="text/css">
.table tbody td{
vartical-align:middle;
}
.btn-incre,.btn-decre{
box-shadow:none;
font-size:25px;
}
.total{
 color:crimson;
}
.total-price{
 color:skyblue;
 size:20px;
}

</style>
</head>
<body>
<%@include file="includes/Navbar.jsp" %>
<div class="container">
		<div class="d-flex py-3">
			<h3 class="total">Total Price:<span class="total-price">${ (total>0)?total:0 }</span></h3>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col ">Name</th>
					<th scope="col ">Price</th>
					<th scope="col ">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<% 
			if(cart_list!=null){
				for(Cart c:cartProduct){%>
					<tr>
					<td><%= c.getCoursename() %></td>
					
					<td><%= c.getCourseprice() %></td>
					<td>
						<form method="post" class="form-inline" action="order-now">
							<input type="hidden" name="id" value="<%= c.getId() %>" class="from-input">
							<div class="form-group d-flex justify-content-between w-50">
            <a href="quantity-inc-dec?action=dec&id=<%= c.getId() %>" class="btn btn-sm btn-decre"><i class="fas fa-minus-square"></i></a>
            <input typ="text" name="quantity" class="form-control " value="<%= c.getQuantity() %>" readonly/>
            <a href="quantity-inc-dec?action=inc&id=<%= c.getId() %>" class="btn btn-sm btn-incre"><i class="fas fa-plus-square"></i></a>
                         </div>
                         <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                         </form>
                </td><td><a href="remove-from-cart?id=<%= c.getId() %>" class="btn btn-sm btn-danger">Cancel</a></td>
             </tr>
				<% }
				
			}%>
				
       </tbody>
      
    </table>
 </div>
 <%@include file="includes/Footer.jsp" %>
 
</body>
</html>