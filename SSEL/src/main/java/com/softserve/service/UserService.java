package com.softserve.service;

import java.util.List;

import com.softserve.entity.Subject;
import com.softserve.entity.User;
import com.softserve.form.Registration;
import com.softserve.form.ResetPassword;

public interface UserService {

	public User addUser(User user);

	public void deleteUser(User user);

	public User updateUser(User user);

	public User getUserById(int id);

	public User getUserByEmail(String email);

	public boolean isExist(String email);

	public List<User> getAllUsers();

	public void registrateStudent(Registration registration, String url, String message);
	
	public void registrateTeacher(Registration registration, String message);

	public User getUserByKey(String key);

	public void remindPassword(User user, String url, String message);

	public void restorePassword(User user, ResetPassword resetPassword);

	public String getEncoderPassword(String password);

	public void changePasswrod(User user, String password);

	public boolean isEqualsPasswords(String password, User user);

	public String getCurrentUser();

	public List<User> getUsersVsLimit(int startPosition, int limitLength, String sortBy, String sortMethod);

	public List<User> getUsersByFirstNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	public List<User> getUsersByRoleVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	public List<User> getUsersByTextVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	public List<User> getUsersByLastNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	public long getUsersCount();

	public long getUsersByFirstNameCount(String searchName);

	public long getUsersByRoleCount(String searchCategory);

	public long getUsersByTextCount(String searchText);

	public long getUsersByLastNameCount(String searchText);
}
