package com.softserve.service;

import java.util.List;

import org.springframework.social.facebook.api.Facebook;

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

	void registrateFacebookUser(Facebook facebook, String url, String message);

	User getUserByKey(String key);

	void remindPassword(User user, String url, String message);

	void restorePassword(User user, ResetPassword resetPassword);

	String getEncoderPassword(String password);

	void changePassword(User user, String password);

	boolean isEqualsPasswords(String password, User user);

	String getCurrentUser();
//by paging info
	public List<User> getUsersVsLimit(int startPosition, int limitLength,
			String sortBy, String sortMethod);

	List<User> getUsersByFirstNameVsLimit(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	List<User> getUsersByRoleVsLimit(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	List<User> getUsersByTextVsLimit(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	List<User> getUsersByLastNameVsLimit(String searchText, int startPosition,
			int limitLength, String sortBy, String sortMethod);

	long getUsersCount();

	long getUsersByFirstNameCount(String searchName);

	long getUsersByRoleCount(String searchCategory);

	long getUsersByTextCount(String searchText);

	long getUsersByLastNameCount(String searchText);
}
