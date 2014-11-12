package com.softserve.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

import com.softserve.entity.User;
import com.softserve.form.ResetPassword;
import com.softserve.service.UserService;
import com.softserve.validator.ResetPasswordValidation;

/**
 * Handle forgot password request
 * 
 * @author Khomyshyn Roman
 */
@Controller
public class ForgotPasswordController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ForgotPasswordController.class);

	private static final String ATTRIBUTE_RESET_PASSWORD = "resetPassword";

	@Autowired
	private UserService userService;

	@Autowired
	private ResetPasswordValidation resetPasswordValidation;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/remind", method = RequestMethod.GET)
	public String remindForm() {
		LOG.debug("Open remind.jsp page");
		return "remind";
	}

	@RequestMapping(value = "/remind", method = RequestMethod.POST)
	public String passwordRestore(@RequestParam String email,
			HttpServletRequest request) {
		if (StringUtils.isBlank(email)) {
			LOG.debug("Email is null");
			return "remind";
		}
		User user = userService.getUserByEmail(email);
		if (user == null) {
			LOG.debug("Email is not registered");
			return "remind";
		}
		String message = new String(messageSource.getMessage(
				"message.user.chage_password", new Object[] {},
				LocaleContextHolder.getLocale()));
		String url = request.getRequestURL().toString();
		LOG.debug("Send message to email {}", email);
		userService.remindPassword(user, url, message);
		return "redirect:/";
	}

	@RequestMapping(value = "/remind/pass", method = RequestMethod.GET)
	public String resetPasswordForm(@RequestParam("key") String key, Model model) {
		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setKey(key);
		model.addAttribute(ATTRIBUTE_RESET_PASSWORD, resetPassword);
		return "restore";
	}

	@RequestMapping(value = "/remind/pass", method = RequestMethod.POST)
	public String passwordRestore(@Valid final ResetPassword resetPassword,
			BindingResult result) {
		resetPasswordValidation.validate(resetPassword, result);
		if (result.hasErrors()) {
			return "restore";
		}
		User user = userService.getUserByKey(resetPassword.getKey());
		userService.restorePassword(user, resetPassword);
		return "redirect:/login";
	}
}
