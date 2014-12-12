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
	@Transactional
	public List<Question> getQuestionsByTestId(int id) {
		return questionDao.getQuestionsByTestId(id);
	}

	@Override
	@Transactional
	public List<Question> getAllQuestions() {
		return questionDao.getAllQuestions();
	}

	@Override
	@Transactional
	public List<Question> getAllDeletedQuestions() {
		return questionDao.getAllDeletedQuestions();
	}

	@Transactional
	@Override
	public Question updateQuestion(Question question) {
		return questionDao.updateQuestion(question);
	}

	@Override
	@Transactional
	public void restoreQuestion(Question question) {
		questionDao.setQuestionDeleted(question, false);
	}

	@Override
	public double[] getUserMarkByQuestion(int questionId,
			List<Option> userOptions) {
		Question question = getQuestionById(questionId);
		double answerMark = 0;
		double mark = 0;
		List<Option> options = question.getQuestion().getOptions();
		answerMark = question.getMark() / options.size();
		for (int i = 0; i < userOptions.size(); i++) {
			if ((userOptions.get(i).isCorrect() ^ options.get(i).isCorrect())) {
				mark -= answerMark;
			} else {
				mark += answerMark;
			}
		}
		if (mark < 0) {
			mark = 0;
		}
		return new double[] { mark, question.getMark() };
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
