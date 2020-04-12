package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Product;

public interface IProductService {

	//添加商品
	public void add(Product product);
	
	//查询全部的商品
	public List<Product> list();
	
	//删除一件商品
	public void delete(int id);
	
	//根据商品的id查询该商品
	public Product load(int id);
	
	//前台index页面查询商品、商品图片
	public List<Product> listAllProducts();
	
	//前台根据商品的id查看该商品的全部详细信息 
	public Product listProduct(int pid);
	
	//用户付款成功后：更新一个商品 的库存和销量
	public void updateSalesInventory(Product product);
}
