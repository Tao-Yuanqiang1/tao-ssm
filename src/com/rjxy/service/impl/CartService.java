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

	//根据用户id查询其在购物车中的商品数量
	public int loadByUserSize(int uid) {
		int size = cartDao.loadByUserSize(uid);
		return size;
	}

	//添加商品到购物车
	public void add(Cart cart) {
		//查看购物车中有没有相同的商品
		Cart c = cartDao.listByProduct(cart);
		if(c == null) {
			cartDao.add(cart);			
		}
		else {
			c.setQuantity(c.getQuantity() + cart.getQuantity());
			cartDao.update(c);
		}
	}

	//根据用户的id查询该用户在购物车中的全部商品信息
	public List<Cart> listByUser(int uid) {
		return cartDao.listByUser(uid);
	}

	//修改商品数量
	public void updateQuantity(Cart cart) {
		cartDao.update(cart);
	}

	//查询购物车中的一件商品
	public Cart load(int cid) {
		return cartDao.load(cid);
	}

	//删除一个购物车
	public void delete(int cid) {
		cartDao.delete(cid);
	}

	//商品结算页面：查询该一件商品的图片、等等
	public Cart loadByBuy(int cid) {
		return cartDao.loadByBuy(cid);
	}

}
