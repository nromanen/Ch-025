package com.softserve.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.softserve.validator.RegistrationValidation;

/**
 * Handle registration request
 * 
 * @author Khomyshyn Roman
 */
@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private RegistrationValidation registrationValidation;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String regForm(Model model) {
		model.addAttribute("registration", new Registration());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistration(@Valid final Registration registration,
			BindingResult result, HttpServletRequest request) {
		registrationValidation.validate(registration, result);
		if (result.hasErrors()) {
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
			url.replaceAll(request.getServletPath(), "/");
			userService.registrateStudent(registration, url, message);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
	public String confirmEmail(@RequestParam("key") String key) {
		User user = userService.getUserByKey(key);
		if (user != null && user.isBlocked() == true) {
			user.setBlocked(false);
			userService.updateUser(user);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(
			@RequestParam(value = "error", required = false) String error,
			Map<String, Object> map) {
		if (error != null && error.equals("BadCredentialsException")) {
			map.put("error", "BadCredentialsException");
		} else if (error != null && error.equals("userDisabled")) {
			map.put("error", "userDisabled");
		}
		return "login";
	}

	@RequestMapping(value = "/isExistsEmail", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> isExistsEmail(
			@RequestParam String email) {
		Map<String, String> map = new HashMap<>();
		map.put("valid", Boolean.toString(!userService.isExist(email)));
		return map;
	}
}
