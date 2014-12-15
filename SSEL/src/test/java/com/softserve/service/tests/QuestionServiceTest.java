package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
import com.softserve.service.QuestionService;
import com.softserve.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class QuestionServiceTest {

	@Autowired
	QuestionService questionService;

	@Autowired
	TestService testService;

	private Question question;
	private QuestionText questionText = new QuestionText();
	private Option option = new Option();
	private List<Option> options = new ArrayList<Option>();

	@Before
	public void initialize() {
		question = new Question();
		question.setTest(testService.getTestById(1));
		question.setMark(5);
		for (int i = 1; i < 6; i++) {
			option = new Option();
			option.setIsCorrect(false);
			if (i == 3 || i == 4) {
				option.setIsCorrect(true);
			}
			option.setValue("option " + i);
			options.add(option);
		}
		questionText.setValue("Some question");
		questionText.setOptions(options);
		question.setQuestionText(questionText);
	}

	@Test
	public void checkAnswer() {
		assertFalse(questionService.checkAnswer(question, question
				.getQuestion().getOptions().get(0).getValue()));
		assertTrue(questionService.checkAnswer(question, question.getQuestion()
				.getOptions().get(2).getValue()));
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testGetQuestionById() {
		assertNotNull(questionService.getQuestionById(2));
		assertNull(questionService.getQuestionById(111));
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testDeleteQuestionById() {
		assertEquals(3, questionService.getAllQuestions().size());
		questionService.deleteQuestion(questionService.getQuestionById(1));
		assertEquals(2, questionService.getAllQuestions().size());
		assertTrue(questionService.getQuestionById(1).isDeleted());
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testGetQuestionsByTestId() {
		assertNotNull(questionService.getQuestionsByTestId(1));
		assertNull(questionService.getQuestionsByTestId(111));
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testUpdateQuestion() {
		Question question = questionService.getQuestionById(1);
		assertEquals(1, question.getTest().getId());
		question.setTest(testService.getTestById(2));
		questionService.updateQuestion(question);
		assertEquals(2, questionService.getQuestionById(1).getTest().getId());
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testRestoreQuestion() {
		assertEquals(3, questionService.getAllQuestions().size());
		questionService.deleteQuestion(questionService.getQuestionById(1));
		assertEquals(2, questionService.getAllQuestions().size());
		assertTrue(questionService.getQuestionById(1).isDeleted());
		questionService.restoreQuestion(questionService.getQuestionById(1));
		assertFalse(questionService.getQuestionById(1).isDeleted());
		assertEquals(3, questionService.getAllQuestions().size());
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testAddQuestion() {
		assertEquals(3, questionService.getAllQuestions().size());
		questionService.addQuestion(question);
		assertEquals(4, questionService.getAllQuestions().size());
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testGetAllDeletedQuestions() {
		assertNull(questionService.getAllDeletedQuestions());
		questionService.deleteQuestion(questionService.getQuestionById(1));
		assertEquals(1, questionService.getAllDeletedQuestions().size());
	}

	@Test
	@DatabaseSetup("classpath:questions.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void getUserMarkByQuestion() {
		Question myQuestion = questionService.addQuestion(question);
		assertEquals(question.getMark(), questionService.getUserMarkByQuestion(
				myQuestion.getId(), options)[1], 0.0);
		options.get(0).setIsCorrect(true);
		assertNotNull(questionService.getUserMarkByQuestion(myQuestion.getId(),
				options));
	}

	@Test
//	@DatabaseSetup("classpath:questions.xml")
//	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:questions.xml")
	public void testGetRightAnswers() {
		assertNotNull(questionService.getRightAnswers(question));
//		assertNotNull(questionService.getRightAnswers(questionService.getQuestionById(1)));
	}
}