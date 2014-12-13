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
		 * QuestionsCreator qCreator = new QuestionsCreator(); Test test =
		 * testService.getTestById(1); ArrayList<Question> qList =
		 * (ArrayList<Question>) qCreator.createQuestions(test); for (Question q
		 * : qList) { questionService.addQuestion(q); }
		 * 
		 * //User 1 Question 1
		 * 
		 * TestStatistic testStatistic01 = new TestStatistic();
		 * testStatistic01.setTest(testService.getTestById(1));
		 * testStatistic01.setUser(userService.getUserById(7));
		 * testStatistic01.setGroup(groupService.getGroupById(3));
		 * testStatistic01.setQuestion(questionService.getQuestionById(4));
		 * testStatistic01.setUserResult(1); testStatistic01.setMaxResult(1);
		 * 
		 * ArrayList<Integer> userAnswers01 = new ArrayList<Integer>();
		 * userAnswers01.add(0); userAnswers01.add(0); userAnswers01.add(1);
		 * userAnswers01.add(0);
		 * 
		 * testStatistic01.setUserAnswers(userAnswers01);
		 * 
		 * //User 1 Question 2
		 * 
		 * TestStatistic testStatistic02 = new TestStatistic();
		 * testStatistic02.setTest(testService.getTestById(1));
		 * testStatistic02.setUser(userService.getUserById(7));
		 * testStatistic02.setGroup(groupService.getGroupById(3));
		 * testStatistic02.setQuestion(questionService.getQuestionById(5));
		 * testStatistic02.setUserResult(1); testStatistic02.setMaxResult(2);
		 * 
		 * ArrayList<Integer> userAnswers02 = new ArrayList<Integer>();
		 * userAnswers02.add(0); userAnswers02.add(0); userAnswers02.add(1);
		 * userAnswers02.add(1); userAnswers02.add(0);
		 * 
		 * testStatistic02.setUserAnswers(userAnswers02);
		 * 
		 * //User 1 Question 3
		 * 
		 * TestStatistic testStatistic03 = new TestStatistic();
		 * testStatistic03.setTest(testService.getTestById(1));
		 * testStatistic03.setUser(userService.getUserById(7));
		 * testStatistic03.setGroup(groupService.getGroupById(3));
		 * testStatistic03.setQuestion(questionService.getQuestionById(6));
		 * testStatistic03.setUserResult(0.75f);
		 * testStatistic03.setMaxResult(2);
		 * 
		 * ArrayList<Integer> userAnswers03 = new ArrayList<Integer>();
		 * userAnswers03.add(1); userAnswers03.add(1); userAnswers03.add(1);
		 * userAnswers03.add(0);
		 * 
		 * testStatistic03.setUserAnswers(userAnswers03);
		 * 
		 * testStatisticService.addTestStatistic(testStatistic01);
		 * testStatisticService.addTestStatistic(testStatistic02);
		 * testStatisticService.addTestStatistic(testStatistic03);
		 */

		ArrayList<TestStatistic> tsByUserByTest = (ArrayList<TestStatistic>) testStatisticService
				.getTestStatisticByUserByTest(1, 1);
		ArrayList<TestStatistic> tsByGroupByTest = (ArrayList<TestStatistic>) testStatisticService
				.getTestStatisticByGroupByTest(3, 1);
		float firstUserResultByTest = testStatisticService.getUserResultByTest(
				1, 1);
		List<Float> GroupResultByTest = testStatisticService
				.getGroupResultByTest(3, 1);

		model.addAttribute("tsByUserByTest", tsByUserByTest);
		model.addAttribute("tsByGroupByTest", tsByGroupByTest);
		model.addAttribute("firstUserResultByTest", firstUserResultByTest);
		model.addAttribute("GroupResultByTest", GroupResultByTest);

		return "testStatistic";
	}

	@RequestMapping(value = "/userTestStatistic", method = RequestMethod.GET)
	public String viewLogs(Model model,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "testId", required = true) Integer testId) {

		ArrayList<TestStatistic> tsByUserByTestList = (ArrayList<TestStatistic>) testStatisticService
				.getTestStatisticByUserByTest(userId, testId);

		model.addAttribute("tsByUserByTestList", tsByUserByTestList);
		model.addAttribute("user", userService.getUserById(userId));
		model.addAttribute("test", testService.getTestById(testId));

		return "userTestStatistic";
	}

}
