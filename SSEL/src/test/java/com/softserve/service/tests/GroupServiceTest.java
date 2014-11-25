package com.softserve.service.tests;

import static org.junit.Assert.*;

import java.util.List;

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
import com.softserve.entity.Group;
import com.softserve.service.CourseSchedulerService;
import com.softserve.service.GroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/forTest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/forTest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class GroupServiceTest {
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private CourseSchedulerService courseSchedulerService;
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:studentCabinetTestDataset.xml")
	public void testAddGroup() {
		Group group = new Group();
		group.setGroupId(4);
		group.setActive(true);
		group.setDeleted(false);
		group.setCourse(courseSchedulerService.getCourseSchedulerById(1));
		groupService.addGroup(group);
		group = groupService.getGroupById(4);
		assertNotNull(group);
	}
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:studentCabinetTestDataset.xml")
	public void testUpdateGroup() {
		Group group = groupService.getGroupById(3);
		group.setActive(false);
		Group updated = groupService.updateGroup(group);
		assertEquals(group, updated);;
	}

	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:studentCabinetTestDataset.xml")
	public void testDeleteRestore() {
		Group group = groupService.getGroupById(3);
		int before = groupService.getDeletedGroups().size();
		group.setDeleted(true);
		groupService.deleteGroup(group);
		int after = groupService.getDeletedGroups().size();
		assertNotEquals(before, after);
		groupService.restoreGroup(group);
		after = groupService.getDeletedGroups().size();
		assertEquals(before, after);
	}
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:studentCabinetTestDataset.xml")
	public void testgetGroupByScheduler() {
		Group group = groupService.getGroupByScheduler(2);
		assertNotNull(group);
		group = groupService.getGroupByScheduler(34);
		assertNull(group);
	}
	
	@Test
	@DatabaseSetup("classpath:studentCabinetTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:studentCabinetTestDataset.xml")
	public void testgetGroupByStudent() {
		List<Group> groups = groupService.getGroupsByStudent(1);
		assertTrue(groups.size() > 0);
		groups = groupService.getGroupsByStudent(34);
		assertTrue(groups.size() == 0);
	}
}