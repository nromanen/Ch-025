package com.softserve.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.entity.User;
import com.softserve.form.Registration;
import com.softserve.service.UserService;
import com.softserve.validator.RegistrationValidation;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

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
		userService.registrate(registration, request);
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

	@RequestMapping(value = "/email_exists", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody boolean isEmailExist(
			@RequestBody Map<String, Object> map) {
		String email = (String) map.get("email");
		if (email == null) {
			return false;
		}
		return userService.isExist(email);
	}
}
