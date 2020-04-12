package com.rjxy.service;

import java.util.List;

import com.rjxy.model.Cart;

public interface ICartService {

	//�����û�id��ѯ���ڹ��ﳵ�е���Ʒ����
	public int loadByUserSize(int uid);
	
	//�����Ʒ�����ﳵ
	public void add(Cart cart);
	
	//�����û���id��ѯ���û��ڹ��ﳵ�е�ȫ����Ʒ��Ϣ
	public List<Cart> listByUser(int uid);
	
	//�޸���Ʒ������
	public void updateQuantity(Cart cart);
	
	//��ѯ���ﳵ�е�һ����Ʒ
	public Cart load(int cid);
	
	//ɾ��һ�����ﳵ
	public void delete(int cid);
	
	//��Ʒ����ҳ�棺��ѯ��һ����Ʒ��ͼƬ���ȵ�
	public Cart loadByBuy(int cid);

}
