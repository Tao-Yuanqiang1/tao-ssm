package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Order;

public interface IOrderService {

	//����һ������
	public void add(Order order);
	
	//��ѯһ������
	public Order load(int oid);
	
	//��ѯһ����������ϸ����Ϣ��ͼƬ����Ʒ��
	public Order loadAllOne(int oid);
	
	//����һ��������״̬
	public void updateStatus(Order order);
	
	//��ѯ��ͬ״̬�Ķ�����Ӧ�Ķ���
	public List<Order> listByStatus(Order order);
	
	//��ѯȫ������
	public List<Order> listByStatus0(int uid);
	
	//��ѯ��������״̬������
	public int listOrderBySize(Order order);
}
