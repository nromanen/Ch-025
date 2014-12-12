package com.softserve.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.dao.QuestionDao;
import com.softserve.entity.Question;
import com.softserve.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired 
	private QuestionDao questionDao;
	
	public QuestionServiceImpl() {

	}

	@Transactional
	@Override
	public Question addQuestion(Question newQuestion) {
		return questionDao.addQuestion(newQuestion);
	}

	@Transactional
	@Override
	public Question updateQuestion(Question updatedQuestion) {
		return questionDao.updateQuestion(updatedQuestion);
	}

	@Override
	public void deleteQuestion(int questionId) {
		questionDao.setDeleted(questionId, true);
	}

	@Override
	public void restoreQuestion(int questionId) {
		questionDao.setDeleted(questionId, false);
	}

	@Override
	public Question getQuestionById(int questionId) {
		return questionDao.getQuestionById(questionId);
	}

	@Override
	public List<Question> getAllQuestionsByTest(int testId) {
		return questionDao.getAllQuestionsByTest(testId);
	}
	
}
