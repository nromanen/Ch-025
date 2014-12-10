package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.TestStatisticDao;
import com.softserve.entity.TestStatistic;

@Repository
public class TestStatisticDaoImpl implements TestStatisticDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(BlockDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public void addTestStatistic(TestStatistic testStatistic) {
		LOG.debug("Add testStatistic");
		entityManager.persist(testStatistic);
	}

	@Override
	public List<TestStatistic> getTestStatisticByUserByTest(int userId,
			int testId) {
		LOG.debug("Get TestStatistic by user and by test");
		String stringQuery = "FROM TestStatistic ts WHERE ts.userId = :userId AND ts.testId = :testId";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("userId", userId);
		query.setParameter("testId", testId);
		ArrayList<TestStatistic> tsList = (ArrayList<TestStatistic>) query
				.getResultList();
		return tsList;
	}
	
	@Override
	public List<TestStatistic> getTestStatisticByGroupByTest(int groupId,
			int testId) {
		LOG.debug("Get TestStatistic by group and by test");
		String stringQuery = "FROM TestStatistic ts WHERE ts.groupId = :groupId AND ts.testId = :testId";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("groupId", groupId);
		query.setParameter("testId", testId);
		ArrayList<TestStatistic> tsList = (ArrayList<TestStatistic>) query
				.getResultList();
		return tsList;
	}

	@Override
	public float getUserResultByTest(int userId, int testId) {
		LOG.info("Count users result by certain test");
		ArrayList<TestStatistic> tsList = (ArrayList<TestStatistic>) getTestStatisticByUserByTest(
				userId, testId);
		if (tsList.size() != 0) {
			int maxResult = 0;
			int userResult = 0;
			for (TestStatistic testStat : tsList) {
				try {
				String[] marks = testStat.getResult().split("of");
				userResult += Integer.parseInt(marks[0]);
				maxResult += Integer.parseInt(marks[1]);
				} catch (NumberFormatException e1) {
					LOG.info("Problems with parsing String in getUserResultByTest method");
					LOG.info(e1.toString());
				} catch (ArrayIndexOutOfBoundsException e2) {
					LOG.info("Problems with parsing String in getUserResultByTest method");
					LOG.info(e2.toString());
				}
			}
			if (maxResult != 0) {
				return ((float) userResult / (float) maxResult);
			}
		}
		return 0;
	}


}
