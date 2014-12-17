package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Question;

/**
 * Specify question data access object
 *
 * @author Ivan
 *
 */
public interface QuestionDao {

	/**
	 * Add new question into test
	 *
	 * @param question
	 *            it's question to add
	 * @return added question
	 */
	Question addQuestion(Question question);

	/**
	 * Return question by id
	 *
	 * @param id
	 *            unique question identifier
	 * @return question if exists, null - otherwise
	 */
	Question getQuestionById(int id);

	/**
	 * Return question by id
	 *
	 * @param id
	 *            test identifier
	 * @return list of questions if exists, null - otherwise
	 */
	List<Question> getQuestionsByTestId(int id);

	/**
	 * Return all questions
	 *
	 * @return list of questions
	 */
	List<Question> getAllQuestions();

	/**
	 * Return all deleted questions
	 *
	 * @return list of deleted questions if exists, null - otherwise
	 */
	List<Question> getAllDeletedQuestions();

	/**
	 * Update question
	 *
	 * @param question
	 *            it's question to update
	 * @return updated question
	 */
	Question updateQuestion(Question question);

	/**
	 * Mark question as deleted
	 *
	 * @param questionId
	 *            unique question identifier
	 * @param deleted
	 *            true - mark as deleted, false - restore
	 */
	void setQuestionDeleted(Question question, boolean deleted);

}
