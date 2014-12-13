package com.softserve.dao;

import java.util.List;

import com.softserve.entity.TestStatistic;

public interface TestStatisticDao {
	
	void addTestStatistic(TestStatistic testStatistic);
	
	List<TestStatistic> getTestStatisticByUserByTest (int userId, int testId);
	
	List<TestStatistic> getTestStatisticByGroupByTest (int groupId, int testId);
	
	float getUserResultByTest(int userId, int testId);
	
	

}
