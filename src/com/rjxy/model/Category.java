package com.rjxy.model;

import java.util.List;

/*
 * ��Ʒ��� ��Ӧ���ݿ��t_category
 * id ����
 * name �������
 * describe ����
 * products һ������Ӧ��������Ʒ
 */
public class Category {

	private int id;
	private String name;
	private String describe;
	private List<Product> products; //һ�Զ�

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
