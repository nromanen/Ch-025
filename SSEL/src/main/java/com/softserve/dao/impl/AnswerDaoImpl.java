package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.AnswerDao;
import com.softserve.entity.Answer;

/**
 * Implements AnswerDao
 * @author Anatoliy
 *
 */
@Repository
public class AnswerDaoImpl implements AnswerDao{

	private static final Logger LOG = LoggerFactory
			.getLogger(AnswerDaoImpl.class);
	
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	public AnswerDaoImpl() {
	}

	@Override
	public Answer addAnswer(Answer newAnswer) {
		LOG.debug("Add answer {}", newAnswer.getId());
		entityManager.persist(newAnswer);
		return newAnswer;
	}

	@Override
	public Answer updateAnswer(Answer updatedAnswer) {
		LOG.debug("Update answer {}", updatedAnswer.getId());
		return entityManager.merge(updatedAnswer);
	}

	@Override
	public void setDeleted(int answerId, boolean deleted) {
		Query query = entityManager.createQuery("UPDATE Answer a SET a.isDeleted = :deleted WHERE a.id = :id")
				.setParameter("deleted", deleted)
				.setParameter("id", answerId);
		if (query.executeUpdate() != 0) {
			LOG.debug("Delete successful {}",answerId);
		} else {
			LOG.debug("Delete failed {}", answerId);
		}
	}

	@Override
	public void setAnswerRight(int answerId, boolean right) {
		Query query = entityManager.createQuery("UPDATE Answer a SET a.isRight = :right, a.isDeleted = :deleted WHERE a.id = :id")
				.setParameter("deleted", false)
				.setParameter("id", answerId)
				.setParameter("right", right);
		if (query.executeUpdate() != 0) {
			LOG.debug("Delete successful {}",answerId);
		} else {
			LOG.debug("Delete failed {}", answerId);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Answer getAnswerById(int answerId) {
		Query query = entityManager.createQuery("FROM Answer a WHERE a.id = :id")
				.setParameter("id", answerId);
		List<Answer> answer = query.getResultList();
		return (answer.isEmpty()) ? null : answer.get(0); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAnswersByQuestion(int questionId) {
		Query query = entityManager.createQuery("FROM Answer a WHERE a.question.id = :id")
				.setParameter("id", questionId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getCorrectAnswersByQuestion(int questionId) {
		Query query = entityManager.createQuery("FROM Answer a WHERE a.question.id = :id and a.isRight = :right")
				.setParameter("id", questionId)
				.setParameter("right", true);
		return query.getResultList();
	}

	@Override
	public void setMarkForEachAnswer(int questionId, double questionMarkValue) {
		Query query = entityManager.createQuery("UPDATE Answer a SET a.mark = :mark WHERE a.test.id = :id"
				+ "a.question.id = :id and a.isRight = :right")
				.setParameter("id", questionId)
				.setParameter("mark", questionMarkValue)
				.setParameter("right", true);
		if(query.executeUpdate() != 0) {
			LOG.debug("Update answers mark success for test {}", questionId);
		} else {
			LOG.debug("Update answers mark failed for test {}", questionId);
		}
	}
	
}
