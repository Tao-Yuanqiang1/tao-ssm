package com.rjxy.dao;

import com.rjxy.model.User;

public interface IUserDao extends IBaseDao<User> {
	
	//用户登录
	public User login(String username);
	
	//修改用户的余额
	public void updateBalance(User user);

}
