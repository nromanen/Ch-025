package com.softserve.service;

import java.util.Date;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.linkedin.api.LinkedIn;

import com.softserve.entity.User;
import com.softserve.form.Registration;
import com.softserve.form.ResetPassword;

public interface UserService {

	User addUser(User user);

	void deleteUser(User user);

	User updateUser(User user);

	User getUserById(int id);

	User getUserByEmail(String email);

	boolean isExist(String email);

	List<User> getAllUsers();

	void registrateStudent(Registration registration, String url, String message);

	void registrateTeacher(Registration registration, String message);

	void registrateFacebookUser(Facebook facebook);
	
	void registrateLinkedInUser(LinkedIn linkedIn);

	User getUserByKey(String key);

	void remindPassword(User user, String url, String message);

	void restorePassword(User user, ResetPassword resetPassword);

	String getEncoderPassword(String password);

	void changePassword(User user, String password);

	boolean isEqualsPasswords(String password, User user);
	
	List<User> getUsersByExpiredDate(Date date);

	String getCurrentUser();
	
	void changeExpiredDate(String email, String message);

	public List<User> getUsersByPage(int startPosition, int limitLength,
			String sortBy, String sortMethod);

	List<User> getUsersByFirstNameByPage(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	List<User> getUsersByRoleByPage(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	List<User> getUsersByTextByPage(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	List<User> getUsersByLastNameByPage(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	long getCountOfUsers();

	long getCountOfUsersByFirstName(String searchName);

	long getCountOfUsersByRole(String searchCategory);

	long getCountOfUsersByText(String searchText);

	long getCountOfUsersByLastName(String searchText);
}
