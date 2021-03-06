package com.softserve.service;

import java.util.List;

import com.softserve.entity.Test;

public interface TestService {
	/**
	 * Add new test
	 * @param newTest test to add
	 * @return added test
	 */
	Test addTest(Test newTest);
	/**
	 * Update test
	 * @param updatedTest updated tes
	 * @return updated test
	 */
	Test updateTest(Test updatedTest);
	/**
	 * Return test by id
	 * @param testId unique test identifier
	 * @return test
	 */
	Test getTestById(int testId);
	/**
	 * Mark test as deleted
	 * @param test test to mark
	 */
	void deleteTest(int testId);
	/**
	 * Restore deleted test
	 * @param test deleted test
	 */
	void restoreTest(int testId);
	/**
	 * Return tests for some block
	 * @param blockId unique block identifier
	 * @return list of tests
	 */
	List<Test> getTestsByBlock(int blockId);
	/**
	 * Get tests by subject
	 * @param subjectId unique subject identifier
	 * @return list of tests
	 */
	List<Test> getTestBySubject(int subjectId);
	/**
	 * Get active tests
	 * @return list of tests
	 */
	List<Test> getActiveTests();
}
