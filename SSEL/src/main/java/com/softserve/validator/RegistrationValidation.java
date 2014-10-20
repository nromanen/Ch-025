package com.softserve.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.form.Registration;
import com.softserve.service.UserService;

@Component()
public class RegistrationValidation implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Registration.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "",
				"Field password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"", "Field password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "",
				"Field firstName is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "",
				"Field lastName is required.");
		Registration registration = (Registration) target;

		if (userService.isExist(registration.getEmail())) {
			errors.rejectValue("email", "", "This email is exits");
		}

		if (!(registration.getPassword().equals(registration
				.getConfirmPassword()))) {
			errors.rejectValue("password", "notmatch.password");
		}
	}

}
