package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Cart;

public interface ICartService {

	//根据用户id查询其在购物车中的商品数量
	public int loadByUserSize(int uid);
	
	//添加商品到购物车
	public void add(Cart cart);
	
	//根据用户的id查询该用户在购物车中的全部商品信息
	public List<Cart> listByUser(int uid);
	
	//修改商品的数量
	public void updateQuantity(Cart cart);
	
	//查询购物车中的一件商品
	public Cart load(int cid);
	
	//删除一个购物车
	public void delete(int cid);
	
	//商品结算页面：查询该一件商品的图片、等等
	public Cart loadByBuy(int cid);

}
