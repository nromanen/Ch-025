package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.QuestionDao;
import com.softserve.entity.Question;

/**
 * Implements QuestionDao
 * @author Anatoliy
 *
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
	private static final Logger LOG = LoggerFactory
			.getLogger(TestDaoImpl.class);
	
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	public QuestionDaoImpl() {
	}
	
	@Override
	public Question addQuestion(Question newQuestion) {
		LOG.debug("Add new question {}", newQuestion.getId());
		return entityManager.merge(newQuestion);
	}

	@Override
	public Question updateQuestion(Question updatedQuestion) {
		LOG.debug("Update new question {}", updatedQuestion.getId());
		return entityManager.merge(updatedQuestion);
	}

	@Override
	public void setDeleted(int questionId, boolean deleted) {
		Query query = entityManager.createQuery("UPDATE Question q SET q.isDeleted = :deleted WHERE q.id = :id")
				.setParameter("deleted", deleted)
				.setParameter("id", questionId);
		if (query.executeUpdate() != 0) {
			LOG.debug("Delete successful {}",questionId);
		} else {
			LOG.debug("Delete failed {}");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Question getQuestionById(int questionId) {
		Query query = entityManager.createQuery("FROM Question q WHERE q.id = :id")
				.setParameter("id", questionId);
		List<Question> question = query.getResultList();
		return (question.size() == 0) ? null : question.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestionsByTest(int testId) {
		Query query = entityManager.createQuery("FROM Question q WHERE q.test.id = :id and q.isDeleted = :del")
				.setParameter("id", testId)
				.setParameter("del", false);
		return query.getResultList();
	}

	@Override
	public double getMarkForEachQuestion(int testId) {
		Query query = entityManager.createQuery("SELECT count(q.id) FROM Question q WHERE "
				+ "q.test.id = :id and q.isDeleted = :del")
				.setParameter("del", false)
				.setParameter("id", testId);
		Double d = (Double) query.getSingleResult();
		return (d != null) ? 100/d : 0;
	}

}
