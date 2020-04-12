package com.rjxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.IImgDao;
import com.rjxy.model.Img;
import com.rjxy.service.IImgService;

@Service
public class ImgService implements IImgService {

	public IImgDao imgDao;
	
	@Resource
	public void setImgDao(IImgDao imgDao) {
		this.imgDao = imgDao;
	}

	//添加图片
	public void add(Img img) {
		imgDao.add(img);
	}

	//根据商品的id删除图片
	public void delete(int pid) {
		imgDao.delete(pid);
	}

}
