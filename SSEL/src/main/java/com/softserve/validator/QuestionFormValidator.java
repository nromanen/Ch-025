package com.softserve.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.entity.Answer;
import com.softserve.form.QuestionForm;

@Component
public class QuestionFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return QuestionForm.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "question.question",
				"question.required");
		QuestionForm qf = (QuestionForm) arg0;
		if (qf.getQuestion().getMark() == 0) {
			errors.reject("question.mark", "question.mark");
		}
		if (qf.getQuestion().getAnswersCount() <= 0) {
			errors.reject("question.answersCount", "question.answers_count");
		}
		answersValidation(qf.getAnswers(), errors);
	}

	private void answersValidation(List<Answer> answers, Errors errors) {
		for (int i = 0; i < answers.size(); i++) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"answers[i].answer", "answer.required");
		}
	}

}
