package com.rjxy.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.rjxy.dao.IBaseDao;

@Repository
public class BaseDao<T> implements IBaseDao<T> {

	private SqlSessionFactory sqlSessionFactory;
	
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	protected SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

	// 创建泛型的运行时类对象
	private Class<?> clz;

	private Class<?> getClz() {
		if(clz == null) {
			clz= (Class<?>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return clz;
	}
	
	@Override
	public void add(T t) {
		openSession().insert(getClz().getName()+ ".add", t);
	}

	@Override
	public void delete(int id) { 
		openSession().delete(getClz().getName()+ ".delete", id);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		openSession().update(getClz().getName()+ ".update", t);
	}

	@Override
	public T load(int id) {
		// TODO Auto-generated method stub
		return openSession().selectOne(getClz().getName()+ ".load", id);
	}

	@Override
	public List<T> list() {
		// TODO Auto-generated method stub
		return openSession().selectList(getClz().getName()+ ".list");
	}

	
}
