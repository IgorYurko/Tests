package com.tests.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tests.dao.ICategoryDao;
import com.tests.dao.IOperations;
import com.tests.model.Category;
import com.tests.service.ICategoryService;



@Service
public class CategoryServiceImpl extends AbstractService<Category> implements ICategoryService{
	
	@Autowired
	private ICategoryDao dao;
	
	@Override
	protected IOperations<Category> getDao() {

		return dao;
		
	}

	
	
}
