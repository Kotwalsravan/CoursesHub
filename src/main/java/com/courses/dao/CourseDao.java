package com.courses.dao;
import java.util.*;


import com.courses.model.*;
import com.courses.model.*;

import java.sql.*;
public class CourseDao {
   private Connection con=null;
  private String query;
  private PreparedStatement pst;
  private ResultSet rs;
	public CourseDao(Connection con) {
    this.con=con;
	}
	public List<Course> getAllCourses(){
		List<Course> courses=new ArrayList<Course>();
		 try {
			 query="select * from courselist";
			 pst=this.con.prepareStatement(query);
			 rs=pst.executeQuery();
			 while(rs.next()) {
				  Course row = new Course();
		            row.setId(rs.getInt("id"));
		            row.setCoursename(rs.getString("coursename"));
		            row.setCourseprice(rs.getDouble("courseprice")); 
		            row.setCourseimage(rs.getString("courseimage"));
		            courses.add(row);
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return courses;
	}
	 public List<Cart> getCartCourses(ArrayList<Cart> cartList){
		   List<Cart> courses=new ArrayList<Cart>();
		   try {
			   if(cartList.size()>0) {
				   for(Cart item:cartList) {
					   query="select * from courselist where id=?";
					   pst=this.con.prepareStatement(query);
					   pst.setInt(1,item.getId());
					   rs=pst.executeQuery();
					   while(rs.next()) {
						   Cart row=new Cart();
						   row.setId(rs.getInt("id"));
						   row.setCoursename(rs.getString("coursename"));
						   row.setCourseprice(rs.getDouble("courseprice")*item.getQuantity());
						   row.setCourseimage(rs.getString("courseimage"));
						   row.setQuantity(item.getQuantity());
						   courses.add(row);
						   
					   }
				   }
			   }
		   }catch(Exception e) {
			   e.printStackTrace();	   }
		   return courses;
	   }
	 public double getTotalCartPrice(ArrayList<Cart> cartList){
		 double sum=0;
		 try {
			 if(cartList.size()>0) {
				 for(Cart c:cartList) {
					 query="select courseprice from courselist where id=?";
					 pst=this.con.prepareStatement(query);
					 pst.setInt(1, c.getId());
					 rs=pst.executeQuery();
					 while(rs.next()) {
					   sum+=rs.getDouble("courseprice")*c.getQuantity();	 
					 }
					 }
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return sum;
	 }
	 public Course getSingleCourse(int id) {
			// TODO Auto-generated method stub
			Course row=null;
			try {
				query="select * from courselist where id=?";
				pst=this.con.prepareStatement(query);
				pst.setInt(1, id);
				rs=pst.executeQuery();
				while(rs.next()) {
					row=new Course();
					row.setId(rs.getInt("id"));
					row.setCoursename(rs.getString("coursename"));
					row.setCourseprice(rs.getDouble("courseprice"));
					row.setCourseimage(rs.getString("courseimage"));
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return row;
		}
}
