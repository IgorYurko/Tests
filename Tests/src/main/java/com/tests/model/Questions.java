package com.tests.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Questions implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="quest")
	private String quest;
	
	@OneToOne
	@JoinColumn
	private Category category;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="questions_id")
	private Set<Answers> answers;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="questions_id")
	private List<Goodanswer> goodanswer;
	
	public Questions() {
		super();
	}

	public Questions(int id, String quest, Category category,
			Set<Answers> answers, List<Goodanswer> goodanswer) {
		super();
		this.id = id;
		this.quest = quest;
		this.category = category;
		this.answers = answers;
		this.goodanswer = goodanswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuest() {
		return quest;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answers> answers) {
		this.answers = answers;
	}

	public List<Goodanswer> getGoodanswer() {
		return goodanswer;
	}

	public void setGoodanswer(List<Goodanswer> goodanswer) {
		this.goodanswer = goodanswer;
	}

	
	
}
