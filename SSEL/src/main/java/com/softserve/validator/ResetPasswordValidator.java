package com.softserve.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.form.ResetPassword;
import com.softserve.service.UserService;
import com.softserve.util.Patterns;

@Component
public class ResetPasswordValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return ResetPassword.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"dataerror.field_required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"dataerror.field_required");

		ResetPassword resetPassword = (ResetPassword) target;

		if (!(resetPassword.getPassword().equals(resetPassword
				.getConfirmPassword()))) {
			errors.rejectValue("confirmPassword",
					"dataerror.passwords_do_not_match");
		}

		if (userService.getUserByKey(resetPassword.getKey()) == null) {
			errors.rejectValue("key", "dataerror.key_not_found");
		}

		if (!resetPassword.getPassword().matches(Patterns.PASSWORD_PATTERN)) {
			errors.rejectValue("password", "dataerror.password_pattern");
		}

		if (resetPassword.getPassword().length() < Patterns.PASSWORD_MIN_LENGTH) {
			errors.rejectValue("password", "dataerror.minimum_4_characters");
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
