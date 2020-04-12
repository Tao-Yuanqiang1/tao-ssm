package com.rjxy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.IProductDao;
import com.rjxy.model.Product;

@Repository
public class ProductDao extends BaseDao<Product> implements IProductDao {

	//ǰ̨indexҳ���ѯ��Ʒ����ƷͼƬ
	public List<Product> listAllProducts() {
		return openSession().selectList(Product.class.getName()+ ".listAllProducts");
	}

	//ǰ̨������Ʒ��id�鿴����Ʒ��ȫ����ϸ��Ϣ 
	public Product listProduct(int pid) {
		return openSession().selectOne(Product.class.getName()+ ".listProduct", pid);
	}

}
