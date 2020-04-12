package com.rjxy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.IAddressDao;
import com.rjxy.model.Address;

@Repository
public class AddressDao extends BaseDao<Address> implements IAddressDao {
	
	//查询用户所有的地址
	public List<Address> listByUser(int uid) {
		List<Address> addresses = openSession().selectList(Address.class.getName()+ ".listByUser", uid);
		return addresses;
	}

}
