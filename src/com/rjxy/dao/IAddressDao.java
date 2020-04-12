package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Address;

public interface IAddressDao extends IBaseDao<Address> {

	//查询用户的全部地址
	public List<Address> listByUser(int uid);
}
