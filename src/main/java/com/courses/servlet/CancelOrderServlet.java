package com.courses.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.connection.DbCon;
import com.courses.dao.OrderDao;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");
	    try {
	    	String id=request.getParameter("id");
	    	System.out.println(id);
	    	if(id!=null) {
	    		OrderDao orderDao=new OrderDao(DbCon.getConnection());
	    		orderDao.cancelOrder(Integer.parseInt(id));
	    		
	    	}
			response.sendRedirect("orders.jsp");

	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
