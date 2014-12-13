package com.softserve.util;

import com.softserve.entity.Question;
import com.softserve.entity.TestStatistic;

public class TestStatisticWithQuestion {
	
	private TestStatistic testStatistic;
	
	private Question question;  // ПЕРЕРОБИТИ!!!!!!!!!!!

	public TestStatistic getTestStatistic() {
		return testStatistic;
	}

	public void setTestStatistic(TestStatistic testStatistic) {
		this.testStatistic = testStatistic;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
