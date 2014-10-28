package com.softserve.service;

import java.util.List;

import com.softserve.entity.CourseScheduler;

public interface CourseSchedulerService {

	public CourseScheduler addCourseScheduler(CourseScheduler courseScheduler);

	public void deleteCourseScheduler(CourseScheduler courseScheduler);

	public CourseScheduler updateCourseScheduler(CourseScheduler courseScheduler);

	public CourseScheduler getCourseSchedulerById(int id);

	public List<CourseScheduler> getAllCourseScheduleres();

	public List<CourseScheduler> getCourseScheduleresBySubjectId(int id);
	
}
