package com.softserve.dao;

import java.util.Date;
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
	
	List<User> getUsersByExpiredDate(Date date);

	List<User> getAllUsers();

	List<User> getUsersByPage(int startPosition, int limitLength,
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

	long getCountOfUsersByRegistrationDate(Date startDate, Date endDate);

}
