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

	//�����Ʒ
	public void add(Product product) {
		productDao.add(product);
	}

	//��ѯȫ������Ʒ
	public List<Product> list() {
		List<Product> products = productDao.list();
		return products;
	}

	//ɾ��һ����Ʒ
	public void delete(int id) {
		productDao.delete(id);
	}

	//������Ʒ��id��ѯ����Ʒ
	public Product load(int id) {
		Product product = productDao.load(id);
		return product;
	}

	//ǰ̨indexҳ���ѯ��Ʒ����ƷͼƬ
	public List<Product> listAllProducts() {
		List<Product> products = productDao.listAllProducts();
		return products;
	}

	//ǰ̨������Ʒ��id�鿴����Ʒ��ȫ����ϸ��Ϣ 
	public Product listProduct(int pid) {
		Product product = productDao.listProduct(pid);
		return product;
	}

	//�û�����ɹ��󣺸���һ����Ʒ �Ŀ�������
	public void updateSalesInventory(Product product) {
		productDao.update(product);
	}
	
	
	

}
