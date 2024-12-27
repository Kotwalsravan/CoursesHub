package com.courses.servlet;

import java.util.ArrayList;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.connection.*;
import com.courses.dao.*;
import com.courses.model.*;
import com.courses.servlet.*;


import java.io.*;


/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			User auth=(User)request.getSession().getAttribute("auth");
			if(auth!=null) {
				String productId=request.getParameter("id");
				int productQuantity=Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity<=0) {
					productQuantity=1;
					
				}
				Order orderModel=new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setQuantity(productQuantity);
				orderModel.setUid(auth.getId());
				OrderDao orderDao=new OrderDao(DbCon.getConnection());
				boolean result=orderDao.insertOrder(orderModel);
				if(result) {
					ArrayList<Cart> cart_list=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
					if(cart_list!=null) {
						for(Cart c:cart_list) {
							if(c.getId()==Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
				}
				else {
					out.println("order Failed");
				}
		  
			}
		}
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
