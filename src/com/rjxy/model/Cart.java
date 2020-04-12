package com.rjxy.model;

import java.sql.Timestamp;
import java.util.Date;

/*
 * ���ﳵ��  ��Ӧ���ݿ��� t_cart
 * id ����
 * product ��Ʒ
 * user �û�
 * quantity ��Ʒ����
 * date ����
 * 
 */
public class Cart {


	private int id;
	private int quantity;
	private Timestamp date;
	private Product product; //���һ
	private User user; //���һ

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
