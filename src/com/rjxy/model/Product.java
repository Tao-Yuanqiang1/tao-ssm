package com.rjxy.model;

import java.util.List;

/*
 * ��Ʒ�ࣺ ��Ӧ���ݿ�� t_product
 * id  ����
 * name  ��Ʒ����
 * price  ��Ʒ�۸�
 * sales  ��Ʒ����
 * inventory  ��Ʒ���
 * describe ��Ʒ������
 * discount ��Ʒ���ۿ�
 * status  ��Ʒ��״̬
 * category ������
 */
public class Product {

	private int id;
	private String name;
	private double price;
	private int sales;
	private int inventory;
	private String describe;
	private int discount;
	private int status;
	private Category category; //���һ
	private List<Img> imgs; //һ�Զ�
	
	public List<Img> getImgs() {
		return imgs;
	}

	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
