package com.softserve.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginController.class);
	
	private static final String BAD_CREDENTIALS_EXCEPTION = "BadCredentialsException";
	private static final String USER_DISABLED = "userDisabled";
	private static final String KEY_ERROR = "error";
	
	private Facebook facebook;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(
			@RequestParam(value = "error", required = false) String error,
			Map<String, Object> map) {
		LOG.debug("Check login error");
		if (StringUtils.isNotBlank(error)
				&& error.equals(BAD_CREDENTIALS_EXCEPTION)) {
			map.put(KEY_ERROR, BAD_CREDENTIALS_EXCEPTION);
		} else if (StringUtils.isNotBlank(error) && error.equals(USER_DISABLED)) {
			map.put(KEY_ERROR, USER_DISABLED);
		}
		return "login";
	}
	
	@RequestMapping(value = "/connect/facebook", method = RequestMethod.GET)
	public String facebookConnect() {
		return "redirect:/";
	}
}
