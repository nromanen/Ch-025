package com.softserve.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.entity.Test;
import com.softserve.util.Patterns;
/**
 * Validate test
 * @author Anatoliy
 *
 */
@Component
public class TestValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Test.class.equals(arg0);
	}

	@Override
	public void validate(Object toValidate, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "test.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "test.description.required");
		Test test = (Test) toValidate;
		if (!test.getName().matches(Patterns.TEST_PATTERN)) {
			errors.rejectValue("name", "test.name.required");
		}
	}

}
