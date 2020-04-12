package com.rjxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.IProductDao;
import com.rjxy.model.Product;
import com.rjxy.service.IProductService;

@Service
public class ProductService implements IProductService {

	private IProductDao productDao;
	
	@Resource
	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	//添加商品
	public void add(Product product) {
		productDao.add(product);
	}

	//查询全部的商品
	public List<Product> list() {
		List<Product> products = productDao.list();
		return products;
	}

	//删除一件商品
	public void delete(int id) {
		productDao.delete(id);
	}

	//根据商品的id查询该商品
	public Product load(int id) {
		Product product = productDao.load(id);
		return product;
	}

	//前台index页面查询商品、商品图片
	public List<Product> listAllProducts() {
		List<Product> products = productDao.listAllProducts();
		return products;
	}

	//前台根据商品的id查看该商品的全部详细信息 
	public Product listProduct(int pid) {
		Product product = productDao.listProduct(pid);
		return product;
	}

	//用户付款成功后：更新一个商品 的库存和销量
	public void updateSalesInventory(Product product) {
		productDao.update(product);
	}
	
	
	

}
