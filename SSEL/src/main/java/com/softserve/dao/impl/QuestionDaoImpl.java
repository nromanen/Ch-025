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
 *
 * @author Ivan
 *
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory
			.getLogger(QuestionDaoImpl.class);

	/** The entity manager. */
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	/**
	 * @see com.softserve.dao.QuestionDao#addQuestion(Question)
	 */
	@Override
	public Question addQuestion(Question question) {
		LOG.debug("Add question {}", question.getId());
		entityManager.persist(question);
		return question;
	}

	/**
	 * @see com.softserve.dao.QuestionDao#getQuestionById(int)
	 */
	@Override
	public Question getQuestionById(int id) {
		LOG.debug("Get question with id = {}", id);
		return entityManager.find(Question.class, id);
	}

	/**
	 * @see com.softserve.dao.QuestionDao#getQuestionsByTestId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionsByTestId(int id) {
		LOG.debug("Get question with test id = {}", id);
		Query query = entityManager
				.createQuery("FROM Question q WHERE q.test.id = :val and q.isDeleted = :del");
		query.setParameter("val", id);
		query.setParameter("del", false);
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	/**
	 * @see com.softserve.dao.QuestionDao#getAllQuestions()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestions() {
		LOG.debug("Get all questions");
		Query query = entityManager
				.createQuery("FROM Question q WHERE q.isDeleted = :val");
		query.setParameter("val", false);
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllDeletedQuestions() {
		LOG.debug("Get all deleted questions");
		Query query = entityManager
				.createQuery("FROM Question q WHERE q.isDeleted = :val");
		query.setParameter("val", true);
		return query.getResultList().isEmpty() ? null : query.getResultList();
	}

	/**
	 * @see com.softserve.dao.QuestionDao#updateQuestion(Question)
	 */
	@Override
	public Question updateQuestion(Question question) {
		LOG.debug("Update question with id = {}", question.getId());
		entityManager.merge(question);
		return question;
	}

	/**
	 * @see com.softserve.dao.QuestionDao#setQuestionDeleted(int, boolean)
	 */
	@Override
	public void setQuestionDeleted(Question question, boolean deleted) {
		Query query = entityManager
				.createQuery("UPDATE Question q SET q.isDeleted = :del WHERE q.id = :id");
		query.setParameter("id", question.getId());
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted = {} question(id = {})", deleted,
					question.getId());
		} else {
			LOG.warn("Tried to delete question(id = {})", question.getId());
		}

	}

}
