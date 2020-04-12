package com.rjxy.model;

import java.sql.Timestamp;
import java.util.Date;

/*
 * 购物车类  对应数据库表的 t_cart
 * id 主键
 * product 商品
 * user 用户
 * quantity 商品数量
 * date 日期
 * 
 */
public class Cart {


	private int id;
	private int quantity;
	private Timestamp date;
	private Product product; //多对一
	private User user; //多对一

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
