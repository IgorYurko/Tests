package com.tests.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.tests.dao.IOperations;

@SuppressWarnings("unchecked")
public abstract class AbstractDaoImpl<T extends Serializable> implements IOperations<T> {

	protected Class<T> clazz;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = Preconditions.checkNotNull(clazzToSet);
	}
	

	@Override
	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	

}
