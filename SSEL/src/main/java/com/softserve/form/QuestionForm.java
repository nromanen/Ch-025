package com.softserve.form;

import java.util.List;

import com.softserve.entity.Answer;
import com.softserve.entity.Question;

public class QuestionForm {
	private Question question;
	
	private List<Answer> answers;
	
	private int testId;
	
	public QuestionForm() {

	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

}
