package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Product;

public interface IProductDao extends IBaseDao<Product> {

	//ǰ̨indexҳ���ѯ��Ʒ����ƷͼƬ
	public List<Product> listAllProducts();
	
	//ǰ̨������Ʒ��id�鿴����Ʒ��ȫ����ϸ��Ϣ 
	public Product listProduct(int pid);
	
}
