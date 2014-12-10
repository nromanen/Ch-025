package com.softserve.service;

import java.util.ArrayList;
import java.util.List;

import com.softserve.entity.TestStatistic;

public interface TestStatisticService {
	
	void addTestStatistic(TestStatistic testStatistic);
	
	List<TestStatistic> getTestStatisticByUserByTest (int userId, int testId);
	
	List<TestStatistic> getTestStatisticByGroupByTest (int groupId, int testId);
	
	float getUserResultByTest(int userId, int testId);
	
	ArrayList<Float> getGroupResultByTest(int groupId, int testId);

}
