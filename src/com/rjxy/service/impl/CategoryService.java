package com.rjxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.ICategoryDao;
import com.rjxy.model.Category;
import com.rjxy.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	private ICategoryDao categoryDao;
	
	@Resource
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//��̨����Ա����ѯȫ������Ʒ���
	public List<Category> list() {
		List<Category> categories = categoryDao.list();
		return categories;
	}

	//��̨����Ա�����Ʒ�������
	public void add(Category category) {
		categoryDao.add(category);
	}

	//������Ʒ����id��ѯ����Ʒ������Ϣ
	public Category load(int id) {
		Category category = categoryDao.load(id);
		return category;
	}

	//������Ʒ����id�޸ĸ���Ʒ������Ϣ
	public void update(Category category) {
		categoryDao.update(category);
	}

	//������Ʒ����idɾ������Ʒ
	public void delete(int id) {
		categoryDao.delete(id);
	}

}
