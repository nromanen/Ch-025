package com.softserve.dao;

import java.util.List;

import com.softserve.entity.User;

public interface UserDao {

	public void addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public User getUserById(int id);

	public User getUserByEmail(String email);
	
	public User getUserByKey(String key);

	public boolean isExist(String email);

	public List<User> getAllUsers();
}
