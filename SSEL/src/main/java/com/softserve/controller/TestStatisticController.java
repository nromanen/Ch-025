package com.softserve.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Question;
import com.softserve.entity.Test;
import com.softserve.entity.TestStatistic;
import com.softserve.service.GroupService;
import com.softserve.service.QuestionService;
import com.softserve.service.TestService;
import com.softserve.service.TestStatisticService;
import com.softserve.service.UserService;
import com.softserve.util.QuestionsCreator;

@Controller
public class TestStatisticController {

	@Resource(name = "TestStatisticService")
	private TestStatisticService testStatisticService;

	@Autowired
	private TestService testService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/testStatistic", method = RequestMethod.GET)
	public String testStatistic(Model model) {
		/*
		QuestionsCreator qCreator = new QuestionsCreator();
		Test test = testService.getTestById(2);
		ArrayList<Question> qList = (ArrayList<Question>) qCreator
				.createQuestions(test);
		for (Question q : qList) {
			questionService.addQuestion(q);
		}
		*/
		/*
		int localUserId = 7; 
		
		// User 1 Question 1

		TestStatistic testStatistic01 = new TestStatistic();
		testStatistic01.setTest(testService.getTestById(2));
		testStatistic01.setUser(userService.getUserById(localUserId));
		testStatistic01.setGroup(groupService.getGroupById(3));
		testStatistic01.setQuestion(questionService.getQuestionById(6));
		testStatistic01.setUserResult(2.5f);
		testStatistic01.setMaxResult(5);

		ArrayList<Integer> userAnswers01 = new ArrayList<Integer>();
		userAnswers01.add(0);
		userAnswers01.add(1);
		userAnswers01.add(0);
		userAnswers01.add(0);

		testStatistic01.setUserAnswers(userAnswers01);

		// User 1 Question 2

		TestStatistic testStatistic02 = new TestStatistic();
		testStatistic02.setTest(testService.getTestById(2));
		testStatistic02.setUser(userService.getUserById(localUserId));
		testStatistic02.setGroup(groupService.getGroupById(3));
		testStatistic02.setQuestion(questionService.getQuestionById(7));
		testStatistic02.setUserResult(2.4f);
		testStatistic02.setMaxResult(4);

		ArrayList<Integer> userAnswers02 = new ArrayList<Integer>();
		userAnswers02.add(0);
		userAnswers02.add(0);
		userAnswers02.add(1);
		userAnswers02.add(1);
		userAnswers02.add(0);

		testStatistic02.setUserAnswers(userAnswers02);

		// User 1 Question 3

		TestStatistic testStatistic03 = new TestStatistic();
		testStatistic03.setTest(testService.getTestById(2));
		testStatistic03.setUser(userService.getUserById(localUserId));
		testStatistic03.setGroup(groupService.getGroupById(3));
		testStatistic03.setQuestion(questionService.getQuestionById(8));
		testStatistic03.setUserResult(0.75f);
		testStatistic03.setMaxResult(3);

		ArrayList<Integer> userAnswers03 = new ArrayList<Integer>();
		userAnswers03.add(0);
		userAnswers03.add(0);
		userAnswers03.add(1);
		userAnswers03.add(1);

		testStatistic03.setUserAnswers(userAnswers03);
		
		// User 1 Question 4
		
		TestStatistic testStatistic04 = new TestStatistic();
		testStatistic04.setTest(testService.getTestById(2));
		testStatistic04.setUser(userService.getUserById(localUserId));
		testStatistic04.setGroup(groupService.getGroupById(3));
		testStatistic04.setQuestion(questionService.getQuestionById(9));
		testStatistic04.setUserResult(3);
		testStatistic04.setMaxResult(4);

		ArrayList<Integer> userAnswers04 = new ArrayList<Integer>();
		userAnswers04.add(1);
		userAnswers04.add(1);
		userAnswers04.add(1);
		userAnswers04.add(0);

		testStatistic04.setUserAnswers(userAnswers04);

		
		// User 1 Question 5
		
		TestStatistic testStatistic05 = new TestStatistic();
		testStatistic05.setTest(testService.getTestById(2));
		testStatistic05.setUser(userService.getUserById(localUserId));
		testStatistic05.setGroup(groupService.getGroupById(3));
		testStatistic05.setQuestion(questionService.getQuestionById(10));
		testStatistic05.setUserResult(2);
		testStatistic05.setMaxResult(4);

		ArrayList<Integer> userAnswers05 = new ArrayList<Integer>();
		userAnswers05.add(0);
		userAnswers05.add(1);
		userAnswers05.add(1);
		userAnswers05.add(0);

		testStatistic05.setUserAnswers(userAnswers05);

		
		testStatisticService.addTestStatistic(testStatistic01);
		testStatisticService.addTestStatistic(testStatistic02);
		testStatisticService.addTestStatistic(testStatistic03);
		testStatisticService.addTestStatistic(testStatistic04);
		testStatisticService.addTestStatistic(testStatistic05);
*/
		ArrayList<TestStatistic> tsByUserByTest = (ArrayList<TestStatistic>) testStatisticService
				.getTestStatisticByUserByTest(7, 1);
		ArrayList<TestStatistic> tsByGroupByTest = (ArrayList<TestStatistic>) testStatisticService
				.getTestStatisticByGroupByTest(3, 1);
		float firstUserResultByTest1 = testStatisticService.getUserResultByTest(7, 1);
		float firstUserResultByTest2 = testStatisticService.getUserResultByTest(7, 2);
		List<Float> GroupResultByTest = testStatisticService
				.getGroupResultByTest(3, 1);

		model.addAttribute("tsByUserByTest", tsByUserByTest);
		model.addAttribute("tsByGroupByTest", tsByGroupByTest);
		model.addAttribute("firstUserResultByTest1", firstUserResultByTest1);
		model.addAttribute("firstUserResultByTest2", firstUserResultByTest2);
		model.addAttribute("GroupResultByTest", GroupResultByTest);

		return "testStatistic";
	}

	@RequestMapping(value = "/userTestStatistic", method = RequestMethod.GET)
	public String viewLogs(Model model,
			@RequestParam(value = "testId", required = true) Integer testId) {
		Integer userId = userService.getUserByEmail(
				userService.getCurrentUser()).getId();
		List<TestStatistic> tsByUserByTestList = testStatisticService
				.getTestStatisticByUserByTest(userId, testId);
		float totalUserResult = ((float) testStatisticService
				.getUserResultByTest(userId, testId) * 100);

		model.addAttribute("totalUserResult", totalUserResult);
		model.addAttribute("tsByUserByTestList", tsByUserByTestList);
		model.addAttribute("user", userService.getUserById(userId));
		model.addAttribute("test", testService.getTestById(testId));

		return "userTestStatistic";
	}

}
