package com.softserve.service.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.dao.TestStatisticDao;
import com.softserve.entity.TestStatistic;
import com.softserve.entity.User;
import com.softserve.service.impl.TestStatisticServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestStatisticServiceTest {
	
	private TestStatistic testStatistic;
	private TestStatisticDao testStatisticDao;
	private TestStatisticServiceImpl testStatisticServiceImpl = new TestStatisticServiceImpl();
	private List<TestStatistic> testStatisticList;
	
	@Before
	public void setUp() {
		testStatisticDao = mock(TestStatisticDao.class);
		testStatisticServiceImpl.setTestStatisticDao(testStatisticDao);
				User user01 = new User();
				user01.setId(8);
				User user02 = new User();
				user02.setId(9);
		testStatistic = new TestStatistic();
			testStatistic.setId(32);
			testStatistic.setUser(user01);
		TestStatistic testStatistic2 = new TestStatistic();
			testStatistic2.setId(33);
			testStatistic2.setUser(user02);
		TestStatistic testStatistic3 = new TestStatistic();
			testStatistic3.setId(34);
			testStatistic3.setUser(user02);
		
		testStatisticList = new ArrayList<TestStatistic>();
			testStatisticList.add(testStatistic);
			testStatisticList.add(testStatistic2);
			testStatisticList.add(testStatistic3);
	}
	
	@Test
	public void testAddTestStatistic() {
		testStatisticServiceImpl.addTestStatistic(testStatistic);
		verify((testStatisticDao), times(1)).addTestStatistic(testStatistic);
		verifyNoMoreInteractions(testStatisticDao);
	}

	@Test
	public void testGetTestStatisticByUserByTest() {
		when(testStatisticDao.getTestStatisticByUserByTest(2, 2)).thenReturn(testStatisticList);
		List<TestStatistic> theTSList = testStatisticServiceImpl.getTestStatisticByUserByTest(2, 2);
		assertTrue((theTSList.size() == 3) && (theTSList.get(0).getId() == 32));
	}
	
	
	@Test
	public void testGetTestStatisticByGroupByTest() {
		when(testStatisticDao.getTestStatisticByGroupByTest(1, 1)).thenReturn(testStatisticList);
		List<TestStatistic> theTSList = testStatisticServiceImpl.getTestStatisticByGroupByTest(1, 1);
		assertTrue((theTSList.size() == 3) && (theTSList.get(0).getId() == 32));
	}
	
	
	@Test
	public void testGetUserResultByTest() {
		when(testStatisticDao.getUserResultByTest(1, 1)).thenReturn(0.73f);
		float userResult = testStatisticServiceImpl.getUserResultByTest(1, 1);
		assertTrue(userResult == 0.73f);
		
	}
	
	@Test
	public void testGetGroupResultByTest() {
		when(testStatisticDao.getTestStatisticByGroupByTest(2, 2)).thenReturn(testStatisticList);
		when(testStatisticDao.getUserResultByTest(8, 2)).thenReturn(0.54f);
		when(testStatisticDao.getUserResultByTest(9, 2)).thenReturn(0.83f);
		
		List<Float> floatList = testStatisticServiceImpl.getGroupResultByTest(2, 2);
		assertTrue((floatList.size() == 2) && (floatList.get(0) == 0.54f));
	}
	
	
	
	
	
	
	
}
