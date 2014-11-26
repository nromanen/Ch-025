package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Question;


/**
 * Specify question data access object
 * @author Anatoliy
 *
 */
public interface QuestionDao {
	/**
	 * Add new question into test
	 * @param newQuestion question to add
	 * @return added question
	 */
	Question addQuestion(Question newQuestion);
	/**
	 * Update question
	 * @param updatedQuestion question to update
	 * @return updated question
	 */
	Question updateQuestion(Question updatedQuestion);
	/**
	 * Mark question as deleted
	 * @param questionId unique question identifier
	 * @param deleted true - mark as deleted, false - restore
	 */
	void setDeleted(int questionId, boolean deleted);
	/**
	 * Return question by id
	 * @param questionId unique question identifier
	 * @return question if exists, null - otherwise
	 */
	Question getQuestionById(int questionId);
	/**
	 * Return all questions for test
	 * @return list of questions
	 */
	List<Question> getAllQuestionsByTest(int testId);
	/**
	 * Return value of mark that gives for all questions (sum along question return 100)
	 * @param testId unique test identifier
	 * @return values of mark
	 */
	double getMarkForEachQuestion(int testId);
	
}
