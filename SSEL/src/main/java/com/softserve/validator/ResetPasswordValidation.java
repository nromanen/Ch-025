package com.softserve.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.form.ResetPassword;
import com.softserve.service.UserService;

@Component
public class ResetPasswordValidation implements Validator {

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
	}

}
