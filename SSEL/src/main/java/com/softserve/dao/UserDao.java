package com.softserve.dao;

import java.util.List;

import com.softserve.entity.User;

public interface UserDao {

	public User addUser(User user);

	public void deleteUser(User user);

	public User updateUser(User user);

	public User getUserById(int id);

	public User getUserByEmail(String email);

	public User getUserByKey(String key);

	public boolean isExist(String email);

	public List<User> getAllUsers();

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
