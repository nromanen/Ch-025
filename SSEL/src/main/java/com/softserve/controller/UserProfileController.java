package com.softserve.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.softserve.entity.User;
import com.softserve.service.UserService;

/**
 * Handle user profile request
 * 
 * @author Khomyshyn Roman
 */
@Controller
public class UserProfileController {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserProfileController.class);

	private static final String NAME_PATTERN = "[A-ZА-ЯІЇЄ]{1}[A-ZА-ЯІЇЄa-zа-яіїє]{1,30}";
	private static final String ATTRIBUTE_VALID = "valid";
	private static final String ATTRIBUTE_IMAGE = "image";
	private static final String ATTRIBUTE_USER = "user";
	private static final String KEY_OLD_PASSWORD = "oldPassword";
	private static final String KEY_NEW_PASSWORD = "newPassword";
	private static final String KEY_FIRSTNAME = "firstName";
	private static final String KEY_LASTNAME = "lastName";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile")
	public String showUserProfile(HttpSession session) {
		String email = userService.getCurrentUser();
		User user = userService.getUserByEmail(email);
		session.setAttribute(ATTRIBUTE_USER, user);
		if (user.getImage() != null) {
			String encodedImage = new String(Base64.encode(user.getImage()));
			session.setAttribute(ATTRIBUTE_IMAGE, encodedImage);
		}
		return "profile";
	}

	@RequestMapping(value = "/checkOldPassword")
	public @ResponseBody Map<String, String> checkOldPassword(
			@RequestParam String oldPassword) {
		String email = userService.getCurrentUser();
		User user = userService.getUserByEmail(email);
		Map<String, String> map = new HashMap<>();
		if (StringUtils.isBlank(oldPassword)) {
			map.put(ATTRIBUTE_VALID, Boolean.toString(Boolean.FALSE));
		} else {
			map.put(ATTRIBUTE_VALID, Boolean.toString(userService
					.isEqualsPasswords(oldPassword, user)));
		}
		return map;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changePasswordAction(
			@RequestBody Map<String, Object> map) {
		String oldPassword = map.get(KEY_OLD_PASSWORD).toString();
		String newPassword = map.get(KEY_NEW_PASSWORD).toString();
		String email = userService.getCurrentUser();
		User user = userService.getUserByEmail(email);
		if (!userService.isEqualsPasswords(oldPassword, user)
				|| StringUtils.isAnyBlank(oldPassword, newPassword)) {
			LOG.debug(
					"{} trying to change their password but incorrectly entered old password",
					email);
			return "error";
		}
		userService.changePassword(user, newPassword);
		LOG.debug("{} has successfuly changed their password", email);
		return "success";
	}

	@RequestMapping(value = "/changeUserInformation", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changeFirstNameAction(
			@RequestBody Map<String, Object> map, HttpSession session) {
		String firstName = map.get(KEY_FIRSTNAME).toString();
		String lastName = map.get(KEY_LASTNAME).toString();
		if (firstName.matches(NAME_PATTERN) && lastName.matches(NAME_PATTERN)) {
			User user = userService
					.getUserByEmail(userService.getCurrentUser());
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user = userService.updateUser(user);
			session.setAttribute("user", user);
			return "success";
		}
		return "error";
	}

	@RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			String email = userService.getCurrentUser();
			User user = userService.getUserByEmail(email);
			try {
				user.setImage(mpf.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			user = userService.updateUser(user);
			String encodedImage = new String(Base64.encode(user.getImage()));
			session.setAttribute("image", encodedImage);
		}
		return "success";
	}

}
