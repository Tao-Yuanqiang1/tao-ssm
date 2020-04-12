package com.rjxy.service;

import com.rjxy.model.OrderProduct;

public interface IOrderProductService {

	//添加一个商品与订单的关系
	public void add(OrderProduct orderProduct);
}
