package com.softserve.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.dao.AnswerDao;
import com.softserve.entity.Answer;
import com.softserve.entity.Question;
import com.softserve.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{
	@Autowired
	private AnswerDao answerDao;
	
	public AnswerServiceImpl() {

	}

	@Transactional
	@Override
	public Answer addAnswer(Answer newAnswer) {
		return answerDao.addAnswer(newAnswer);
	}
	
	@Transactional
	@Override
	public Answer updateAnswer(Answer updatedAnswer) {
		return answerDao.updateAnswer(updatedAnswer);
	}

	@Override
	public Answer getAnswerById(int answerId) {
		return answerDao.getAnswerById(answerId);
	}

	@Override
	public List<Answer> getAnswersByQuestion(int questionId) {
		return answerDao.getAnswersByQuestion(questionId);
	}

	@Override
	public List<Answer> getCorrectAnswersByQuestion(int questionId) {
		return answerDao.getCorrectAnswersByQuestion(questionId);
	}

	@Override
	public void setMarkForEachAnswer(Question question) {
		double answersCount = answerDao.getCorrectAnswersByQuestion(question.getId()).size();
		double answerMark = question.getMark() / answersCount;
		answerDao.setMarkForEachAnswer(question.getId(), answerMark);
	}
	
	

}
