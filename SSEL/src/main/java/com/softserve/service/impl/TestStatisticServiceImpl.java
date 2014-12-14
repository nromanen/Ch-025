package com.softserve.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.TestStatisticDao;
import com.softserve.entity.TestStatistic;
import com.softserve.service.QuestionService;
import com.softserve.service.TestStatisticService;

@Service("TestStatisticService")
public class TestStatisticServiceImpl implements TestStatisticService {

	@Autowired
	private TestStatisticDao testStatisticDao;

	@Autowired
	private QuestionService questionService;
	
	
	public void setTestStatisticDao(TestStatisticDao testStatisticDao) {
		this.testStatisticDao = testStatisticDao;
	}
	

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
	public List<Float> getGroupResultByTest(int groupId, int testId) {
		List<TestStatistic> testStatList = testStatisticDao
				.getTestStatisticByGroupByTest(groupId, testId);
		ArrayList<Integer> uniqueUserIds = new ArrayList<Integer>();
		for (TestStatistic tStat : testStatList) {
			if (!(uniqueUserIds.contains(tStat.getUser().getId()))) {
				uniqueUserIds.add(tStat.getUser().getId());
			}
		}
		List<Float> userResults = new ArrayList<Float>();
		for (int userId : uniqueUserIds) {
			userResults.add(testStatisticDao
					.getUserResultByTest(userId, testId));
		}
		Collections.sort(userResults);
		return userResults;
	}

}
