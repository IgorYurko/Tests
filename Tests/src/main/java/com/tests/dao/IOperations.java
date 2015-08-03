package com.tests.dao;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

	List<T> findAll();
	
}
