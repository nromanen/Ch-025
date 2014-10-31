package com.softserve.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.entity.CourseScheduler;
import com.softserve.service.AdministratorService;
import com.softserve.service.impl.AdministratorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AdministratorServiceTest {
	
	List<CourseScheduler> list;
	AdministratorService administratorService;
	AdministratorService administratorService2;
	
	@Before
	public void setUp() {
		list = spy(new ArrayList<CourseScheduler>());
		addTestToList();
		administratorService = mock(AdministratorService.class);
		administratorService2 = spy(new AdministratorServiceImpl(list));
	}
	
	@Test
	public void simplyTestMock() {
		when(administratorService.getActiveCourses()).thenReturn(list);
		List<CourseScheduler> l = administratorService.getActiveCourses();
		assertEquals(list, l);
	}
	
	@Test
	public void simplyTestIsNotCalled() {
		assertNotNull(administratorService);
		administratorService.getActiveCourses();
		verify(list, never()).add(null);
	}
	
	@Test
	public void simplyTestIsCalled() {
		CourseScheduler scheduler = addTestToList();
		administratorService2.getActiveCourses();
		verify(list, times(1)).add(scheduler);
	}

	@SuppressWarnings("deprecation")
	private CourseScheduler addTestToList() {
		CourseScheduler scheduler2 = new CourseScheduler();
		scheduler2.setStart(new Date(2013, 5, 21));
		scheduler2.setEnd(new Date(2016,6,12));
		list.add(scheduler2);
		return scheduler2;
	}
}
