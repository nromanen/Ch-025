package com.softserve.service;

import java.util.Date;
import java.util.List;

import com.softserve.entity.CourseScheduler;

public interface CourseSchedulerService {

	CourseScheduler addCourseScheduler(CourseScheduler courseScheduler);

	void deleteCourseScheduler(CourseScheduler courseScheduler);

	CourseScheduler updateCourseScheduler(CourseScheduler courseScheduler);

	CourseScheduler getCourseSchedulerById(int id);

	List<CourseScheduler> getAllCourseScheduleres();

	List<CourseScheduler> getCourseScheduleresBySubjectId(int id);
	
	List<CourseScheduler> getCourseSchedulersBySubjectUserId(int id);
	
	List<CourseScheduler> getActiveSubscribedCoursesByUserId(int id);
	
	List<CourseScheduler> getFutureSubscribedCoursesByUserId(int id);
	
	List<CourseScheduler> getFinishedSubscribedCoursesByUserId(int id);

	List<CourseScheduler> getCourseSchedulersByStartDate(Date date);
}
