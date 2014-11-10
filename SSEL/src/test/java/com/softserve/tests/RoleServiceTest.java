package com.softserve.tests;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.dao.RoleDao;
import com.softserve.entity.Role;
import com.softserve.service.impl.RoleServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

	private RoleDao roleDao;
	private RoleServiceImpl roleServiceImpl;
	
	@Before
	public void setUp() {
		roleDao = Mockito.mock(RoleDao.class);
		roleServiceImpl = new RoleServiceImpl();
		roleServiceImpl.setRoleDao(roleDao);
	}

	@Test
	public void testGetRoleById() {
		Role role = new Role();
		role.setRole("ADMIN");
		role.setId(1);
		when(roleServiceImpl.getRoleById(1)).thenReturn(role);
	}
	
	@Test
	public void testAddRole() {
		Role role = new Role();
		role.setRole("ADMIN");
		role.setId(1);
		roleServiceImpl.addRole(role);
		verify(roleDao).addRole(role);
	}
	
	@Test
	public void checkTimes() {
		Role role = new Role();
		role.setRole("ADMIN");
		role.setId(1);
		roleServiceImpl.addRole(role);
		roleServiceImpl.addRole(role);
		roleServiceImpl.addRole(role);
		
		verify(roleDao, times(3)).addRole(role);
	}
	
	@Test
	public void testUpdateRole() {
		Role role = new Role();
		role.setRole("ADMIN");
		role.setId(1);
		roleServiceImpl.addRole(role);
		role.setRole("TEACHER");
		roleServiceImpl.updateRole(role);
		
		verify(roleDao, times(1)).updateRole(role);
	}
	
	@Test
	public void testGetRoleByName() {
		Role role = new Role();
		role.setRole("ADMIN");
		role.setId(1);
		roleServiceImpl.addRole(role);
		role.setRole("TEACHER");
		roleServiceImpl.updateRole(role);
	
		assertNotNull(roleServiceImpl.getAllRoles());
	}

}
