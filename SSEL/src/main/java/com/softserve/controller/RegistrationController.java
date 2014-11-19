package com.softserve.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.entity.User;
import com.softserve.form.Registration;
import com.softserve.service.UserService;
import com.softserve.validator.RegistrationValidator;

/**
 * Handle registration request
 * 
 * @author Khomyshyn Roman
 */
@Controller
public class RegistrationController {

	private static final Logger LOG = LoggerFactory
			.getLogger(RegistrationController.class);

	private static final String KEY_VALID = "valid";
	private static final String ATTRIBUTE_REGISTRATION = "registration";

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RegistrationValidator registrationValidator;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String regForm(Model model) {
		LOG.debug("Open registration.jsp");
		model.addAttribute(ATTRIBUTE_REGISTRATION, new Registration());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistration(@Valid final Registration registration,
			BindingResult result, HttpServletRequest request) {
		LOG.debug("Check information from registration form");
		registrationValidator.validate(registration, result);
		if (result.hasErrors()) {
			LOG.debug("Registration form has error");
			return "registration";
		}
		if (registration.isTeacher()) {
			String message = messageSource.getMessage(
					"message.teacher.confirm_registration", new Object[] {},
					LocaleContextHolder.getLocale());
			userService.registrateTeacher(registration, message);
		} else {
			String message = messageSource.getMessage(
					"message.user.confirm_registration", new Object[] {},
					LocaleContextHolder.getLocale());
			String url = request.getRequestURL().toString();
			userService.registrateStudent(registration, url, message);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
	public String confirmEmail(@RequestParam("key") String key) {
		LOG.debug("Student confirm his account");
		User user = userService.getUserByKey(key);
		if (user != null && user.isBlocked()) {
			user.setBlocked(false);
			userService.updateUser(user);
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/isExistsEmail", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> isExistsEmail(
			@RequestParam String email) {
		Map<String, String> map = new HashMap<>();
		map.put(KEY_VALID, Boolean.toString(!userService.isExist(email)));
		return map;
	}
}
