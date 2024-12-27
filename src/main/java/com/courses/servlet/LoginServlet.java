package com.courses.servlet;
import com.courses.dao.*;
import com.courses.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courses.connection.DbCon;

import java.io.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("login.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try {
			String name=request.getParameter("login-name");
			String email=request.getParameter("login-email");
			String password=request.getParameter("login-password");
			UserDao udao=new UserDao(DbCon.getConnection());
			User user=udao.userLogin(name,email, password);
			if(user!=null) {
				request.getSession().setAttribute("auth",user);
		    	response.sendRedirect("index.jsp");
			}
			else {
                response.sendRedirect("login.jsp?error=Invalid login or registration failed.");
            }
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}

}