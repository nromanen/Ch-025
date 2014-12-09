package com.softserve.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.entity.Test;

/**
 * Validate test
 * 
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"name.required", "Name field is missing");
	}

}
