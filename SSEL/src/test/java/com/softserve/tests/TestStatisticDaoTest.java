package com.softserve.tests;

import static org.junit.Assert.assertNotNull;
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
import com.softserve.dao.TestStatisticDao;
import com.softserve.entity.Group;
import com.softserve.entity.TestStatistic;
import com.softserve.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class TestStatisticDaoTest {

	@Autowired
	private TestStatisticDao testStatisticDao;

	@Autowired
	private TestService testService;

	@Test
	@DatabaseSetup("classpath:TestStatistic.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:TestStatistic.xml")
	public void testGetTestStatisticByUserByTest() {
		List<TestStatistic> tsList = testStatisticDao
				.getTestStatisticByUserByTest(1, 1);
		assertTrue((tsList.size() == 3)
				&& (tsList.get(0).getUser().getId() == 1));

	}

	@Test
	@DatabaseSetup("classpath:TestStatistic.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:TestStatistic.xml")
	public void testGetTestStatisticByGroupByTest() {
		List<TestStatistic> tsList = testStatisticDao
				.getTestStatisticByGroupByTest(1, 1);
		assertTrue((tsList.size() == 4)
				&& (tsList.get(1).getUserResult() == 3f));
	}

	@Test
	@DatabaseSetup("classpath:TestStatistic.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:TestStatistic.xml")
	public void testGetUserResultByTest() {
		float user1Result = testStatisticDao.getUserResultByTest(1, 1);
		float user2Result = testStatisticDao.getUserResultByTest(2, 1);
		float user3Result = testStatisticDao.getUserResultByTest(2, 6);
		assertTrue((user1Result == 0.65f) && (user2Result == 0.875f) && (user3Result == 0f));
	}

	@Test
	@DatabaseSetup("classpath:TestStatistic.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:TestStatistic.xml")
	public void testAddTestStatistic() {
		TestStatistic testStatistic = new TestStatistic();
		testStatistic.setTest(testService.getTestById(1));
		Group group = new Group();
		group.setGroupId(5);
		testStatistic.setGroup(group);
		testStatisticDao.addTestStatistic(testStatistic);
		assertNotNull(testStatisticDao.getTestStatisticByGroupByTest(5, 1));
	}

}
