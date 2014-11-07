package com.softserve.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.UserDao;
import com.softserve.entity.TeacherRequest;
import com.softserve.entity.User;
import com.softserve.entity.User.Roles;
import com.softserve.form.Registration;
import com.softserve.form.ResetPassword;
import com.softserve.service.MailService;
import com.softserve.service.RoleService;
import com.softserve.service.TeacherRequestService;
import com.softserve.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final int PASSWORD_STRENGTH = 10;
	private static final String DEFAULT_PASSWORD = "ssel2014";

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MailService mailService;

	@Autowired
	private TeacherRequestService teacherRequestService;

	@Override
	@Transactional
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return userDao.updateUser(user);
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
		user.setBlocked(true);
		user.setFirstName(registration.getFirstName().trim());
		user.setLastName(registration.getLastName().trim());
		user.setRegistration(new Date());
		user.setExpired(new Date());
		user.setVerificationKey(passwordEncoder.encode(registration.getEmail()));

		if (!registration.isTeacher()) {
			user.setRole(roleService.getRoleByName(Roles.STUDENT.toString()));
			String url = request.getRequestURL().toString();
			url.replaceAll(request.getServletPath(), "/");
			String message = "Thank you for registration.<br>Please confirm your email by clicking next link: ";
			url = url + "/confirm?key=" + user.getVerificationKey();
			message += "<a href=\"" + url + "\">" + url + "</a>";
			mailService.sendMail(user.getEmail(), "SSEL registration", message);
			userDao.addUser(user);
		} else {
			user.setRole(roleService.getRoleByName(Roles.TEACHER.toString()));
			user = userDao.addUser(user);
			TeacherRequest teacherRequest = new TeacherRequest();
			teacherRequest.setActive(true);
			teacherRequest.setRequestDate(new Date());
			teacherRequest.setUser(user);
			teacherRequestService.addTeacherRequest(teacherRequest);
			//TODO send email
		}
	}

	@Override
	@Transactional
	public User getUserByKey(String key) {
		return userDao.getUserByKey(key);
	}

	@Override
	@Transactional
	public void remindPassword(User user, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String message = "Change your password by clicking on next link: ";
		url = url + "/pass?key=" + user.getVerificationKey();
		message += "<a href=\"" + url + "\">" + url + "</a>";
		mailService.sendMail(user.getEmail(), "SSEL change password", message);
		user.setBlocked(true);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				PASSWORD_STRENGTH);
		user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
		userDao.updateUser(user);
	}

	@Override
	@Transactional
	public void restorePassword(User user, ResetPassword resetPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				PASSWORD_STRENGTH);
		user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
		user.setBlocked(false);
		userDao.updateUser(user);
	}

	@Override
	@Transactional
	public String getEncoderPassword(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				PASSWORD_STRENGTH);
		return passwordEncoder.encode(password);
	}

	@Override
	@Transactional
	public boolean isEqualsPasswords(String password, User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				PASSWORD_STRENGTH);
		return passwordEncoder.matches(password, user.getPassword());
	}

	@Override
	@Transactional
	public void changePasswrod(User user, String password) {
		user.setPassword(getEncoderPassword(password));
		userDao.updateUser(user);
	}

	@Override
	@Transactional
	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		return authentication.getName();
	}

	@Override
	@Transactional
	public List<User> getUsersVsLimit(int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersVsLimit(startPosition, limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByFirstNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByFirstNameVsLimit(searchText, startPosition,
				limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByLastNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByLastNameVsLimit(searchText, startPosition,
				limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByRoleVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByRoleVsLimit(searchText,
				startPosition, limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByTextVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByTextVsLimit(searchText, startPosition, limitLength, sortBy, sortMethod);
	}

	@Override
	public long getUsersCount() {
		return userDao.getUsersCount();
	}

	@Override
	public long getUsersByFirstNameCount(String searchName) {
		return userDao.getUsersByFirstNameCount(searchName);
	}

	@Override
	public long getUsersByLastNameCount(String searchName) {
		return userDao.getUsersByLastNameCount(searchName);
	}

	@Override
	public long getUsersByRoleCount(String searchRole) {
		return userDao.getUsersByRoleCount(searchRole);
	}

	@Override
	public long getUsersByTextCount(String searchText) {
		return userDao.getUsersByTextCount(searchText);
	}
}
