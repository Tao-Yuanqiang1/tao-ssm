package com.rjxy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.IOrderDao;
import com.rjxy.model.Order;

@Repository
public class OrderDao extends BaseDao<Order> implements IOrderDao {

	//查询一个订单的详细与信息（图片、商品）
	public Order loadAllOne(int oid) {
		return openSession().selectOne(Order.class.getName()+ ".loadAllOne", oid);
	}

	//查询不同状态的订单对应的订单
	public List<Order> listByStatus(Order order) {
		return openSession().selectList(Order.class.getName()+ ".listByStatus", order);
	}

	//查询全部订单
	public List<Order> listByStatus0(int uid) {
		return openSession().selectList(Order.class.getName()+ ".listByStatus0", uid);
	}

	//查询订单各个状态的数量
	public int listOrderBySize(Order order) {
		return openSession().selectOne(Order.class.getName()+ ".listOrderBySize", order);
	}

}
