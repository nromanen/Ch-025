package com.softserve.tests;

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
}
