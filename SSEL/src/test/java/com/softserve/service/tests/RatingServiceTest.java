package com.softserve.service.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.softserve.entity.Block;
import com.softserve.entity.Group;
import com.softserve.entity.Rating;
import com.softserve.entity.User;
import com.softserve.service.BlockService;
import com.softserve.service.GroupService;
import com.softserve.service.RatingService;
import com.softserve.service.StudentGroupService;
import com.softserve.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/forTest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/forTest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })

public class RatingServiceTest {
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentGroupService studentGroupService;
	
	@Autowired
	private BlockService blockService;
	
	@Test
	@DatabaseSetup("classpath:ratingTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:ratingTestDataset.xml")
	public void testAddRating() {
		Rating newRecord = new Rating();
		newRecord.setRatingId(3);
		newRecord.setMark(95.0);
		User user = userService.getUserById(2);
		Group group = studentGroupService.getStudentGroupById(2).getGroupNumber();
		newRecord.setGroup(group);
		newRecord.setUser(user);
		Rating rating = ratingService.addRating(newRecord);
		assertNotNull(rating);		
	}
	
	@Test
	@DatabaseSetup("classpath:ratingTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:ratingTestDataset.xml")
	public void testUpdateRating() {
		Rating newRecord = ratingService.getRatingById(1);
		newRecord.setMark(64);
		User user = userService.getUserById(2);
		Group group = studentGroupService.getStudentGroupById(2).getGroupNumber();
		Block block = blockService.getBlockById(1);
		newRecord.setGroup(group);
		newRecord.setUser(user);
		Rating rating = ratingService.updateRating(newRecord);
		assertEquals(rating, newRecord);		
	}

	@Test
	@DatabaseSetup("classpath:ratingTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:ratingTestDataset.xml")
	public void testGetRatingById() {
		Rating newRecord = ratingService.getRatingById(1);
		assertEquals(newRecord.getRatingId(), 1);		
	}
	
	@Test
	@DatabaseSetup("classpath:ratingTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:ratingTestDataset.xml")
	public void testGetRatingByGroupAndUser() {
		List<Rating> list = ratingService.getRatingByGroupAndUser(1, 1);
		assertNotNull(list.get(0));
		list = ratingService.getRatingByGroupAndUser(1, 5);
		assertNull(list);
	}

	@Test
	@DatabaseSetup("classpath:ratingTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:ratingTestDataset.xml")
	public void testGetAvgRatingByGroupAndUser() {
		double res = ratingService.getAverageRatingByUserAndGroup(1, 1);
		assertTrue(res >= 0);
	}
	
	@Test
	@DatabaseSetup("classpath:ratingTestDataset.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:ratingTestDataset.xml")
	public void testGetProgressByGroupAndUser() {
		double res = ratingService.getProgressByGroupAndUser(1, 1);
		assertTrue(res >= 0);
	}
}
