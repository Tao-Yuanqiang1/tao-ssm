package com.rjxy.service;

import com.rjxy.model.Img;

public interface IImgService {

	//保存图片
	public void add(Img img);
	
	//根据商品的id删除图片
	public void delete(int pid);
	
}
