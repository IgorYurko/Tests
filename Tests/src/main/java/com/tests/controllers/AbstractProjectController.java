package com.tests.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.tests.service.ICategoryService;
import com.tests.service.IQuestionsService;
import com.tests.springform.TestForm;

public abstract class AbstractProjectController {
	
	@Autowired
	protected ICategoryService category;
	
	@Autowired
	protected IQuestionsService questions;
	
	@Autowired
	protected TestForm testForm;

	protected final int Q_NUMBER = 10;
}
