package com.softserve.controller;

import java.util.Map;

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

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/profile")
	public String showUserProfile() {
		return "profile";
	}

	@RequestMapping(value = "/checkOldPassword", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody boolean checkOldPassword(@RequestBody Map<String, Object> map) {
		String email = (String) map.get("email");
		String oldPassword = (String) map.get("old_password");
		User user = userService.getUserByEmail(email);
		if (!userService.isEqualsPasswords(oldPassword, user)) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody String changePasswordAction(
			@RequestBody Map<String, Object> map) {
		return "success";
	}
}
