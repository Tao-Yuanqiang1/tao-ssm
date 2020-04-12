package com.rjxy.dao;

import java.util.List;

import com.rjxy.model.Cart;

public interface ICartDao extends IBaseDao<Cart> {
	
	//�����û�id��ѯ���ڹ��ﳵ�е���Ʒ����
	public int loadByUserSize(int uid);

	//�����û���id��ѯ���û��ڹ��ﳵ�е�ȫ����Ʒ��Ϣ
	public List<Cart> listByUser(int uid);
	
	//������Ʒ��id���û���id�鿴���ﳵ����û����ͬ����Ʒ
	public Cart listByProduct(Cart cart);
	
	//��Ʒ����ҳ�棺��ѯ��һ����Ʒ��ͼƬ���ȵ�
	public Cart loadByBuy(int cid);
}
