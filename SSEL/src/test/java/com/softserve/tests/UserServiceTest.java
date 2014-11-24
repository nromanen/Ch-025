package com.softserve.tests;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.dao.RoleDao;
import com.softserve.dao.UserDao;
import com.softserve.entity.Role;
import com.softserve.entity.User;
import com.softserve.entity.User.Social;
import com.softserve.service.MailService;
import com.softserve.service.TeacherRequestService;
import com.softserve.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	private UserDao userDao;
	@Mock
	private RoleDao roleDao;
	@Mock
	private MailService mailService;
	@Mock
	private TeacherRequestService teacherRequestService;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIsExistEmail() {
		Role role = new Role();
		role.setId(1);
		role.setRole("Admin");
		User user = new User();
		user.setRole(role);
		user.setEmail("roma1sk@mail.com");
		user.setFirstName("Roma");
		user.setLastName("Roma");
		user.setBlocked(false);
		user.setExpired(new Date());
		user.setId(1);
		user.setPassword("sagafsgf");
		user.setRegistration(new Date());
		user.setSocial(Social.FACEBOOK);
		userServiceImpl.addUser(user);
		when(userServiceImpl.getUserByEmail("roma1sk@mail.com")).thenReturn(
				user);
	}
}
