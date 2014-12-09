/**
 * 
 */
package com.softserve.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.softserve.entity.Subject;

/**
 * @author hash
 *
 */
@Component
public class SubjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> classObject) {
		return Subject.class.equals(classObject);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"name.required", "Name field is missing");
	}

}
