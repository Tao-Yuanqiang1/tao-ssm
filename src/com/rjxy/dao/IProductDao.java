package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Product;

public interface IProductDao extends IBaseDao<Product> {

	//前台index页面查询商品、商品图片
	public List<Product> listAllProducts();
	
	//前台根据商品的id查看该商品的全部详细信息 
	public Product listProduct(int pid);
	
}
