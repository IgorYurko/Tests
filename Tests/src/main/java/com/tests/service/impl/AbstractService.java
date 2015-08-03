package com.tests.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tests.dao.IOperations;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	protected abstract IOperations<T> getDao();
	
    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }
	
}
