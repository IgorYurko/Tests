package com.tests.springform;

import org.springframework.stereotype.Component;

@Component
public class TestForm {

	private Integer answerForm;

	public TestForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestForm(Integer answerForm) {
		super();
		this.answerForm = answerForm;
	}

	public Integer getAnswerForm() {
		return answerForm;
	}

	public void setAnswerForm(Integer answerForm) {
		this.answerForm = answerForm;
	}

	
}
