package com.courses.model;

public class Course {
   private int id;
   private String coursename;
   private double courseprice;
   private String courseimage;
   public Course() {
		
	}
	public Course(int id,String coursename,double courseprice,String courseimage) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.coursename=coursename;
		this.courseprice=courseprice;
		this.courseimage=courseimage;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public double getCourseprice() {
		return courseprice;
	}
	public void setCourseprice(double courseprice) {
		this.courseprice = courseprice;
	}
	public String getCourseimage() {
		return courseimage;
	}
	public void setCourseimage(String courseimage) {
		this.courseimage = courseimage;
	}
	

}
