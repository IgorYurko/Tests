package com.tests.dao;

import java.util.List;

import com.tests.model.Questions;



public interface IQuestionsDao extends IOperations<Questions>{

	List<Questions> findAllFromId(int id);
	
}
