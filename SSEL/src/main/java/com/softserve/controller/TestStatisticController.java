package com.softserve.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.TestStatistic;
import com.softserve.service.TestStatisticService;
import com.softserve.service.UserService;

@Controller
public class TestStatisticController {

	@Resource(name = "TestStatisticService")
	private TestStatisticService testStatisticService;

	@Autowired
	private UserService userService;

	/**
	 * Shows on userTestStatistic.jsp page user statistic by certain test. User
	 * id takes from session.
	 * 
	 */
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

		return "userTestStatistic";
	}

}
