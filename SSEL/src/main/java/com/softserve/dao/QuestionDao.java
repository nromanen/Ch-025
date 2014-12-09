package com.softserve.dao;

import com.softserve.entity.Question;

public interface QuestionDao {

	Question addQuestion(Question question);

	Question getQuestionById(int id);

}
