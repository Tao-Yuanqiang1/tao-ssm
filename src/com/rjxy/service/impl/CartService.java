package com.rjxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjxy.dao.ICartDao;
import com.rjxy.model.Cart;
import com.rjxy.service.ICartService;

@Service
public class CartService implements ICartService {
	
	private ICartDao cartDao;
	
	@Resource
	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}

	//�����û�id��ѯ���ڹ��ﳵ�е���Ʒ����
	public int loadByUserSize(int uid) {
		int size = cartDao.loadByUserSize(uid);
		return size;
	}

	//�����Ʒ�����ﳵ
	public void add(Cart cart) {
		//�鿴���ﳵ����û����ͬ����Ʒ
		Cart c = cartDao.listByProduct(cart);
		if(c == null) {
			cartDao.add(cart);			
		}
		else {
			c.setQuantity(c.getQuantity() + cart.getQuantity());
			cartDao.update(c);
		}
	}

	//�����û���id��ѯ���û��ڹ��ﳵ�е�ȫ����Ʒ��Ϣ
	public List<Cart> listByUser(int uid) {
		return cartDao.listByUser(uid);
	}

	//�޸���Ʒ����
	public void updateQuantity(Cart cart) {
		cartDao.update(cart);
	}

	//��ѯ���ﳵ�е�һ����Ʒ
	public Cart load(int cid) {
		return cartDao.load(cid);
	}

	//ɾ��һ�����ﳵ
	public void delete(int cid) {
		cartDao.delete(cid);
	}

	//��Ʒ����ҳ�棺��ѯ��һ����Ʒ��ͼƬ���ȵ�
	public Cart loadByBuy(int cid) {
		return cartDao.loadByBuy(cid);
	}

}
