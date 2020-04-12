package com.rjxy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rjxy.dao.IOrderDao;
import com.rjxy.model.Order;

@Repository
public class OrderDao extends BaseDao<Order> implements IOrderDao {

	//��ѯһ����������ϸ����Ϣ��ͼƬ����Ʒ��
	public Order loadAllOne(int oid) {
		return openSession().selectOne(Order.class.getName()+ ".loadAllOne", oid);
	}

	//��ѯ��ͬ״̬�Ķ�����Ӧ�Ķ���
	public List<Order> listByStatus(Order order) {
		return openSession().selectList(Order.class.getName()+ ".listByStatus", order);
	}

	//��ѯȫ������
	public List<Order> listByStatus0(int uid) {
		return openSession().selectList(Order.class.getName()+ ".listByStatus0", uid);
	}

	//��ѯ��������״̬������
	public int listOrderBySize(Order order) {
		return openSession().selectOne(Order.class.getName()+ ".listOrderBySize", order);
	}

}
