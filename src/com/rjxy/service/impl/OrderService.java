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
	
	//保存一个订单
	public void add(Order order) {
		orderDao.add(order);
	}

	//查询一个订单
	public Order load(int oid) {
		return orderDao.load(oid);
	}

	//查询一个订单的详细与信息（图片、商品）
	public Order loadAllOne(int oid) {
		return orderDao.loadAllOne(oid);
	}

	//更新一个订单的状态
	public void updateStatus(Order order) {
		orderDao.update(order);
	}

	//查询不同状态的订单对应的订单
	public List<Order> listByStatus(Order order) {
		return orderDao.listByStatus(order);
	}

	//查询全部订单
	public List<Order> listByStatus0(int uid) {
		List<Order> orders = orderDao.listByStatus0(uid);
		return orders;
	}

	//查询订单各个状态的数量
	public int listOrderBySize(Order order) {
		int size = orderDao.listOrderBySize(order);
		return size;
	}

	
}
