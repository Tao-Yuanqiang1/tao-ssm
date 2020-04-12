package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Order;

public interface IOrderService {

	//保存一个订单
	public void add(Order order);
	
	//查询一个订单
	public Order load(int oid);
	
	//查询一个订单的详细与信息（图片、商品）
	public Order loadAllOne(int oid);
	
	//更新一个订单的状态
	public void updateStatus(Order order);
	
	//查询不同状态的订单对应的订单
	public List<Order> listByStatus(Order order);
	
	//查询全部订单
	public List<Order> listByStatus0(int uid);
	
	//查询订单各个状态的数量
	public int listOrderBySize(Order order);
}
