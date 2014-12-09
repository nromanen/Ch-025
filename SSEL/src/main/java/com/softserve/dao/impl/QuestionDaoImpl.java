package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.QuestionDao;
import com.softserve.entity.Question;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory
			.getLogger(QuestionDaoImpl.class);

	/** The entity manager. */
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	@Transactional
	public Question addQuestion(Question question) {
		LOG.debug("Add question {}", question.getId());
		entityManager.persist(question);
		return question;
	}

	@Override
	@Transactional
	public Question getQuestionById(int id) {
		LOG.debug("Get question with id = {}", id);
		return entityManager.find(Question.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionsByTestId(int id) {
		LOG.debug("Get question with id = {}", id);
		Query query = entityManager
				.createQuery("SELECT q FROM questions q WHERE q.id_Test = :val");
		query.setParameter("val", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllQuestions() {
		LOG.debug("Get all questions");
		return entityManager.createQuery("FROM questions").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getAllDeletedQuestions() {
		LOG.debug("Get question with id");
		Query query = entityManager
				.createQuery("SELECT q FROM questions q WHERE q.deleted = :val");
		query.setParameter("val", true);
		return query.getResultList();
	}

	@Override
	public Question updateQuestion(Question question) {
		LOG.debug("Update question with id = {}", question.getId());
		return entityManager.merge(question);
	}

	@Override
	public void setQuestionDeleted(Question question, boolean deleted) {
		Query query = entityManager
				.createQuery("UPDATE questions q SET q.isDeleted = :del WHERE q.id = :id");
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
