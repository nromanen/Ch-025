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

	private static final String NAME_PATTERN = "[A-Z]{1}[a-z]{1,30}";
	private static final String EMAIL_PATTERN = "[A-Za-z0-9_\\.-]{1,30}@[A-Za-z0-9_\\.-]{1,30}";

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Registration.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"dataerror.field_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"dataerror.field_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"dataerror.field_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"dataerror.field_required");
		Registration registration = (Registration) target;

		if (!registration.getFirstName().matches(NAME_PATTERN)) {
			errors.rejectValue("firstName", "dataerror.firstname");
		}
		if (!registration.getLastName().matches(NAME_PATTERN)) {
			errors.rejectValue("lastName", "dataerror.lastname");
		}

		if (userService.isExist(registration.getEmail())) {
			errors.rejectValue("email", "dataerror.email_exist");
		}

		if (!(registration.getPassword().equals(registration
				.getConfirmPassword()))) {
			errors.rejectValue("confirmPassword", "", "Password doesn't match");
		}

		if (!registration.getEmail().matches(EMAIL_PATTERN)) {
			errors.rejectValue("email", "dataerror.email_example");
		}
	}

}
