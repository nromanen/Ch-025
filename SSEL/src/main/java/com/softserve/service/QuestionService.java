package com.softserve.service;

import com.softserve.entity.Question;


public interface QuestionService {

	Question addQuestion(Question question);

	Question getQuestionById(int id);
}
