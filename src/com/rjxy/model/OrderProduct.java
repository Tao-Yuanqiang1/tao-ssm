package com.rjxy.model;

/*
 * 订单与商品的关系 t_order_product
 * id 主键
 * product 商品
 * order 订单
 * quantity 数量
 */
public class OrderProduct {

	private int id;
	private int quantity;
	private Product product; //多对一
	private Order order; //多对一

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
