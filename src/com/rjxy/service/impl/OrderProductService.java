package com.rjxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.IOrderProductDao;
import com.rjxy.model.OrderProduct;
import com.rjxy.service.IOrderProductService;

@Service
public class OrderProductService implements IOrderProductService {

	private IOrderProductDao orderProductDao;
	
	@Resource
	public void setOrderProductDao(IOrderProductDao orderProductDao) {
		this.orderProductDao = orderProductDao;
	}

	//添加一个商品和订单的关系
	public void add(OrderProduct orderProduct) {
		orderProductDao.add(orderProduct);
	}

}
