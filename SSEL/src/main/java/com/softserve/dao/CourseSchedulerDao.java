package com.softserve.dao;

import java.util.List;

import com.softserve.entity.CourseScheduler;

public interface CourseSchedulerDao {

	public void addCourseScheduler(CourseScheduler courseScheduler);

	public void deleteCourseScheduler(CourseScheduler courseScheduler);

	public void updateCourseScheduler(CourseScheduler courseScheduler);

	public CourseScheduler getCourseSchedulerById(int id);

	public List<CourseScheduler> getAllCourseScheduleres();

	public List<CourseScheduler> getCourseScheduleresBySubjectId(int id);
	
	public List<CourseScheduler> getSubscribedCoursesByUserId(int id);
	
}
