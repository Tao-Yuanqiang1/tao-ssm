package com.rjxy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.IProductDao;
import com.rjxy.model.Product;

@Repository
public class ProductDao extends BaseDao<Product> implements IProductDao {

	//前台index页面查询商品、商品图片
	public List<Product> listAllProducts() {
		return openSession().selectList(Product.class.getName()+ ".listAllProducts");
	}

	//前台根据商品的id查看该商品的全部详细信息 
	public Product listProduct(int pid) {
		return openSession().selectOne(Product.class.getName()+ ".listProduct", pid);
	}

}
