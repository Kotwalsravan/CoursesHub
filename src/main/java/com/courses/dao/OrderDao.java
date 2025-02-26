package com.courses.dao;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import com.courses.model.*;
import com.courses.dao.*;

import java.io.*;
public class OrderDao {
   private Connection con;
   private String query;
   private PreparedStatement pst;
   private ResultSet rs;
	public OrderDao() {
		// TODO Auto-generated constructor stub
	}
	public OrderDao(Connection con) {
		this.con=con;
	}
	public boolean insertOrder(Order model) {
		boolean result=false;
		try {
			query="insert into orders(p_id,u_id,o_quantity) values(?,?,?)";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2,model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.executeUpdate();
			result=true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	public List<Order> userOrders(int id){
		List<Order> list=new ArrayList<>();
		try {
			query="select * from orders where u_id=? order by orders.o_id desc";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				CourseDao courseDao=new CourseDao(this.con);
				int cId=rs.getInt("p_id");
				Course course=courseDao.getSingleCourse(cId);
				order.setOrderId(rs.getInt("o_id"));
				order.setId(cId);
				order.setCoursename(course.getCoursename());
				order.setCourseprice(course.getCourseprice()*rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				list.add(order);
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return list;
	}
	public void cancelOrder(int id) {
		try {
			query="delete from orders where p_id=?";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
