package com.rjxy.dao;

import com.rjxy.model.User;

public interface IUserDao extends IBaseDao<User> {
	
	//�û���¼
	public User login(String username);
	
	//�޸��û������
	public void updateBalance(User user);

}
