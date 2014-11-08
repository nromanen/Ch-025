package com.softserve.tests;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.softserve.dao.CourseSchedulerDao;
import com.softserve.entity.CourseScheduler;
import com.softserve.service.StudentCabinetSevice;
import com.softserve.service.impl.StudentCabinetServiceImp;

/**
 * Tests student cabinet service
 * @author Анатолій
 *
 */
public class StudentCabinetServiceTest {
	private StudentCabinetSevice studentCabinetService;
	private CourseSchedulerDao courseSchedulerDao;
	private List<CourseScheduler> list;
	@SuppressWarnings("deprecation")
	@Before
	public void initTests() {
		courseSchedulerDao = mock(CourseSchedulerDao.class);
		studentCabinetService = new StudentCabinetServiceImp(courseSchedulerDao);
		list = new ArrayList<>();
		Date start[] = {new Date("05-Oct-2014 11:06:37 AM"), new Date("09-Nov-2014 11:06:37 AM"), 
						new Date("06-Nov-2014 11:06:37 AM"), new Date("04-Nov-2014 11:06:37 AM") };
		Date end[] = {new Date("05-Nov-2014 11:06:37 AM"), new Date("09-Dec-2014 11:06:37 AM"), 
				new Date("28-Nov-2014 11:06:37 AM"), new Date("15-Nov-2014 11:06:37 AM")};
		for (int i = 0;i < 4; i++) {
			CourseScheduler newCourse = new CourseScheduler();
			newCourse.setStart(start[i]);
			newCourse.setEnd(end[i]);
			list.add(newCourse);
		}
	}
	
	@Test
	public void testVerifyDaoMethod() {
		studentCabinetService.initSubscribedList(anyInt());
		verify(courseSchedulerDao).getSubscribedCoursesByUserId(anyInt());
	}
	
	@Test
	public void testSubscribedListIsNotNull() {
		when(courseSchedulerDao.getSubscribedCoursesByUserId(anyInt())).thenReturn(list);
		studentCabinetService.initSubscribedList(anyInt());
		assertEquals(list, studentCabinetService.getSubscribedCourseList());
	}
	
	@Test
	public void testSubscribedListIsNull() {
		when(courseSchedulerDao.getSubscribedCoursesByUserId(anyInt())).thenReturn(null);
		studentCabinetService.initSubscribedList(anyInt());
		assertNull(studentCabinetService.getSubscribedCourseList());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFutureCourses() {
		when(courseSchedulerDao.getSubscribedCoursesByUserId(anyInt())).thenReturn(list);
		studentCabinetService.initSubscribedList(anyInt());
		assertEquals(1, studentCabinetService.getFutureCourses(new Date("08-Nov-2014")).size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetActiveCourses() {
		when(courseSchedulerDao.getSubscribedCoursesByUserId(anyInt())).thenReturn(list);
		studentCabinetService.initSubscribedList(anyInt());
		assertEquals(2, studentCabinetService.getActiveCourses(new Date("09-Nov-2014")).size());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetFinishedCourses() {
		when(courseSchedulerDao.getSubscribedCoursesByUserId(anyInt())).thenReturn(list);
		studentCabinetService.initSubscribedList(anyInt());
		assertEquals(1, studentCabinetService.getFinishedCourses(new Date("06-Nov-2014")).size());
	}
}
