package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Category;

public interface ICategoryService {

	//后台管理员查询全部商品类别
	public List<Category> list();
	
	//后台管理员添加商品类别属性
	public void add(Category category);
	
	//根据商品类别的id查询该商品类别的信息
	public Category load(int id);
	
	//根据商品类别的id修改该商品类别的信息
	public void update(Category category);
	
	//根据商品类别的id删除该商品
	public void delete(int id);
}
