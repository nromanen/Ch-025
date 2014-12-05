package com.softserve.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.UserDao;
import com.softserve.entity.TeacherRequest;
import com.softserve.entity.User;
import com.softserve.entity.User.Roles;
import com.softserve.entity.User.Social;
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
	private static final int ONE_YEAR = 1;

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
	public void registrateStudent(Registration registration, String url,
			String message) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				PASSWORD_STRENGTH);
		Calendar calendar = Calendar.getInstance();

		User user = new User();
		user.setEmail(registration.getEmail().trim());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setBlocked(true);
		user.setFirstName(registration.getFirstName().trim());
		user.setLastName(registration.getLastName().trim());
		user.setRegistration(calendar.getTime());
		calendar.add(Calendar.YEAR, ONE_YEAR);
		user.setExpired(calendar.getTime());
		user.setVerificationKey(passwordEncoder.encode(registration.getEmail()));
		user.setRole(roleService.getRoleByName(Roles.STUDENT.toString()));
		user.setSocial(Social.REGISTRATION);

		url = url + "/confirm?key=" + user.getVerificationKey();
		message += " <a href=\"" + url + "\">" + url + "</a>";
		mailService.sendMail(user.getEmail(), "SSEL registration", message);
		userDao.addUser(user);
	}

	@Override
	@Transactional
	public void registrateTeacher(Registration registration, String message) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				PASSWORD_STRENGTH);
		Calendar calendar = Calendar.getInstance();

		User user = new User();
		user.setEmail(registration.getEmail().trim());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setBlocked(true);
		user.setFirstName(registration.getFirstName().trim());
		user.setLastName(registration.getLastName().trim());
		user.setRegistration(calendar.getTime());
		calendar.add(Calendar.YEAR, ONE_YEAR);
		user.setExpired(calendar.getTime());
		user.setVerificationKey(passwordEncoder.encode(registration.getEmail()));
		user.setRole(roleService.getRoleByName(Roles.TEACHER.toString()));
		user.setSocial(Social.REGISTRATION);
		user = userDao.addUser(user);

		TeacherRequest teacherRequest = new TeacherRequest();
		teacherRequest.setActive(true);
		teacherRequest.setRequestDate(new Date());
		teacherRequest.setUser(user);
		teacherRequestService.addTeacherRequest(teacherRequest);

		mailService.sendMail(user.getEmail(), "SSEL registration", message);
	}

	@Override
	@Transactional
	public void registrateFacebookUser(Facebook facebook, String url,
			String message) {
		UserOperations operations = facebook.userOperations();
		FacebookProfile profile = operations.getUserProfile();
		if (!userDao.isExist(profile.getEmail())) {
			Calendar calendar = Calendar.getInstance();
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
					PASSWORD_STRENGTH);
			User user = new User();
			user.setBlocked(false);
			user.setEmail(profile.getEmail());
			user.setRegistration(calendar.getTime());
			calendar.add(Calendar.YEAR, ONE_YEAR);
			user.setExpired(calendar.getTime());
			user.setFirstName(profile.getFirstName());
			user.setLastName(profile.getLastName());
			user.setImage(operations.getUserProfileImage());
			user.setPassword(getEncoderPassword(DEFAULT_PASSWORD));
			user.setRole(roleService.getRoleByName(Roles.STUDENT.toString()));
			user.setSocial(Social.FACEBOOK);
			user.setVerificationKey(passwordEncoder.encode(profile.getEmail()));
			userDao.addUser(user);
			url = url.replace("/social",
					"/remind/pass?key=" + user.getVerificationKey());
			message += " <a href=\"" + url + "\">" + url + "</a>";
			mailService.sendMail(user.getEmail(), "SSEL create new password",
					message);
		}
	}

	@Override
	@Transactional
	public User getUserByKey(String key) {
		return userDao.getUserByKey(key);
	}

	@Override
	@Transactional
	public void remindPassword(User user, String url, String message) {
		url = url + "/pass?key=" + user.getVerificationKey();
		message += " <a href=\"" + url + "\">" + url + "</a>";
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
	public void changePassword(User user, String password) {
		user.setPassword(getEncoderPassword(password));
		user.setSocial(Social.REGISTRATION);
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
	public List<User> getUsersByPage(int startPosition, int limitLength,
			String sortBy, String sortMethod) {
		return userDao.getUsersByPage(startPosition, limitLength, sortBy,
				sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByFirstNameByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByFirstNameByPage(searchText, startPosition,
				limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByLastNameByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByLastNameByPage(searchText, startPosition,
				limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByRoleByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByRoleByPage(searchText, startPosition,
				limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<User> getUsersByTextByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return userDao.getUsersByTextByPage(searchText, startPosition,
				limitLength, sortBy, sortMethod);
	}

	@Override
	public long getCountOfUsers() {
		return userDao.getCountOfUsers();
	}

	@Override
	public long getCountOfUsersByFirstName(String searchName) {
		return userDao.getCountOfUsersByFirstName(searchName);
	}

	@Override
	public long getCountOfUsersByLastName(String searchName) {
		return userDao.getCountOfUsersByLastName(searchName);
	}

	@Override
	public long getCountOfUsersByRole(String searchRole) {
		return userDao.getCountOfUsersByRole(searchRole);
	}

	@Override
	public long getCountOfUsersByText(String searchText) {
		return userDao.getCountOfUsersByText(searchText);
	}

	@Override
	public long getCountOfUsersByRegistrationDate(Date startDate, Date endDate) {
		return userDao.getCountOfUsersByRegistrationDate(startDate, endDate);
	}
}
