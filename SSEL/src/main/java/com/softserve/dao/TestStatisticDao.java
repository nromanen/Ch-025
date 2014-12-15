package com.softserve.dao;

import java.util.List;

import com.softserve.entity.TestStatistic;

public interface TestStatisticDao {

	/**
	 * Add TestStatistic object to database.
	 */
	void addTestStatistic(TestStatistic testStatistic);

	/**
	 * Gets from database list of TestStatistic objects by certain user and by
	 * particular test
	 * 
	 * @return List of TestStatistic objects
	 */
	List<TestStatistic> getTestStatisticByUserByTest(int userId, int testId);

	/**
	 * Gets from database list of TestStatistic objects by certain group and by
	 * particular test
	 * 
	 * @return List of TestStatistic objects
	 */
	List<TestStatistic> getTestStatisticByGroupByTest(int groupId, int testId);

	/**
	 * Counts total user result by test.
	 * 
	 * @return user result by certain test
	 */
	float getUserResultByTest(int userId, int testId);

}
