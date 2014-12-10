package com.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.QuestionDao;
import com.softserve.dao.impl.QuestionDaoImpl;
import com.softserve.entity.Option;
import com.softserve.entity.Question;
import com.softserve.entity.QuestionText;
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
	public double[] getUserMarkByQuestion(int questionId, List<Option> userOptions) {
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
		return new double[] {mark, question.getMark()};
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

	public static void main(String[] args) {
		System.out.println("hello");
		Question question = new Question();
		question.setTest(33);

		Option o1 = new Option();
		o1.setCorrect(true);
		o1.setValue("option1");

		Option o2 = new Option();
		o2.setCorrect(false);
		o2.setValue("option2");

		List<Option> options = new ArrayList<Option>();
		options.add(o1);
		options.add(o2);

		QuestionText qt = new QuestionText();
		qt.setValue("some value");
		qt.setOptions(options);

		question.setQuestionText(qt);

		QuestionDaoImpl questionService = new QuestionDaoImpl();

		System.out.println(questionService.addQuestion(question));
	}

}
