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
	@SuppressWarnings("unchecked")
	public List<TestStatistic> getTestStatisticByUserByTest(int userId,
			int testId) {
		LOG.debug("Get TestStatistic by user and by test");
		String stringQuery = "FROM TestStatistic ts WHERE ts.user.id = :userId AND ts.test.id = :testId";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("userId", userId);
		query.setParameter("testId", testId);
		List<TestStatistic> tsList = (List<TestStatistic>) query
				.getResultList();
		return tsList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TestStatistic> getTestStatisticByGroupByTest(int groupId,
			int testId) {
		LOG.debug("Get TestStatistic by group and by test");
		String stringQuery = "FROM TestStatistic ts WHERE ts.group.id = :groupId AND ts.test.id = :testId";
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("groupId", groupId);
		query.setParameter("testId", testId);
		List<TestStatistic> tsList = (List<TestStatistic>) query
				.getResultList();
		return tsList;
	}

	@Override
	public float getUserResultByTest(int userId, int testId) {
		LOG.info("Count users result by certain test");
		ArrayList<TestStatistic> tsList = (ArrayList<TestStatistic>) getTestStatisticByUserByTest(
				userId, testId);
		if (tsList.size() != 0) {
			int totalMaxResult = 0;
			int totalUserResult = 0;
			for (TestStatistic testStat : tsList) {
				totalUserResult += testStat.getUserResult();
				totalMaxResult += testStat.getMaxResult();
			}
			if (totalMaxResult != 0) {
				return ((float) totalUserResult / (float) totalMaxResult);
			}
		}
		return 0f;
	}

}
