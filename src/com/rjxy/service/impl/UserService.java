package com.rjxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.IUserDao;
import com.rjxy.exception.ShoppingException;
import com.rjxy.model.User;
import com.rjxy.service.IUserService;
import com.rjxy.util.CodeUtil;

@Service
public class UserService implements IUserService {

	private IUserDao userDao;
	
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	//用户登录
	public User login(String username, String password) {
		User user = userDao.login(username);
		if(user.getUsername() == null || user.getEmail() == null || user.getPhone() == null) {
			throw new ShoppingException("用户名不存在!");
		}
		if(!user.getPassword().equals(CodeUtil.getMD5Encoding(password))) {
			throw new ShoppingException("密码不正确!");
		}
		return user;
	}

	//用户注册
	public void register(User user) {
		String password = user.getPassword();
		user.setPassword(CodeUtil.getMD5Encoding(password));
		userDao.add(user);
	}

	//修改用户余额
	public void updateBalance(User user) {
		userDao.updateBalance(user);
	}

}
