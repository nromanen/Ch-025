package com.softserve.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.form.Registration;
import com.softserve.service.UserService;

@Component
public class RegistrationValidation implements Validator {

	private static final String NAME_PATTERN = "[A-ZÀ-ß²¯ª]{1}[A-ZÀ-ß²¯ªa-zà-ÿ³¿º-]{1,30}";
	private static final String EMAIL_PATTERN = "[A-Za-z0-9_\\.-]{1,30}@[A-Za-z0-9_\\.-]{1,30}";

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
				"Field first name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "",
				"Field last name is required.");
		Registration registration = (Registration) target;

		if (!registration.getFirstName().matches(NAME_PATTERN)) {
			errors.rejectValue("firstName", "", "Wrong first name");
		}
		if (!registration.getLastName().matches(NAME_PATTERN)) {
			errors.rejectValue("lastName", "", "Wrong last name");
		}

		if (userService.isExist(registration.getEmail())) {
			errors.rejectValue("email", "", "email is exests");
		}

		if (!(registration.getPassword().equals(registration
				.getConfirmPassword()))) {
			errors.rejectValue("password", "", "Password doesn't match");
		}

		if (!registration.getEmail().matches(EMAIL_PATTERN)) {
			errors.rejectValue("email", "", "Wrong email");
		}
	}

}
