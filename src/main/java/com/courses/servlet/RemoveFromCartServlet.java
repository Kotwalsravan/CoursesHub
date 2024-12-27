package com.courses.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.model.Cart;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		try {
		 int id=Integer.parseInt(request.getParameter("id"));
		 
		 if(id>0) {
			 ArrayList<Cart> cart_list=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			 if(cart_list!=null) {
				 for(Cart c:cart_list) {
					 if(c.getId()==id) {
						 cart_list.remove(cart_list.indexOf(c));
						 break;
					 }
				 }
				 response.sendRedirect("cart.jsp");
			 }
		 }else {
			 response.sendRedirect("cart.jsp");
		 }
		 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
