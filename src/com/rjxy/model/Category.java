package com.rjxy.model;

import java.util.List;

/*
 * 商品类别： 对应数据库表t_category
 * id 主键
 * name 类别名称
 * describe 描述
 * products 一个类别对应的所有商品
 */
public class Category {

	private int id;
	private String name;
	private String describe;
	private List<Product> products; //一对多

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}


}
