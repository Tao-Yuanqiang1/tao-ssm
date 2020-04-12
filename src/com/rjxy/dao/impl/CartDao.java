package com.rjxy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.ICartDao;
import com.rjxy.model.Cart;

@Repository
public class CartDao extends BaseDao<Cart> implements ICartDao {

	//根据用户id查询其在购物车中的商品数量
	public int loadByUserSize(int uid) {
		return openSession().selectOne(Cart.class.getName()+ ".loadByUserSize", uid);
	}

	//根据用户的id查询该用户在购物车中的全部商品信息
	public List<Cart> listByUser(int uid) {
		return openSession().selectList(Cart.class.getName()+ ".listByUser", uid);
	}

	//根据商品的id和用户的id查看购物车中有没有相同的商品
	public Cart listByProduct(Cart cart) {
		return openSession().selectOne(Cart.class.getName()+ ".listByProduct", cart);
	}

	//商品结算页面：查询该一件商品的图片、等等
	public Cart loadByBuy(int cid) {
		return openSession().selectOne(Cart.class.getName()+ ".loadByBuy", cid);
	}

}
