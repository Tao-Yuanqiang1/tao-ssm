package com.rjxy.model;

import java.util.List;

/*
 * ��ַ�� ��Ӧ���ݿ���t_address
 * id ����
 * base ������ַ��ʡ����
 * place ��ϸ��ַ
 * name �ռ���
 * phone �ռ�����ϵ��ʽ
 * status ��ַ״̬
 * user �û�
 * 
 */
public class Address {

	private int id;
	private String base;
	private String place;
	private String name;
	private String phone;
	private int status;
	private User user; //���һ

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
