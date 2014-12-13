package com.softserve.service;

import java.util.List;

import com.softserve.entity.Option;
import com.softserve.entity.Question;

/**
 * Implements QuestionDao
 * @author Ivan
 *
 */
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
	 * Return all questions for test with id
	 * @return list of questions if exists, null - otherwise
	 */
	List<Question> getQuestionsByTestId(int id);

	/**
	 * Return all questions
	 * @return list of questions if exists, null - otherwise
	 */
	List<Question> getAllQuestions();

	/**
	 * Return all deleted questions
	 * @return list of deleted questions if exists, null - otherwise
	 */
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

	/**
	 * Return array[2] where [0] mark for user, [1] - mark for question
	 * @param questionId unique question identifier
	 * @param options list of user answers for question
	 */
	double[] getUserMarkByQuestion(int questionId, List<Option> options);

	/**
	 * Check answer is right
	 * @param question question to check
	 * @param answer answer to question
	 * @return  true if answer is right or false - answer wrong
	 */
	boolean checkAnswer(Question question, String answer);

	/**
	 * Return right answers for question
	 * @param question question to check
	 * @return list with answers
	 */
	List<String> getRightAnswers(Question question);
}
