package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.TestDao;
import com.softserve.entity.Test;

@Repository
public class TestDaoImpl implements TestDao{
	private static final Logger LOG = LoggerFactory
			.getLogger(TestDaoImpl.class);
	
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;
	
	public TestDaoImpl() {
		
	}
	
	@Override
	public Test addTest(Test newTest) {
		LOG.debug("Add new test with id {}", newTest.getId());
		entityManager.persist(newTest);
		return newTest;
	}

	@Override
	public Test updateTest(Test updatedTest) {
		LOG.debug("Add new test with id {}", updatedTest.getId());
		return entityManager.merge(updatedTest);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Test getTestById(int testId) {
		LOG.debug("Get test by id {}", testId);
		Query query = entityManager.createQuery("FROM Test t INNER JOIN FETCH t.block WHERE t.id = :id")
				.setParameter("id", testId);
		List<Test> test = query.getResultList();
		return (test.size() == 0) ? null : test.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getTestsByBlockId(int blockId) {
		Query query = entityManager.createQuery("FROM Test t INNER JOIN FETCH t.block "
				+ "WHERE t.block.id = :id and t.isDeleted = :val")
				.setParameter("id", blockId)
				.setParameter("val", false);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getAllTests() {
		LOG.debug("");
		Query query = entityManager.createQuery("FROM Test t WHERE t.isDeleted = :val")
				.setParameter("val", false);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getAliveTests() {
		Query query = entityManager.createQuery("FROM Test t WHERE t.isDeleted = :del and t.alive = :alive")
				.setParameter("alive", true)
				.setParameter("del", false);
		return query.getResultList();
	}
	
	@Override
	public void setDeletedTest(int testId, boolean deleted) {
		Query query = entityManager.createQuery("UPDATE Test t SET t.isDeleted = :deleted WHERE t.id = :id")
				.setParameter("deleted", deleted)
				.setParameter("id", testId);
		if (query.executeUpdate() != 0) {
			LOG.debug("Update successful {}",testId);
		} else {
			LOG.debug("Update failed {}",testId);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> getTestsBySubject(int subjectId) {
		Query query = entityManager.createQuery("SELECT t FROM Test t INNER JOIN FETCH t.block "
				+ "WHERE t.block.subject.id = :id and t.isDeleted = :val")
				.setParameter("id", subjectId)
				.setParameter("val", false);
		return query.getResultList();
	}
	
	
	

}
