package com.softserve.service;

import java.util.List;

import com.softserve.entity.Option;
import com.softserve.entity.Question;

public interface QuestionService {

	Question addQuestion(Question question);

	void deleteQuestion(Question question);

	Question getQuestionById(int id);

	List<Question> getQuestionsByTestId(int id);

	List<Question> getAllQuestions();

	List<Question> getAllDeletedQuestions();

	Question updateQuestion(Question question);

	void restoreQuestion(Question question);

	double getUserMark(int questionId, List<Option> option);

}
