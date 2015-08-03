package com.tests.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tests.dao.IQuestionsDao;
import com.tests.model.Questions;

@Repository
public class QuestionsDaoImpl extends AbstractDaoImpl<Questions> implements IQuestionsDao{

	public QuestionsDaoImpl() {
		
		super();
		setClazz(Questions.class);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> findAllFromId(int id) {
		
		return getCurrentSession().createQuery("From " + clazz.getName() + 
				" where category_id=" + id).list();
		
	}

}
