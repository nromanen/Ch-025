package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.softserve.entity.User;
import com.softserve.entity.User.Social;
import com.softserve.form.Registration;
import com.softserve.form.ResetPassword;
import com.softserve.service.TeacherRequestService;
import com.softserve.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherRequestService teacherRequestService;

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testGetUserByEmail() {
		assertNotNull(userService.getUserByEmail("roma1sk@mail.ru"));
		assertNull(userService.getUserByEmail("roma@mail.ru"));
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testIsExistsUsers() {
		assertTrue(userService.isExist("roma1sk@mail.ru"));
		assertFalse(userService.isExist("roma@mail.ru"));
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testDeleteUser() {
		User user = userService.getUserByEmail("roma1sk@mail.ru");
		assertEquals(2, userService.getAllUsers().size());
		userService.deleteUser(user);
		assertEquals(1, userService.getAllUsers().size());
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testGetUserByVerificationKey() {
		User user = userService
				.getUserByKey("$2a$10$dEDCNJbD9vOU0HTMAxqbo.mygQOPuFWJQZDgrOIk1g4f/hooby6g6");
		assertTrue(user.getFirstName().equals("Roma"));
		user = userService.getUserByKey("$2a$10$vg4zM/lxJTit54");
		assertNull(user);
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testUpdateUser() {
		User user = userService.getUserByEmail("roma1sk@mail.ru");
		user.setFirstName("roma");
		user.setBlocked(true);
		user.setLastName("roma");
		user = userService.updateUser(user);
		assertTrue(user.getLastName().equals("roma"));
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testComparePasswordHash() {
		User user = userService.getUserByEmail("roma1sk@mail.ru");
		assertTrue(userService.isEqualsPasswords("ssel2014", user));
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testRegistrationStudent() {
		assertEquals(2, userService.getAllUsers().size());
		Registration registration = new Registration();
		registration.setConfirmPassword("1234");
		registration.setEmail("roma.homyshyn@gmail.com");
		registration.setFirstName("Roma");
		registration.setLastName("Homyshyn");
		registration.setPassword("1234");
		registration.setTeacher(false);
		userService.registrateStudent(registration, "url", "message");
		assertEquals(3, userService.getAllUsers().size());
		userService.deleteUser(userService
				.getUserByEmail("roma.homyshyn@gmail.com"));
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testRegistrationTeacher() {
		assertEquals(2, userService.getAllUsers().size());
		Registration registration = new Registration();
		registration.setConfirmPassword("1234");
		registration.setEmail("roma@gmail.com");
		registration.setFirstName("Roma");
		registration.setLastName("Homyshyn");
		registration.setPassword("1234");
		registration.setTeacher(true);
		userService.registrateTeacher(registration, "message");
		assertEquals(3, userService.getAllUsers().size());
		teacherRequestService.deleteTeacherRequest(teacherRequestService
				.getTeacherRequestById(1));
		userService.deleteUser(userService.getUserByEmail("roma@gmail.com"));
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testRemindPassword() {
		User user = userService.getUserByEmail("roma1sk@mail.ru");
		assertFalse(user.isBlocked());
		userService.remindPassword(user, "url", "message");
		user = userService.getUserByEmail("roma1sk@mail.ru");
		assertTrue(user.isBlocked());
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testChangePassword() {
		User user = userService.getUserByEmail("roma1sk@mail.ru");
		assertTrue(user.getSocial().equals(Social.FACEBOOK));
		userService.updateUser(user);
		userService.changePassword(user, "1234");
		user = userService.getUserByEmail("roma1sk@mail.ru");
		assertTrue(user.getSocial().equals(Social.REGISTRATION));
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testRestorePassword() {
		User user = userService.getUserByEmail("roma1sk@mail.ru");
		user.setBlocked(true);
		userService.updateUser(user);
		ResetPassword resetPassword = new ResetPassword();
		resetPassword.setPassword("1234");
		userService.restorePassword(user, resetPassword);
		user = userService.getUserByEmail("roma1sk@mail.ru");
		assertFalse(user.isBlocked());
	}

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void testGetUserCount() {
		assertEquals(userService.getAllUsers().size(),
				userService.getCountOfUsers());
	}
}
