package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Address;

public interface IAddressDao extends IBaseDao<Address> {

	//��ѯ�û���ȫ����ַ
	public List<Address> listByUser(int uid);
}
