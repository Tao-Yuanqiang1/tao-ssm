package com.rjxy.model;

/*
 * ��������Ʒ�Ĺ�ϵ t_order_product
 * id ����
 * product ��Ʒ
 * order ����
 * quantity ����
 */
public class OrderProduct {

	private int id;
	private int quantity;
	private Product product; //���һ
	private Order order; //���һ

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
