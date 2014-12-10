package com.softserve.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.entity.TestStatistic;
import com.softserve.service.TestStatisticService;

@Controller
public class TestStatisticController {
	
	@Resource(name = "TestStatisticService")
	private TestStatisticService testStatisticService;
	
	@RequestMapping(value = "/testStatistic", method = RequestMethod.GET)
	public String viewLogs(Model model) {
/*
		TestStatistic testStatistic01 = new TestStatistic();
		TestStatistic testStatistic02 = new TestStatistic();
		testStatistic01.setTestId(2);
		testStatistic01.setUserId(3);
		testStatistic01.setGroupId(101);
		testStatistic01.setQuestionId(8);
		testStatistic01.setResult("4of4");
		
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		resultList.add(1);
		resultList.add(0);
		resultList.add(1);
		resultList.add(0);
		resultList.add(0);
		resultList.add(1);
		
		testStatistic01.setUserAnswers(resultList);
			
		testStatistic02.setTestId(2);
		testStatistic02.setUserId(3);
		testStatistic02.setGroupId(101);
		testStatistic02.setQuestionId(9);
		testStatistic02.setResult("5of5");
		
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
		List<TestStatistic> theList = testStatisticService.getTestStatisticByUserByTest(3, 2);
		
		float userRes1 = (float) testStatisticService.getUserResultByTest(1, 1);
		float userRes2 = (float) testStatisticService.getUserResultByTest(2, 1);
		float userRes3 = (float) testStatisticService.getUserResultByTest(3, 2);
		float userRes4 = (float) testStatisticService.getUserResultByTest(1, 2);
		
		ArrayList<Float> group100Statistic = testStatisticService.getGroupResultByTest(100, 1);

		
		model.addAttribute("statisticList", theList);
		model.addAttribute("userRes1", userRes1);
		model.addAttribute("userRes2", userRes2);
		model.addAttribute("userRes3", userRes3);
		model.addAttribute("userRes4", userRes4);
		model.addAttribute("group100Statistic", group100Statistic);
		
		return "testStatistic";
	}

}
