package com.softserve.form;

import java.util.List;

import com.softserve.entity.Option;
import com.softserve.entity.Question;

public class QuestionForm {
	private Question question;
	
	private List<Option> answers;
	
	private int testId;
	
	public QuestionForm() {

	}

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

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

}
