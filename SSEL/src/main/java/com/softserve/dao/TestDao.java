package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Test;

/**
 * Specify test data access object functionality
 * @author Anatoliy
 *
 */
public interface TestDao {
	/**
	 * Add new test into test list
	 * @param newTest test to add
	 * @return added test
	 */
	Test addTest(Test newTest);
	/**
	 * 
	 * @param updatedTest
	 * @return
	 */
	Test updateTest(Test updatedTest);
	/**
	 * Return test by id
	 * @param testId unique test identifier
	 * @return test if exist, null otherwise
	 */
	Test getTestById(int testId);
	/**
	 * Return tests for some module
	 * @param blockId unique module identifier
	 * @return list of test for block
	 */
	List<Test> getTestByBlockId(int blockId);
	/**
	 * Return all test which isn't deleted
	 * @return
	 */
	List<Test> getAllTests();
	/**
	 * Return tests which is alive now
	 * @return list of alive tests
	 */
	List<Test> getAliveTests();
	/**
	 * Mark test as deleted
	 * @param testId unique test identifier
	 * @param deleted true - mark as deleted, false - restore
	 */
	void setDeletedTest(int testId, boolean deleted);
}
