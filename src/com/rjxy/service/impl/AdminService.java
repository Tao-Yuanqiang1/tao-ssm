package com.rjxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.IAdminDao;
import com.rjxy.exception.ShoppingException;
import com.rjxy.model.Admin;
import com.rjxy.service.IAdminService;
import com.rjxy.util.CodeUtil;

@Service
public class AdminService implements IAdminService {

	private IAdminDao adminDao;
	
	@Resource
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	//管理员登录验证密码
	//其参数是从 表单页面 传递过来的
	public Admin login(String username, String password) {
		//通过用户名从数据库中查找该管理员信息
		Admin admin = adminDao.login(username);
		
		if(admin == null) {
			throw new ShoppingException("用户名不存在");
		}
		if(!admin.getPassword().equals(CodeUtil.getMD5Encoding(password))) {
			//密码错误
			throw new ShoppingException("密码错误");
		}
		return admin;
	}

}
