package com.softserve.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.entity.User;
import com.softserve.service.UserService;

@Controller
public class UserProfileController {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserProfileController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile")
	public String showUserProfile() {
		return "profile";
	}

	@RequestMapping(value = "/checkOldPassword", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody boolean checkOldPassword(
			@RequestBody Map<String, Object> map) {
		String email = userService.getCurrentUser();
		String oldPassword = map.get("old_password").toString();
		User user = userService.getUserByEmail(email);
		if (!userService.isEqualsPasswords(oldPassword, user)) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changePasswordAction(
			@RequestBody Map<String, Object> map) {
		String oldPassword = map.get("oldPassword").toString();
		String newPassword = map.get("newPassword").toString();
		String email = userService.getCurrentUser();
		User user = userService.getUserByEmail(email);
		if (!userService.isEqualsPasswords(oldPassword, user)) {
			LOG.trace(
					"{} trying to change their password but incorrectly entered old password",
					email);
			return "error";
		}
		userService.changePasswrod(user, newPassword);
		LOG.trace("{} has successfuly changed their password", email);
		return "success";
	}

	@RequestMapping(value = "/changeFirstName", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changeFirstNameAction(
			@RequestBody Map<String, Object> map, HttpSession session) {
		String firstName = map.get("firstName").toString();
		if (firstName != null) { //TODO pattern
			User user = userService
					.getUserByEmail(userService.getCurrentUser());
			user.setFirstName(firstName);
			user = userService.updateUser(user);
			session.setAttribute("user", user);
			return "success";
		}
		return "error";
	}
	
	@RequestMapping(value = "/changeLastName", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changeLastNameAction(
			@RequestBody Map<String, Object> map, HttpSession session) {
		String lastName = map.get("lastName").toString();
		if (lastName != null) { //TODO pattern
			User user = userService
					.getUserByEmail(userService.getCurrentUser());
			user.setLastName(lastName);
			user = userService.updateUser(user);
			session.setAttribute("user", user);
			return "success";
		}
		return "error";
	}
}
