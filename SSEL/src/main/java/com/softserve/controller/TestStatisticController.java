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

import com.softserve.entity.TestStatistic;
import com.softserve.service.TestStatisticService;
import com.softserve.util.TestStatisticWithQuestion;

@Controller
public class TestStatisticController {
	
	@Resource(name = "TestStatisticService")
	private TestStatisticService testStatisticService;
	
	@RequestMapping(value = "/testStatistic", method = RequestMethod.GET)
	public String testStatistic(Model model) {
/*
		TestStatistic testStatistic01 = new TestStatistic();
		TestStatistic testStatistic02 = new TestStatistic();
		testStatistic01.setTestId(1);
		testStatistic01.setUserId(1);
		testStatistic01.setGroupId(102);
		testStatistic01.setQuestionId(1);
		testStatistic01.setResult("3of4");
		
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		resultList.add(1);
		resultList.add(0);
		resultList.add(1);
		resultList.add(0);
		resultList.add(0);
		resultList.add(1);
		
		testStatistic01.setUserAnswers(resultList);
			
		testStatistic02.setTestId(1);
		testStatistic02.setUserId(3);
		testStatistic02.setGroupId(102);
		testStatistic02.setQuestionId(2);
		testStatistic02.setResult("4of5");
		
		ArrayList<Integer> resultList1 = new ArrayList<Integer>();
		resultList.add(1);
		resultList.add(1);
		resultList.add(0);
		resultList.add(0);
		resultList.add(0);
		resultList.add(0);
		
		testStatistic02.setUserAnswers(resultList1);
		
				
		testStatisticService.addTestStatistic(testStatistic01);
		testStatisticService.addTestStatistic(testStatistic02);
		
	*/
		
		List<TestStatistic> theList = testStatisticService.getTestStatisticByUserByTest(1, 1);
		
		float userRes1 = (float) testStatisticService.getUserResultByTest(1, 1);
		float userRes2 = (float) testStatisticService.getUserResultByTest(2, 1);
		float userRes3 = (float) testStatisticService.getUserResultByTest(3, 1);
		float userRes4 = (float) testStatisticService.getUserResultByTest(1, 2);
		
		ArrayList<Float> group100Statistic = testStatisticService.getGroupResultByTest(101, 1);

		
		
		model.addAttribute("group100Statistic", group100Statistic);
		model.addAttribute("userRes1", userRes1);
		model.addAttribute("userRes2", userRes2);
		model.addAttribute("userRes3", userRes3);
		model.addAttribute("userRes4", userRes4);
		
		model.addAttribute("statisticList", theList);
		
		return "testStatistic";
	}

	@RequestMapping(value = "/userTestStatistic", method = RequestMethod.GET)
	public String viewLogs(Model model,
		@RequestParam(value = "userId", required = true) Integer userId,
		@RequestParam(value = "testId", required = true) Integer testId) {
		
		List<TestStatisticWithQuestion> testStatWithQList = 
				testStatisticService.getTestStatisticByUserByTestForController(userId, testId);
		//testStatWithQList.get(0).getTestStatistic().getUserAnswers().get(4);
		
		
		model.addAttribute("testStatWithQList", testStatWithQList);
		return "userTestStatistic";		
	}
	
	
}
