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

	//�����û���id��ѯ���û������е�ַ
	public List<Address> listByUserAdderss(int uid) {
		return addressDao.listByUser(uid);
	}

	//���һ����ַ
	public void add(Address address) {
		addressDao.add(address);
	}

	//���ݵ�ַid��ѯһ����ַ
	public Address load(int aid) {
		return addressDao.load(aid);
	}

}
