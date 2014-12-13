package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.QuestionDao;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.service.QuestionService;

/**
 * Implements QuestionDao
 * @author Ivan
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;


	/**
	 * @see com.softserve.service.QuestionService#addQuestion(com.softserve.entity.Question)
	 */
	@Override
	@Transactional
	public Question addQuestion(Question question) {
		return questionDao.addQuestion(question);
	}

	/**
	 * @see com.softserve.service.QuestionService#getQuestionById(int)
	 */
	@Override
	@Transactional
	public Question getQuestionById(int id) {
		return questionDao.getQuestionById(id);
	}

	/**
	 * @see com.softserve.service.QuestionService#deleteQuestion(com.softserve.entity.Question)
	 */
	@Override
	@Transactional
	public void deleteQuestion(Question question) {
		questionDao.setQuestionDeleted(question, true);

	}

	/**
	 * @see com.softserve.service.QuestionService#getQuestionsByTestId(int)
	 */
	@Override
	@Transactional
	public List<Question> getQuestionsByTestId(int id) {
		return questionDao.getQuestionsByTestId(id);
	}

	/**
	 * @see com.softserve.service.QuestionService#getAllQuestions()
	 */
	@Override
	@Transactional
	public List<Question> getAllQuestions() {
		return questionDao.getAllQuestions();
	}

	/**
	 * @see com.softserve.service.QuestionService#getAllDeletedQuestions()
	 */
	@Override
	@Transactional
	public List<Question> getAllDeletedQuestions() {
		return questionDao.getAllDeletedQuestions();
	}

	/**
	 * @see com.softserve.service.QuestionService#updateQuestion(com.softserve.entity.Question)
	 */
	@Transactional
	@Override
	public Question updateQuestion(Question question) {
		return questionDao.updateQuestion(question);
	}

	/**
	 * @see com.softserve.service.QuestionService#restoreQuestion(com.softserve.entity.Question)
	 */
	@Override
	@Transactional
	public void restoreQuestion(Question question) {
		questionDao.setQuestionDeleted(question, false);
	}

	/**
	 * @see com.softserve.service.QuestionService#getUserMarkByQuestion(int, java.util.List)
	 */
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

	/**
	 * @see com.softserve.service.QuestionService#checkAnswer(com.softserve.entity.Question, java.lang.String)
	 */
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
