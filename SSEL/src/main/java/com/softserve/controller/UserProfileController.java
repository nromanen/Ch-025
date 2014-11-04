package com.softserve.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.softserve.entity.User;
import com.softserve.service.UserService;

@Controller
public class UserProfileController {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserProfileController.class);

	private static final String NAME_PATTERN = "[A-ZÀ-ß²¯ª]{1}[A-ZÀ-ß²¯ªa-zà-ÿ³¿º]{1,30}";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile")
	public String showUserProfile(HttpSession session) {
		String email = userService.getCurrentUser();
		User user = userService.getUserByEmail(email);
		session.setAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = "/checkOldPassword", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody boolean checkOldPassword(
			@RequestBody Map<String, Object> map) {
		String email = userService.getCurrentUser();
		String oldPassword = map.get("old_password").toString();
		User user = userService.getUserByEmail(email);
		return userService.isEqualsPasswords(oldPassword, user);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changePasswordAction(
			@RequestBody Map<String, Object> map) {
		String oldPassword = map.get("oldPassword").toString();
		String newPassword = map.get("newPassword").toString();
		String email = userService.getCurrentUser();
		User user = userService.getUserByEmail(email);
		if (!userService.isEqualsPasswords(oldPassword, user)) {
			LOG.debug(
					"{} trying to change their password but incorrectly entered old password",
					email);
			return "error";
		}
		userService.changePasswrod(user, newPassword);
		LOG.debug("{} has successfuly changed their password", email);
		return "success";
	}

	@RequestMapping(value = "/changeUserInformation", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changeFirstNameAction(
			@RequestBody Map<String, Object> map, HttpSession session) {
		String firstName = map.get("firstName").toString();
		String lastName = map.get("lastName").toString();
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
			System.out.println(mpf.getOriginalFilename() + " uploaded! ");
			System.out.println(mpf.getOriginalFilename());
			System.out.println(mpf.getSize() + "b");
			System.out.println(mpf.getContentType());
			String email = userService.getCurrentUser();
			User user = userService.getUserByEmail(email);
			try {
				System.out.println(mpf.getBytes());
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
