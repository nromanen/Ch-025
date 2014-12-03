package com.softserve.service;

import java.util.List;

import com.softserve.entity.Answer;
import com.softserve.entity.Question;

public interface AnswerService {
	/**
	 * Add new answer
	 * @param newAnswer answer to add
	 * @return added answer
	 */
	Answer addAnswer(Answer newAnswer);
	/**
	 * Update answer
	 * @param updateAnswer updated answer
	 * @return updated answer
	 */
	Answer updateAnswer(Answer updateAnswer);
	/**
	 * Return answer by id 
	 * @param answerId unique answer identifier
	 * @return
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
	void setMarkForEachAnswer(Question question);
}
