package com.softserve.service.tests;
import org.junit.Assert;
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
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.User;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.StudentCabinetService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class StudentCabinetServiceTest {

	@Autowired
	private StudentCabinetService studentCabinetService;
	
	@Autowired
	private StudentGroupService studentGroupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseSchedulerService courseScheduler;
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value="classpath:studentCabinetTestDataset.xml")
	public void testSubscribeOnFinishedCourse() {
		User user = userService.getUserById(1);
		System.out.println(user);
		CourseScheduler cs = courseScheduler.getCourseSchedulerById(1);
		System.out.println(cs);
		int before = studentGroupService.getAllStudentGroups().size();
		studentCabinetService.subscribe(cs, user, true);
		int after = studentGroupService.getAllStudentGroups().size();
		Assert.assertEquals(before, after);
		
	}
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value="classpath:studentCabinetTestDataset.xml")
	public void testSubscribeOnActiveCourse() {
		User user = userService.getUserById(1);
		CourseScheduler cs = courseScheduler.getCourseSchedulerById(2);
		int before = studentGroupService.getAllStudentGroups().size();
		studentCabinetService.subscribe(cs, user, true);
		int after = studentGroupService.getAllStudentGroups().size();
		Assert.assertEquals(before, after);
	}
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:studentCabinetTestDataset.xml")
	public void testSubscribeOnFutureCourse() {
		User user = userService.getUserById(1);
		CourseScheduler cs = courseScheduler.getCourseSchedulerById(3);
		int before = studentGroupService.getAllStudentGroups().size();
		studentCabinetService.subscribe(cs, user, true);
		int after = studentGroupService.getAllStudentGroups().size();
		Assert.assertNotEquals(before, after);
		studentGroupService.deleteStudentGroup(studentGroupService.getStudentGroupById(1));
	}
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value="classpath:studentCabinetTestDataset.xml")
	public void testUnsubscribeFromCourse() {
		User user = userService.getUserById(1);
		CourseScheduler cs = courseScheduler.getCourseSchedulerById(3);
		studentCabinetService.subscribe(cs, user, true);
		int before = studentGroupService.getAllStudentGroups().size();
		studentCabinetService.subscribe(cs, user, false);
		int after = studentGroupService.getAllStudentGroups().size();
		Assert.assertNotEquals(before, after);	}
}
