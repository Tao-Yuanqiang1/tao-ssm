package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Address;

public interface IAddressService {

	//根据用户的id查询该用户的所有地址
	public List<Address> listByUserAdderss(int uid);
	
	//添加一个地址
	public void add(Address address);
	
	//根据地址id查询一个地址
	public Address load(int aid);
}
