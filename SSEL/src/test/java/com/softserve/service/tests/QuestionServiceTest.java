package com.softserve.service.tests;

import java.util.ArrayList;
import java.util.List;

import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
import com.softserve.service.QuestionService;

public class QuestionServiceTest {

	private QuestionService questionService;


	public QuestionServiceTest() {
		Question question = new Question();
		question.setTest(33);

		Option o1 = new Option();
		o1.setCorrect(true);
		o1.setValue("option1");

		Option o2 = new Option();
		o2.setCorrect(false);
		o2.setValue("option2");

		List<Option> options = new ArrayList<Option>();
		options.add(o1);
		options.add(o2);

		QuestionText qt = new QuestionText();
		qt.setValue("some value");
		qt.setOptions(options);

		question.setQuestionText(qt);

		questionService.addQuestion(question);
	}


}
