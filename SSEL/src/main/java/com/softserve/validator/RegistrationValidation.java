package com.softserve.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.form.Registration;
import com.softserve.service.UserService;

/**
 * Class for validation registration form
 * @author Khomyshyn Roman
 */

@Component
public class RegistrationValidation implements Validator {

	private static final String NAME_PATTERN = "[A-ZА-ЯІЇЄ]{1}[A-ZА-ЯІЇЄa-zа-яіїє]{1,30}";
	private static final String EMAIL_PATTERN = "[A-Za-z0-9_\\.-]{1,30}@[A-Za-z0-9_\\.-]{1,30}";
	private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[//@/./&/!#/$%/^/*/?])(?!.*\\s).{4,20}$";

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
			errors.rejectValue("confirmPassword",
					"dataerror.passwords_do_not_match");
		}

		if (!registration.getEmail().matches(EMAIL_PATTERN)) {
			errors.rejectValue("email", "dataerror.email_example");
		}

		if (!registration.getPassword().matches(PASSWORD_PATTERN)) {
			errors.rejectValue("password", "dataerror.password_pattern");
		}

		if (registration.getPassword().length() < 4) {
			errors.rejectValue("password", "dataerror.minimum_4_characters");
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
