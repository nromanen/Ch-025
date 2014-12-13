package com.softserve.form;

import java.util.List;

import com.softserve.entity.Option;
import com.softserve.entity.Question;

public class QuestionForm {
	private Question question;
	
	private String name;
	
	private List<Option> answers;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Option> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Option> answers) {
		this.answers = answers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
