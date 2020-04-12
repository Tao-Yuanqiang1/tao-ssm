package com.rjxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.IAddressDao;
import com.rjxy.model.Address;
import com.rjxy.service.IAddressService;

@Service
public class AddressService implements IAddressService {

	public IAddressDao addressDao;
	
	@Resource
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}

	//根据用户的id查询该用户的所有地址
	public List<Address> listByUserAdderss(int uid) {
		return addressDao.listByUser(uid);
	}

	//添加一个地址
	public void add(Address address) {
		addressDao.add(address);
	}

	//根据地址id查询一个地址
	public Address load(int aid) {
		return addressDao.load(aid);
	}

}
