package com.softserve.service;

import java.util.Date;
import java.util.List;

import com.softserve.entity.CourseScheduler;

/**
 * Specify course scheduler service functionality
 * 
 * @author
 *
 */
public interface CourseSchedulerService {
	/**
	 * Add new course scheduler
	 * 
	 * @param courseScheduler
	 *            new course scheduler
	 * @return added course scheduler
	 */
	CourseScheduler addCourseScheduler(CourseScheduler courseScheduler);

	/**
	 * Mark course as deleted
	 * 
	 * @param courseScheduler
	 *            course which will marked
	 */
	void deleteCourseScheduler(CourseScheduler courseScheduler);

	/**
	 * Update course
	 * 
	 * @param courseScheduler
	 *            updated course
	 * @return updated course
	 */
	CourseScheduler updateCourseScheduler(CourseScheduler courseScheduler);

	/**
	 * Return course by id
	 * 
	 * @param id
	 *            unique course identifier
	 * @return course
	 */
	CourseScheduler getCourseSchedulerById(int id);

	/**
	 * Return all courses which is not marked as deleted
	 * 
	 * @return list of courses
	 */
	List<CourseScheduler> getAllCourseScheduleres();

	/**
	 * Return courses for subject
	 * 
	 * @param id
	 *            unique subject identifier
	 * @return courses
	 */
	List<CourseScheduler> getCourseScheduleresBySubjectId(int id);

	/**
	 * Return courses for subject author
	 * 
	 * @param id
	 *            unique user identifier
	 * @return courses
	 */
	List<CourseScheduler> getCourseSchedulersBySubjectUserId(int id);

	/**
	 * Return subscribed courses for user which is active now
	 * 
	 * @param id
	 *            unique user identifier
	 * @return
	 */
	List<CourseScheduler> getActiveSubscribedCoursesByUserId(int id);

	/**
	 * Return subscribed courses for user which will start in future
	 * 
	 * @param id
	 *            unique user identifier
	 * @return
	 */
	List<CourseScheduler> getFutureSubscribedCoursesByUserId(int id);

	/**
	 * Return subscribed courses for user which is finished
	 * 
	 * @param id
	 *            unique user identifier
	 * @return
	 */
	List<CourseScheduler> getFinishedSubscribedCoursesByUserId(int id);

	/**
	 * Return courses marked as deleted
	 * 
	 * @return list of courses
	 */
	List<CourseScheduler> getDeletedCourses();

	/**
	 * Restore course which was marked as deleted
	 * 
	 * @param course
	 *            course which will restored
	 */
	void restoreCourseScheduler(CourseScheduler course);

	List<CourseScheduler> getCourseSchedulersByStartDate(Date date);
}
