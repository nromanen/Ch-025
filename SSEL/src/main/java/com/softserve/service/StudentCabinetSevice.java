package com.softserve.service;

import java.util.Date;
import java.util.List;

import com.softserve.entity.CourseScheduler;
/**
 * Specify Student cabinet functionality
 * 
 *
 */
public interface StudentCabinetSevice {

	public void initSubscribedList(int userId);
	
	public List<CourseScheduler> getSubscribedCourseList();
	
	public List<CourseScheduler> getFutureCourses(Date currentDate);
	
	public List<CourseScheduler> getActiveCourses(Date currentDate);
	
	public List<CourseScheduler> getFinishedCourses(Date currentDate);
	
}
