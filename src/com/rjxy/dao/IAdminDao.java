package com.rjxy.dao;

import com.rjxy.model.Admin;

public interface IAdminDao extends IBaseDao<Admin> {
	
	//��½ʱ��ͨ������Ա�û�����ѯ�ù���Ա��Ϣ
	public Admin login(String username);
}
