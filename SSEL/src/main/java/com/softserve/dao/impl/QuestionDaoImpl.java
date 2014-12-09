package com.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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



}
