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

	//后台管理员：查询全部的商品类别
	public List<Category> list() {
		List<Category> categories = categoryDao.list();
		return categories;
	}

	//后台管理员添加商品类别属性
	public void add(Category category) {
		categoryDao.add(category);
	}

	//根据商品类别的id查询该商品类别的信息
	public Category load(int id) {
		Category category = categoryDao.load(id);
		return category;
	}

	//根据商品类别的id修改该商品类别的信息
	public void update(Category category) {
		categoryDao.update(category);
	}

	//根据商品类别的id删除该商品
	public void delete(int id) {
		categoryDao.delete(id);
	}

}
