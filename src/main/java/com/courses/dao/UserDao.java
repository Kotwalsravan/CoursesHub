package com.courses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.courses.model.*;
public class UserDao {
	private Connection con;
	   private String query;
	   private PreparedStatement pst;
	   private ResultSet rs;

	public UserDao(Connection con) {
		// TODO Auto-generated constructor stub
		this.con=con;
	}
    public User userLogin(String name,String email,String password) {
    	User user=null;
    	try {
    		query="select * from users where email=? and password=?";
    		pst=this.con.prepareStatement(query);
    		pst.setString(1,email);
    		pst.setString(2, password);
    		rs=pst.executeQuery();
    		while(rs.next()) {
    			user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				
    		}
    		if(user==null) {
    			query="insert into users (name,email,password) values (?,?,?)";
                pst = this.con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
    		    pst.setString(1,name);
    		    pst.setString(2, email);
    		    pst.setString(3, password);
    		    int rowsAffected = pst.executeUpdate();
                if(rowsAffected>0) {
                	 rs = pst.getGeneratedKeys();
                     if (rs.next()) {
                         int generatedId = rs.getInt(1);
                         user = new User();
                         user.setId(generatedId);
                         user.setName(name);
                         user.setEmail(email);
                     }
                }
    		}
    	}
    catch(Exception e) {
    	e.printStackTrace();
    }
    	return user;
    }
}