package com.courses.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courses.model.*;
import java.util.*;


@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		try {
		   	int id=Integer.parseInt(request.getParameter("id"));
			ArrayList<Cart> cartList=new ArrayList<Cart>();

		   	Cart cart=new Cart();
		   	cart.setId(id);
		   	cart.setQuantity(1);
		   	HttpSession session=request.getSession();
		   	  ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
		   	  if(cart_list==null) {
		   		  cartList.add(cart);
		   		session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
		   	  }else {
					cartList=cart_list;
					boolean exist=false;
					
					for(Cart c:cart_list) {
						if(c.getId()==id) {
							exist=true;
							out.println("<h3 style='color:crimson;text-align:center'>Item Already Exist in Cart<a href='cart.jsp'>Go to Cart Page</a></h3>");
						}
					}
					if(!exist) {
						cartList.add(cart);
						response.sendRedirect("index.jsp");
						
					}
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
