package com.softserve.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.dao.TestDao;
import com.softserve.entity.Test;
import com.softserve.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;
	
	public TestServiceImpl() {
	}
	
	@Transactional
	@Override
	public Test addTest(Test newTest) {
		return testDao.addTest(newTest);
	}

	@Transactional
	@Override
	public Test updateTest(Test updatedTest) {
		return testDao.updateTest(updatedTest);
	}

	@Override
	public Test getTestById(int testId) {
		return testDao.getTestById(testId);
	}
	
	@Transactional
	@Override
	public void deleteTest(int testId) {
		testDao.setDeletedTest(testId, true);
	}

	@Transactional
	@Override
	public void restoreTest(int testId) {
		testDao.setDeletedTest(testId, false);
	}

	@Override
	public List<Test> getTestsByBlock(int blockId) {
		return testDao.getTestsByBlockId(blockId);
	}

	@Override
	public List<Test> getTestBySubject(int subjectId) {
		return testDao.getTestsBySubject(subjectId);
	}

	@Override
	public List<Test> getActiveTests() {
		return testDao.getAliveTests();
	}

}
