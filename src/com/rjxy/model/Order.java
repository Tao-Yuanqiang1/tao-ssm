package com.rjxy.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/*
 * 订单类  对应t_order
 * id 主键
 * price 价格
 * status 状态
 * isdel 是否被删除
 * date 日期
 * address 地址
 * user 用户
 */
public class Order {

	private int id;
	private double price;
	private int status;
	private int isdel;
	private Timestamp date;
	private Address address;
	private User user; //多对一
	private List<OrderProduct> orderProducts; //一对多
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProduct) {
		this.orderProducts = orderProduct;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
