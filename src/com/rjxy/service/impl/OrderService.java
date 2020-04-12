package com.rjxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.IOrderDao;
import com.rjxy.model.Order;
import com.rjxy.service.IOrderService;

@Service
public class OrderService implements IOrderService{

	private IOrderDao orderDao;
	
	@Resource
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	//����һ������
	public void add(Order order) {
		orderDao.add(order);
	}

	//��ѯһ������
	public Order load(int oid) {
		return orderDao.load(oid);
	}

	//��ѯһ����������ϸ����Ϣ��ͼƬ����Ʒ��
	public Order loadAllOne(int oid) {
		return orderDao.loadAllOne(oid);
	}

	//����һ��������״̬
	public void updateStatus(Order order) {
		orderDao.update(order);
	}

	//��ѯ��ͬ״̬�Ķ�����Ӧ�Ķ���
	public List<Order> listByStatus(Order order) {
		return orderDao.listByStatus(order);
	}

	//��ѯȫ������
	public List<Order> listByStatus0(int uid) {
		List<Order> orders = orderDao.listByStatus0(uid);
		return orders;
	}

	//��ѯ��������״̬������
	public int listOrderBySize(Order order) {
		int size = orderDao.listOrderBySize(order);
		return size;
	}

	
}
