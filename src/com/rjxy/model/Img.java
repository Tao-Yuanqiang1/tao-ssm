package com.rjxy.model;

/*
 * ��ƷͼƬ��  ��Ӧ���ݿ��  t_img
 * id ����
 * name ͼƬ����
 * product ��Ӧ����Ʒ
 * 
 */
public class Img {

	private int id;
	private String name;
	private Product product; //���һ

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
