package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Order;

public interface IOrderDao extends IBaseDao<Order> {

	//查询一个订单的详细与信息（图片、商品）
	public Order loadAllOne(int oid);
	
	//查询不同状态的订单对应的订单
	public List<Order> listByStatus(Order order);
	
	//查询全部订单
	public List<Order> listByStatus0(int uid);
	
	//查询订单各个状态的数量
	public int listOrderBySize(Order order);
}
