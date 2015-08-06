package com.tests.springform;

import org.springframework.stereotype.Component;

@Component
public class TestForm {

	private String answerForm;

	public TestForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestForm(String answerForm) {
		super();
		this.answerForm = answerForm;
	}

	public String getAnswerForm() {
		return answerForm;
	}

	public void setAnswerForm(String answerForm) {
		this.answerForm = answerForm;
	}

	
}
