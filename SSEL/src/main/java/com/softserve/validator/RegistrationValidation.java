package com.softserve.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softserve.form.Registration;
import com.softserve.service.UserService;

public class RegistrationValidation implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Registration.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Field password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required.confirmPassword", "Field password is required.");
		Registration registration = (Registration) target;

//		if (userService.isExist(registration.getEmail())){
//			
//		}
		
//		if (!(registration.getPassword().equals(registration
//				.getConfirmPassword()))) {
//			errors.rejectValue("password", "notmatch.password");
//		}

//		if (personService.isExist(registration.getEmail()) > 0) {
//			errors.rejectValue("email", "exist.email");
//		}
	}

}
