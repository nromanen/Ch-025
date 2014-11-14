package com.softserve.dao;

import java.util.List;

import com.softserve.entity.User;

public interface UserDao {

	User addUser(User user);

	void deleteUser(User user);

	User updateUser(User user);

	User getUserById(int id);

	User getUserByEmail(String email);

	User getUserByKey(String key);

	boolean isExist(String email);

	List<User> getAllUsers();

	List<User> getUsersVsLimit(int startPosition, int limitLength,
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
