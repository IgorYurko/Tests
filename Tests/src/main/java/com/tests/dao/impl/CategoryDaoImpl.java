package com.tests.dao.impl;

import org.springframework.stereotype.Repository;

import com.tests.dao.ICategoryDao;
import com.tests.model.Category;


@Repository
public class CategoryDaoImpl extends AbstractDaoImpl<Category> implements ICategoryDao {

	public CategoryDaoImpl() {
		super();
		
		setClazz(Category.class);
		
	}
	
}
