package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
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
import com.softserve.entity.Role;
import com.softserve.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;

	@Test
	@DatabaseSetup("classpath:role.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:role.xml")
	public void testRoleCount() {
		assertEquals(3, roleService.getAllRoles().size());
	}

	@Test
	@DatabaseSetup("classpath:role.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:role.xml")
	public void testGetRoleByName() {
		assertNotNull(roleService.getRoleByName("ADMIN"));
		assertNull(roleService.getRoleByName("ROLE"));
	}

	@Test
	@DatabaseSetup("classpath:role.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:role.xml")
	public void testDeleteRole() {
		Role role = roleService.getRoleByName("STUDENT");
		assertEquals(3, roleService.getAllRoles().size());
		roleService.deleteRole(role);
		assertEquals(2, roleService.getAllRoles().size());
	}

	@Test
	@DatabaseSetup("classpath:role.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:role.xml")
	public void testAddRole() {
		Role role = roleService.getRoleByName("ADMIN");
		roleService.deleteRole(role);
		assertEquals(2, roleService.getAllRoles().size());
		Role admin = new Role();
		admin.setRole("ADMIN");
		roleService.addRole(admin);
		assertEquals(3, roleService.getAllRoles().size());
	}
	
	@Test
	@DatabaseSetup("classpath:role.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:role.xml")
	public void testUpdateRole() {
		Role role = roleService.getRoleById(1);
		assertTrue(role.getRole().equals("ADMIN"));
		role.setRole("admin");
		role = roleService.updateRole(role);
		assertTrue(role.getRole().equals("admin"));
	}

}
