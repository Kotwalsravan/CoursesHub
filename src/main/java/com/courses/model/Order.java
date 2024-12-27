package com.courses.model;

public class Order extends Course{
    private int orderId;
    private int uid;
    private int quantity;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(int orderId, int uid, int quantity) {
		super();
		this.orderId = orderId;
		this.uid = uid;
		this.quantity = quantity;
		
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
