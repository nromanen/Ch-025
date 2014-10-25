package com.softserve.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.softserve.entity.User;
import com.softserve.form.Registration;

public interface UserService {

	public void addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);

	public User getUserById(int id);

	public User getUserByEmail(String email);

	public boolean isExist(String email);

	public List<User> getAllUsers();
	
	public void registrate(Registration registration, HttpServletRequest request);
	
	public User getUserByKey(String key);
}
