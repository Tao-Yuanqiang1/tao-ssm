package com.rjxy.service;

import com.rjxy.model.User;

public interface IUserService {

	//�û�ע��
	public void register(User user);
	
	//�û���¼
	public User login(String username, String password);
	
	//�޸��û������
	public void updateBalance(User user);
}
