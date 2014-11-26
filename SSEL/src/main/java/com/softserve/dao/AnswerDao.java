package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Answer;

/**
 * 
 * @author Anatoliy
 *
 */
public interface AnswerDao {
	/**
	 * Add new answer
	 * @param answer answer to add
	 * @return added answer
	 */
	Answer addAnswer(Answer newAnswer);
	/**
	 * Update existing answer
	 * @param updatedAnswer updated answer
	 * @return updated answer
	 */
	Answer updateAnswer(Answer updatedAnswer);
	/**
	 * Mark answer as deleted
	 * @param answerId unique answer identifier
	 * @param deleted true - mark as deleted, false - restore
	 */
	void setDeleted(int answerId, boolean deleted);
	/**
	 * Set answer right/wrong
	 * @param answerId unique answer identifier
	 * @param right true - answer is right, false - otherwise
	 */
	void setAnswerRight(int answerId, boolean right);
	/**
	 * Returns answer by id
	 * @param answerId unique answer identifier
	 * @return answer if exists, null otherwise
	 */
	Answer getAnswerById(int answerId);
	/**
	 * Return answers by question
	 * @param questionId unique question identifier
	 * @return list of answer
	 */
	List<Answer> getAnswersByQuestion(int questionId);
	/**
	 * Return correct answers for question	
	 * @param questionId unique question identifier
	 * @return list of answers
	 */
	List<Answer> getCorrectAnswersByQuestion(int questionId);
	/**
	 * Return value of mark for each correct answer for question
	 * @param questionId unique question identifier
	 * @param questionValue mark for question
	 * @return value of mark
	 */
	double getMarkForEachAnswer(int questionId, double questionMarkValue);
}
