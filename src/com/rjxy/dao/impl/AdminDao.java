package com.rjxy.dao.impl;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.IAdminDao;
import com.rjxy.model.Admin;

@Repository
public class AdminDao extends BaseDao<Admin> implements IAdminDao {

	@Override
	public Admin login(String username) {
		Admin admin = openSession().selectOne(Admin.class.getName()+ ".login", username);
		return admin;
	}

	

}
