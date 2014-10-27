package com.softserve.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	public void registrate(Registration registration, HttpServletRequest request);

	public User getUserByKey(String key);
	
	public void remindPassword(User user, HttpServletRequest request);
	
	public void restorePassword(User user, ResetPassword resetPassword);
}
