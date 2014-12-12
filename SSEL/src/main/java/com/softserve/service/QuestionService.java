package com.softserve.service;

import java.util.List;

import com.softserve.entity.Option;
import com.softserve.entity.Question;

public interface QuestionService {

	/**
	 * Add question
	 * @param question it's question to add
	 * @return added question
	 */
	Question addQuestion(Question question);

	/**
	 * Mark question as deleted
	 * @param question question to mark deleted
	 */
	void deleteQuestion(Question question);

	/**
	 * Return question by id
	 * @param id unique question identifier
	 * @return question if exists, null - otherwise
	 */
	Question getQuestionById(int id);

	/**
	 * Return all questions for test
	 * @return list of questions if exists, null - otherwise
	 */
	List<Question> getQuestionsByTestId(int id);

	List<Question> getAllQuestions();

	List<Question> getAllDeletedQuestions();

	/**
	 * Update question
	 * @param question it's updated question
	 * @return updated question
	 */
	Question updateQuestion(Question question);

	/**
	 * Restore deleted question
	 * @param question deleted question
	 */
	void restoreQuestion(Question question);

	double[] getUserMarkByQuestion(int questionId, List<Option> option);

	boolean checkAnswer(Question question, String answer);

}