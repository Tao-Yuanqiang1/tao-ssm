package com.rjxy.service;

import com.rjxy.model.User;

public interface IUserService {

	//用户注册
	public void register(User user);
	
	//用户登录
	public User login(String username, String password);
	
	//修改用户的余额
	public void updateBalance(User user);
}
