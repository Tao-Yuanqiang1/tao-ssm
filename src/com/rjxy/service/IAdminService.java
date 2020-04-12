package com.rjxy.service;

import com.rjxy.model.Admin;

public interface IAdminService {

	//管理员登录操作
	public Admin login(String username, String password); 
}
