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

import com.softserve.entity.Block;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.Rating;
import com.softserve.entity.Test;
import com.softserve.entity.TestStatistic;
import com.softserve.entity.User;
import com.softserve.service.BlockService;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.GroupService;
import com.softserve.service.QuestionService;
import com.softserve.service.RatingService;
import com.softserve.service.TestService;
import com.softserve.service.TestStatisticService;
import com.softserve.service.UserService;
import com.softserve.validator.QuestionFormValidator;
import com.softserve.validator.TestValidator;

@Controller
public class TakeTestController {
	@Autowired
	private TestService testService;

	@Autowired
	private BlockService blockService;

	@Autowired
	private TestValidator testValidator;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionFormValidator questionFormValidator;

	@Resource(name = "TestStatisticService")
	private TestStatisticService testStatisticService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private CourseSchedulerService courseSchedulerService;

	@Autowired
	private RatingService ratingService;

	/**
	 * Handle tests list for subject
	 * 
	 * @param subjectId
	 *            unique subject identifier
	 * @param model
	 *            data model for view
	 * @return logical view name
	 */
	@RequestMapping(value = "/takeTest", method = RequestMethod.GET)
	public String takeTest(@RequestParam(value = "testId", required = true) Integer testId, Model model) {

		Integer userId = userService.getUserByEmail(userService.getCurrentUser()).getId();
		List<TestStatistic> tsByUserByTestList = testStatisticService.getTestStatisticByUserByTest(userId, testId);
		float totalUserResult = ((float) testStatisticService.getUserResultByTest(userId, testId) * 100);

		model.addAttribute("totalUserResult", totalUserResult);
		model.addAttribute("tsByUserByTestList", tsByUserByTestList);
		model.addAttribute("user", userService.getUserById(userId));

		Test test = testService.getTestById(testId);
		List<Question> questions = questionService.getQuestionsByTestId(test.getId());

		model.addAttribute("test", test);
		model.addAttribute("questions", questions);
		return "takeTest";
	}

	@RequestMapping(value = "/submitTest", method = RequestMethod.POST)
	public String submitTest(@RequestParam(value = "testId", required = true) Integer testId,
			@RequestParam(value = "questionId", required = true) Integer questionId,
			@RequestParam(value = "choices[]", required = false) ArrayList<Integer> choices, Model model) {

		Integer userId = userService.getUserByEmail(userService.getCurrentUser()).getId();

		TestStatistic testStatistic = new TestStatistic();
		testStatistic.setTest(testService.getTestById(testId));
		testStatistic.setQuestion(questionService.getQuestionById(questionId));
		testStatistic.setUser(userService.getUserById(userId));
		testStatistic.setUserAnswers(choices);
		testStatistic.setGroup(groupService.getGroupsByStudent(userId).get(0));

		ArrayList<Option> options = new ArrayList<Option>();
		for (Integer i : choices) {
			Option option = new Option();
			if (i == 0)
				option.setIsCorrect(false);
			if (i == 1)
				option.setIsCorrect(true);
			options.add(option);
		}

		testStatistic.setUserResult((float) questionService.getUserMarkByQuestion(questionId, options)[0]);
		testStatistic.setMaxResult((int) questionService.getUserMarkByQuestion(questionId, options)[1]);

		testStatisticService.addTestStatistic(testStatistic);

		List<TestStatistic> tsByUserByTestList = testStatisticService.getTestStatisticByUserByTest(userId, testId);
		float totalUserResult = ((float) testStatisticService.getUserResultByTest(userId, testId) * 100);
		
				model.addAttribute("totalUserResult", totalUserResult);
		model.addAttribute("tsByUserByTestList", tsByUserByTestList);
		model.addAttribute("user", userService.getUserById(userId));

		return "ok";

	}

	@RequestMapping(value = "submitRating", method = RequestMethod.POST)
	public String submitRating(@RequestParam(value = "testId", required = true) Integer testId, 
							   @RequestParam(value ="total", required = true) Double total) {
		User user = userService.getUserByEmail(userService.getCurrentUser());
		Rating newRating = new Rating();
		newRating.setMark(testStatisticService.getUserResultByTest(user.getId(), testId)*100.0);
		newRating.setUser(user);
		Test test = testService.getTestById(testId);
		Block block = blockService.getBlockById(test.getBlock().getId());
		CourseScheduler cs = courseSchedulerService.getCourseScheduleresBySubjectId(block.getSubject().getId()).get(0);
		newRating.setGroup(groupService.getGroupByScheduler(cs.getId()));
		newRating.setTest(test);
		ratingService.addRating(newRating);
		return "ok";
	}
	@RequestMapping(value = "/submitTest", method = RequestMethod.GET)
	public String submitTest(@RequestParam(value = "testId", required = true) Integer testId, Model model) {
		Integer userId = userService.getUserByEmail(userService.getCurrentUser()).getId();
		List<TestStatistic> tsByUserByTestList = testStatisticService.getTestStatisticByUserByTest(userId, testId);
		float totalUserResult = ((float) testStatisticService.getUserResultByTest(userId, testId) * 100);
		
		model.addAttribute("test", testService.getTestById(testId));
		model.addAttribute("totalUserResult", totalUserResult);
		model.addAttribute("tsByUserByTestList", tsByUserByTestList);
		model.addAttribute("user", userService.getUserById(userId));
		return "submitTest";
	}
}