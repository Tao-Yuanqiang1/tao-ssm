package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Address;

public interface IAddressService {

	//�����û���id��ѯ���û������е�ַ
	public List<Address> listByUserAdderss(int uid);
	
	//���һ����ַ
	public void add(Address address);
	
	//���ݵ�ַid��ѯһ����ַ
	public Address load(int aid);
}
