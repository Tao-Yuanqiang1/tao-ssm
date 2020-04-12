package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Cart;

public interface ICartDao extends IBaseDao<Cart> {
	
	//根据用户id查询其在购物车中的商品数量
	public int loadByUserSize(int uid);

	//根据用户的id查询该用户在购物车中的全部商品信息
	public List<Cart> listByUser(int uid);
	
	//根据商品的id和用户的id查看购物车中有没有相同的商品
	public Cart listByProduct(Cart cart);
	
	//商品结算页面：查询该一件商品的图片、等等
	public Cart loadByBuy(int cid);
}
