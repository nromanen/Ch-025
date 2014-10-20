package com.softserve.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.entity.Role;
import com.softserve.entity.User;
import com.softserve.service.RoleService;
import com.softserve.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TeacherController {

	private static final Logger logger = LoggerFactory
			.getLogger(TeacherController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacher(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		Role role = roleService.getRoleById(2);
		User user = new User();
		user.setBlocked(false);
		user.setEmail("roma");
		user.setExpired(new Date());
		user.setFirstName("Roma");
		user.setLastName("Roma");
		user.setPassword("123456");
		user.setRegistration(new Date());
		user.setRole(role);

		//userService.addUser(user);

		return "teacher";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String loginForm(Model model){

	return "login";
	}

}
