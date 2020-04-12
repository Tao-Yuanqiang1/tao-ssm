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

	//����Ա��¼��֤����
	//������Ǵ� ��ҳ�� ���ݹ�����
	public Admin login(String username, String password) {
		//ͨ���û��������ݿ��в��Ҹù���Ա��Ϣ
		Admin admin = adminDao.login(username);
		
		if(admin == null) {
			throw new ShoppingException("�û���������");
		}
		if(!admin.getPassword().equals(CodeUtil.getMD5Encoding(password))) {
			//�������
			throw new ShoppingException("�������");
		}
		return admin;
	}

}
