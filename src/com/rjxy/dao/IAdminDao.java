package com.rjxy.dao;

import com.rjxy.model.Admin;

public interface IAdminDao extends IBaseDao<Admin> {
	
	//登陆时，通过管理员用户名查询该管理员信息
	public Admin login(String username);
}
