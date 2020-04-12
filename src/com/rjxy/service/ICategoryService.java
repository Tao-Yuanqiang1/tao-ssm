package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Category;

public interface ICategoryService {

	//��̨����Ա��ѯȫ����Ʒ���
	public List<Category> list();
	
	//��̨����Ա�����Ʒ�������
	public void add(Category category);
	
	//������Ʒ����id��ѯ����Ʒ������Ϣ
	public Category load(int id);
	
	//������Ʒ����id�޸ĸ���Ʒ������Ϣ
	public void update(Category category);
	
	//������Ʒ����idɾ������Ʒ
	public void delete(int id);
}
