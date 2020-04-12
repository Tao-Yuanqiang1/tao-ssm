package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Product;

public interface IProductService {

	//�����Ʒ
	public void add(Product product);
	
	//��ѯȫ������Ʒ
	public List<Product> list();
	
	//ɾ��һ����Ʒ
	public void delete(int id);
	
	//������Ʒ��id��ѯ����Ʒ
	public Product load(int id);
	
	//ǰ̨indexҳ���ѯ��Ʒ����ƷͼƬ
	public List<Product> listAllProducts();
	
	//ǰ̨������Ʒ��id�鿴����Ʒ��ȫ����ϸ��Ϣ 
	public Product listProduct(int pid);
	
	//�û�����ɹ��󣺸���һ����Ʒ �Ŀ�������
	public void updateSalesInventory(Product product);
}
