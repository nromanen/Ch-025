package com.softserve.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.UserDao;
import com.softserve.entity.User;
import com.softserve.entity.User.Roles;
import com.softserve.form.Registration;
import com.softserve.service.MailService;
import com.softserve.service.RoleService;
import com.softserve.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final int PASSWORD_STRENGTH = 10;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MailService mailService;

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	@Transactional
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	@Transactional
	public boolean isExist(String email) {
		return userDao.isExist(email);
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	@Transactional
	public void registrate(Registration registration, HttpServletRequest request) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				PASSWORD_STRENGTH);
		User user = new User();

		user.setEmail(registration.getEmail().trim());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setBlocked(true); // false
		user.setFirstName(registration.getFirstName().trim());
		user.setLastName(registration.getLastName().trim());
		user.setRegistration(new Date());
		user.setRole(roleService.getRoleByName(Roles.STUDENT.toString()));
		user.setExpired(new Date());
		user.setVerificationKey(passwordEncoder.encode(registration.getEmail()));
		String url = request.getRequestURL().toString();
		String url2 = request.getServletPath();
		url.replaceAll(url2, "/");
		String message = "Thank you for registration.<br>Please confirm your email by clicking next link: ";
		url = url + "/confirm?key=" + user.getVerificationKey();
		message += "<a href=\"" + url + "\">" + url + "</a>";

		mailService.sendMail(user.getEmail(), "SSEL registration", message);
		userDao.addUser(user);
	}

	@Override
	public User getUserByKey(String key) {
		return userDao.getUserByKey(key);
	}

}
