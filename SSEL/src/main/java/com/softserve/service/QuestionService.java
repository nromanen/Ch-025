package com.softserve.service;

import java.util.List;

import com.softserve.entity.Question;

public interface QuestionService {
	/**
	 * Add question 
	 * @param newQuestion question to add
	 * @return added question
	 */
	Question addQuestion(Question newQuestion);
	/**
	 * Update question
	 * @param updatedQuestion updated question 
	 * @return updated question
	 */
	Question updateQuestion(Question updatedQuestion);
	/**
	 * Mark question as deleted
	 * @param question question to mark
	 */
	void deleteQuestion(Question question);
	/**
	 * Restore deleted question
	 * @param question deleted question
	 */
	void restoreQuestion(Question question);
	
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
}
