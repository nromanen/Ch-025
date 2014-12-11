package com.softserve.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.TestStatisticDao;
import com.softserve.entity.Question;
import com.softserve.entity.TestStatistic;
import com.softserve.service.QuestionService;
import com.softserve.service.TestStatisticService;
import com.softserve.util.TestStatisticWithQuestion;

@Service("TestStatisticService")
public class TestStatisticServiceImpl implements TestStatisticService {

	@Autowired
	private TestStatisticDao testStatisticDao;

	@Autowired
	private QuestionService questionService; // REDOOOOO!!!!!!

	@Override
	@Transactional
	public void addTestStatistic(TestStatistic testStatistic) {
		testStatisticDao.addTestStatistic(testStatistic);

	}

	@Override
	public List<TestStatistic> getTestStatisticByUserByTest(int userId,
			int testId) {
		return testStatisticDao.getTestStatisticByUserByTest(userId, testId);
	}

	@Override
	public List<TestStatistic> getTestStatisticByGroupByTest(int groupId,
			int testId) {
		return testStatisticDao.getTestStatisticByGroupByTest(groupId, testId);
	}

	@Override
	public float getUserResultByTest(int userId, int testId) {
		return testStatisticDao.getUserResultByTest(userId, testId);
	}

	@Override
	public ArrayList<Float> getGroupResultByTest(int groupId, int testId) {
		List<TestStatistic> testStatList = testStatisticDao
				.getTestStatisticByGroupByTest(groupId, testId);
		ArrayList<Integer> uniqueUserIds = new ArrayList<Integer>();
		for (TestStatistic tStat : testStatList) {
			if (!(uniqueUserIds.contains(tStat.getUserId()))) {
				uniqueUserIds.add(tStat.getUserId());
			}
		}
		ArrayList<Float> userResults = new ArrayList<Float>();
		for (int userId : uniqueUserIds) {
			userResults.add(testStatisticDao
					.getUserResultByTest(userId, testId));
		}
		Collections.sort(userResults);
		return userResults;
	}

	@Override
	public List<TestStatisticWithQuestion> getTestStatisticByUserByTestForController(
			int userId, int testId) {
		List<TestStatistic> tStatList = testStatisticDao
				.getTestStatisticByUserByTest(userId, testId);

		ArrayList<TestStatisticWithQuestion> tStatWithQArray = new ArrayList<TestStatisticWithQuestion>();
		Question question; // REDOOOOO!!!!!!!!
		for (TestStatistic testStat : tStatList) {
			question = questionService
					.getQuestionById(testStat.getQuestionId());
			TestStatisticWithQuestion tStatWithQ = new TestStatisticWithQuestion();
			tStatWithQ.setTestStatistic(testStat);
			tStatWithQ.setQuestion(question);
			tStatWithQArray.add(tStatWithQ);
		}
		return tStatWithQArray;
	}

}
