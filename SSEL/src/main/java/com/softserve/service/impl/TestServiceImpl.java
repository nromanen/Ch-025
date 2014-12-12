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

	@Override
	public void deleteTest(Test test) {
		testDao.setDeletedTest(test.getId(), true);
	}

	@Override
	public void restoreTest(Test test) {
		testDao.setDeletedTest(test.getId(), false);
	}

	@Override
	public List<Test> getTestByBlock(int blockId) {
		return testDao.getTestByBlockId(blockId);
	}

	@Override
	public List<Test> getTestBySubject(int subjectId) {
		return testDao.getTestsBySubject(subjectId);
	}

}
