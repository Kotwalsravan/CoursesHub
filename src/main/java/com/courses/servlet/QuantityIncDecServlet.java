package com.courses.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.courses.model.*;
import com.courses.model.Cart;
/**
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try {
			String action=request.getParameter("action");
			int id=Integer.parseInt(request.getParameter("id"));
			ArrayList<Cart> cart_list=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			if(action!=null&id>=1) {
				if(action.equalsIgnoreCase("inc")) {
					for(Cart c:cart_list) {
						if(c.getId()==id) {
							int quantity=c.getQuantity();
							quantity+=1;
							 c.setQuantity(quantity);
							   response.sendRedirect("cart.jsp");
						}
					}	
				}
				  if(action.equalsIgnoreCase("dec")) {
					   for(Cart c:cart_list) {
						   if(c.getId()==id&&c.getQuantity()>1) {
							   int quantity=c.getQuantity();
							   quantity--;
							   c.setQuantity(quantity);
							   break;
						   }
					   }
					   response.sendRedirect("cart.jsp");
				   }
				   else {
					   response.sendRedirect("cart.jsp");
				   }
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	

}
