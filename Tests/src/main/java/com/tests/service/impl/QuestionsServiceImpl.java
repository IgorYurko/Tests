package com.tests.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tests.dao.IOperations;
import com.tests.dao.IQuestionsDao;
import com.tests.model.Questions;
import com.tests.service.IQuestionsService;

@Service
public class QuestionsServiceImpl extends AbstractService<Questions> implements IQuestionsService{

	@Autowired
	private IQuestionsDao dao;
		
	@Override
	protected IQuestionsDao getDao() {
		
		return dao;
	}

	@Override
	public List<Questions> findAllFromId(int id) {
		
		return getDao().findAllFromId(id);
		
	}

	
}
