package com.softserve.dao;

import java.util.List;

import com.softserve.entity.CourseScheduler;

public interface CourseSchedulerDao {

	public CourseScheduler addCourseScheduler(CourseScheduler courseScheduler);

	public void deleteCourseScheduler(CourseScheduler courseScheduler);

	public CourseScheduler updateCourseScheduler(CourseScheduler courseScheduler);

	public CourseScheduler getCourseSchedulerById(int id);

	public List<CourseScheduler> getAllCourseScheduleres();

	public List<CourseScheduler> getCourseScheduleresBySubjectId(int id);
	
	public List<CourseScheduler> getSubscribedCoursesByUserId(int id);

	List<CourseScheduler> getCourseSchedulersBySubjectUserId(int id);
	
}
