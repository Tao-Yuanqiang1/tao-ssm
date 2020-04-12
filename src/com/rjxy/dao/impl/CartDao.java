package com.rjxy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.ICartDao;
import com.rjxy.model.Cart;

@Repository
public class CartDao extends BaseDao<Cart> implements ICartDao {

	//�����û�id��ѯ���ڹ��ﳵ�е���Ʒ����
	public int loadByUserSize(int uid) {
		return openSession().selectOne(Cart.class.getName()+ ".loadByUserSize", uid);
	}

	//�����û���id��ѯ���û��ڹ��ﳵ�е�ȫ����Ʒ��Ϣ
	public List<Cart> listByUser(int uid) {
		return openSession().selectList(Cart.class.getName()+ ".listByUser", uid);
	}

	//������Ʒ��id���û���id�鿴���ﳵ����û����ͬ����Ʒ
	public Cart listByProduct(Cart cart) {
		return openSession().selectOne(Cart.class.getName()+ ".listByProduct", cart);
	}

	//��Ʒ����ҳ�棺��ѯ��һ����Ʒ��ͼƬ���ȵ�
	public Cart loadByBuy(int cid) {
		return openSession().selectOne(Cart.class.getName()+ ".loadByBuy", cid);
	}

}
