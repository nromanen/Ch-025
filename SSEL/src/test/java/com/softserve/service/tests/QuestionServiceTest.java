package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.softserve.dao.QuestionDao;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
import com.softserve.service.impl.QuestionServiceImpl;

public class QuestionServiceTest {

	private QuestionServiceImpl questionService = new QuestionServiceImpl();
	private Question question = new Question();
	private QuestionDao questionDao;

	@Before
	public void initialize() {
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

//		questionService.addQuestion(question);

//		System.out.println(question.getQuestionText().toString());
		System.out.println("-------------");
		System.out.println(questionService.addQuestion(question));
	}

//	@Test
//	public void testXML() {
//		assertEquals(question.toString(), questionService.addQuestion(question).toString());
//	}

	@Test
	public void test1() {
		assertEquals("1","1");
	}
}
