<<<<<<< HEAD
package com.softserve.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.UserDao;
import com.softserve.entity.User;
import com.softserve.form.Registration;
import com.softserve.service.MailService;
import com.softserve.service.RoleService;
import com.softserve.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MailService mailService;

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);
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
	public void registrate(Registration registration) {
		User user = new User();

		user.setEmail(registration.getEmail().trim());
		user.setPassword(registration.getPassword());
		user.setBlocked(false);
		user.setFirstName(registration.getFirstName().trim());
		user.setLastName(registration.getLastName());
		user.setRegistration(new Date());
		user.setRole(roleService.getRoleByName("USER"));
		user.setExpired(new Date());

		mailService.sendMail(user.getEmail(), "SSEL registration",
				"Thank you for registration");
		userDao.addUser(user);

	}

}
=======
package com.softserve.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.UserDao;
import com.softserve.entity.User;
import com.softserve.form.Registration;
import com.softserve.service.MailService;
import com.softserve.service.RoleService;
import com.softserve.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MailService mailService;

	@Override
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);
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
	public void registrate(Registration registration) {
		User user = new User();

		user.setEmail(registration.getEmail().trim());
		user.setPassword(registration.getPassword());
		user.setBlocked(false);
		user.setFirstName(registration.getFirstName().trim());
		user.setLastName(registration.getLastName());
		user.setRegistration(new Date());
		user.setRole(roleService.getRoleByName("USER"));
		user.setExpired(new Date());

		mailService.sendMail(user.getEmail(), "SSEL registration",
				"Thank you for registration");
		userDao.addUser(user);

	}

}
>>>>>>> cdb409cfb5a30fe58335a64c2c69471f7d040982
