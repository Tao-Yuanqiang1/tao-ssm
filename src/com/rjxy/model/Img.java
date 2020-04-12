package com.rjxy.model;

/*
 * 商品图片类  对应数据库表  t_img
 * id 主键
 * name 图片名称
 * product 对应的商品
 * 
 */
public class Img {

	private int id;
	private String name;
	private Product product; //多对一

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
