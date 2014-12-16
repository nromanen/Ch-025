package com.softserve.service.tests;

import static org.junit.Assert.*;

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
import com.softserve.entity.ConfigurationProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class ConfigurationPropertiesTest {
	@Autowired
	private ConfigurationPropertiesDao configurationProperties;
	
	@Test
	@DatabaseSetup("classpath:configTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:configTestDataset.xml")
	public void testAddProperty() {
		ConfigurationProperty cp = new ConfigurationProperty();
		cp.setId(2);
		cp.setKey("lalal");
		cp.setValue("oifd");
		assertNotNull(configurationProperties.addproperty(cp));
	}
	
	@Test
	@DatabaseSetup("classpath:configTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:configTestDataset.xml")
	public void testUpdateProperty() {
		ConfigurationProperty cp = configurationProperties.getPropertyById(1);
		cp.setKey("lalal");
		cp.setValue("oifd");
		assertEquals(configurationProperties.updateProperty(cp), cp);
	}

	@Test
	@DatabaseSetup("classpath:configTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:configTestDataset.xml")
	public void testGetPropertyById() {
		ConfigurationProperty cp = configurationProperties.getPropertyById(1);
		assertTrue(cp.getId() == 1);
	}
	
	@Test
	@DatabaseSetup("classpath:configTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:configTestDataset.xml")
	public void testGetPropertyByKey() {
		ConfigurationProperty cp = configurationProperties.getPropertyByKey("bla");
		assertEquals("bla", cp.getKey());
	}
}
