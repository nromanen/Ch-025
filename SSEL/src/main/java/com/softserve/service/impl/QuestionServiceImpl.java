package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.QuestionDao;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Override
	@Transactional
	public Question addQuestion(Question question) {
		return questionDao.addQuestion(question);
	}

	@Override
	@Transactional
	public Question getQuestionById(int id) {
		return questionDao.getQuestionById(id);
	}

	@Override
	@Transactional
	public void deleteQuestion(Question question) {
		questionDao.setQuestionDeleted(question, true);

	}

	@Override
	public List<Question> getQuestionsByTestId(int id) {
		return questionDao.getQuestionsByTestId(id);
	}

	@Override
	public List<Question> getAllQuestions() {
		return questionDao.getAllQuestions();
	}

	@Override
	public List<Question> getAllDeletedQuestions() {
		return questionDao.getAllDeletedQuestions();
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionDao.updateQuestion(question);
	}

	@Override
	public void restoreQuestion(Question question) {
		questionDao.setQuestionDeleted(question, false);
	}

	@Override
	public double getUserMarkByQuestion(int questionId, List<Option> userOptions) {
		Question question = getQuestionById(questionId);
		int correctAnswers = 0;
		double answerMark = 0;
		double mark = 0;
		boolean f;
		List<Option> options = question.getQuestion().getOptions();

		for (Option option : options) {
			if (option.isCorrect()) {
				correctAnswers++;
			}
		}

		if (correctAnswers > 0) {
			answerMark = question.getMark() / correctAnswers;

			for (Option userOption : userOptions) {
				if (userOption.isCorrect()) {

					f = checkAnswer(question, userOption.getValue());

					if (f) {
						mark += answerMark;
					} else {
						// When user set wrong answer; 2 is coefficient
						mark -= answerMark / 2;
					}
				}
			}
			if (mark < 0) {
				mark = 0;
			}
		} else {
			mark = 0;
		}
		return mark;
	}

	public boolean checkAnswer(Question question, String answer) {
		List<Option> options = question.getQuestion().getOptions();
		for (Option option : options) {
			if (option.isCorrect()) {
				if (answer.equals(option.getValue())) {
					return true;
				}
			}

		}
		return false;
	}

}
