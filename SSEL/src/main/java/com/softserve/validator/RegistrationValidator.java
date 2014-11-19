package com.softserve.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.form.Registration;
import com.softserve.service.UserService;
import com.softserve.util.Patterns;

/**
 * Class for validation registration form
 * 
 * @author Khomyshyn Roman
 */

@Component
public class RegistrationValidator implements Validator {

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

		if (!registration.getFirstName().matches(Patterns.NAME_PATTERN)) {
			errors.rejectValue("firstName", "dataerror.firstname");
		}
		if (!registration.getLastName().matches(Patterns.NAME_PATTERN)) {
			errors.rejectValue("lastName", "dataerror.lastname");
		}

		if (userService.isExist(registration.getEmail())) {
			errors.rejectValue("email", "dataerror.email_exist");
		}

		if (!(registration.getPassword().equals(registration
				.getConfirmPassword()))) {
			errors.rejectValue("confirmPassword",
					"dataerror.passwords_do_not_match");
		}

		if (!registration.getEmail().matches(Patterns.EMAIL_PATTERN)) {
			errors.rejectValue("email", "dataerror.email_example");
		}

		if (!registration.getPassword().matches(Patterns.PASSWORD_PATTERN)) {
			errors.rejectValue("password", "dataerror.password_pattern");
		}

		if (registration.getPassword().length() < Patterns.PASSWORD_MIN_LENGTH) {
			errors.rejectValue("password", "dataerror.minimum_4_characters");
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
