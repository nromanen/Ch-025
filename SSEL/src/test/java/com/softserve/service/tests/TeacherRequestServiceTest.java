package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.softserve.entity.TeacherRequest;
import com.softserve.service.TeacherRequestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class TeacherRequestServiceTest {

	@Autowired
	private TeacherRequestService teacherRequestService;

	@Test
	@DatabaseSetup("classpath:teacherrequest.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:teacherrequest.xml")
	public void testGetAllActiveRequest() {
		assertEquals(2, teacherRequestService.getAllTeacherRequests().size());
		assertEquals(teacherRequestService.getAllActiveTeacherRequestsCount(),
				teacherRequestService.getAllActiveTeacherRequests().size());
	}

	@Test
	@DatabaseSetup("classpath:teacherrequest.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:teacherrequest.xml")
	public void testGetRequestByUserId() {
		TeacherRequest teacherRequest = teacherRequestService
				.getTeacherRequestByUserId(2);
		assertTrue(teacherRequest.isActive());
		teacherRequest.setActive(false);
		teacherRequest = teacherRequestService
				.updateTeacherRequest(teacherRequest);
		assertFalse(teacherRequest.isActive());
	}

}
