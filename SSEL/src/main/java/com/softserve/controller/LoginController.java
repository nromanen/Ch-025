package com.softserve.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.softserve.entity.User;
import com.softserve.entity.User.Roles;
import com.softserve.entity.User.Social;
import com.softserve.service.RoleService;
import com.softserve.service.UserService;
import com.softserve.util.SecurityUtil;

@Controller
public class LoginController {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginController.class);

	private static final String BAD_CREDENTIALS_EXCEPTION = "BadCredentialsException";
	private static final String USER_DISABLED = "userDisabled";
	private static final String KEY_ERROR = "error";

	@Autowired
	private UserService userService;

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

	@RequestMapping(value = "/social", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		LOG.debug("Rendering registration page.");

		Connection<?> connection = ProviderSignInUtils.getConnection(request);

		if (connection.getApi() == null) {
			return "redirect:/";
		}

		if (connection.getApi() instanceof Facebook) {
			Facebook facebook = (Facebook) connection.getApi();
			userService.registrateFacebookUser(facebook);
			User user = userService.getUserByEmail(facebook.userOperations()
					.getUserProfile().getEmail());
			SecurityUtil.logInUser(user);
			return "redirect:/student";
		}
		return "redirect:/";
	}

}
