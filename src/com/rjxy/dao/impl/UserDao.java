package com.rjxy.dao.impl;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.IUserDao;
import com.rjxy.model.User;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

	//�û���¼
	public User login(String username) {
		return openSession().selectOne(User.class.getName()+ ".login", username);
	}

	//�޸��û����
	public void updateBalance(User user) {
		openSession().update(User.class.getName()+ ".updateBalance", user);
	}

}
