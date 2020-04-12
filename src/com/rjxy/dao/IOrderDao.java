package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Order;

public interface IOrderDao extends IBaseDao<Order> {

	//��ѯһ����������ϸ����Ϣ��ͼƬ����Ʒ��
	public Order loadAllOne(int oid);
	
	//��ѯ��ͬ״̬�Ķ�����Ӧ�Ķ���
	public List<Order> listByStatus(Order order);
	
	//��ѯȫ������
	public List<Order> listByStatus0(int uid);
	
	//��ѯ��������״̬������
	public int listOrderBySize(Order order);
}
