package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Category;
import com.softserve.entity.Question;

public interface QuestionDao {

	Question addQuestion(Question question);

	Question getQuestionById(int id);

	List<Question> getQuestionsByTestId(int id);

	List<Question> getAllQuestions();

	List<Question> getAllDeletedQuestions();

	Question updateQuestion(Question question);

	void setQuestionDeleted(Question question, boolean deleted);

}
