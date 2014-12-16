package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

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
import com.softserve.dao.ConfigurationPropertiesDao;
import com.softserve.entity.Category;
import com.softserve.service.AdministratorService;
import com.softserve.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class AdministratorServiceTest {

	@Autowired
	AdministratorService administratorService;

	@Autowired
	ConfigurationPropertiesDao configurationPropertiesDao;

	@Autowired
	private UserService userService;

	 @Test
	 @DatabaseSetup("classpath:category.xml")
	 @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value =
	 "classpath:category.xml")
	 public void testAddCategory() {
	 assertEquals(true, administratorService.addCategory("Category1"));
	 assertEquals(false, administratorService.addCategory("SomeCategory"));
	 }

	@Test
	@DatabaseSetup("classpath:users.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:users.xml")
	public void TestGetCountRegistredUsersByLastDays() {
		assertNotNull(administratorService.getCountRegistredUsersByLastDays(5).size());
	}

	@Test
	@DatabaseSetup("classpath:config.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:config.xml")
	public void TestSetSupportEmail() {
		assertEquals("sselcourse@gmail.com", administratorService.getSupportEmail());
	}

}
