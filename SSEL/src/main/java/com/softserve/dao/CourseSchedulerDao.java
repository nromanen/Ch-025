package com.softserve.dao;

import java.util.List;

import com.softserve.entity.CourseScheduler;

/**
 * Specify CourseScheduler data access object functionality
 * @author
 *
 */
public interface CourseSchedulerDao {
	/**
	 * Add new scheduler
	 * @param courseScheduler new scheduler
	 * @return added object
	 */
	CourseScheduler addCourseScheduler(CourseScheduler courseScheduler);
	/**
	 * Delete course scheduler
	 * @param courseScheduler scheduler that was removed
	 */
	void deleteCourseScheduler(CourseScheduler courseScheduler);
	/**
	 * Update course scheduler
	 * @param courseScheduler scheduler that'll be update
	 * @return updated object
	 */
	CourseScheduler updateCourseScheduler(CourseScheduler courseScheduler);
	/**
	 * Return course scheduler by id
	 * @param id unique course scheduler identifier
	 * @return course scheduler if exists, and null otherwise
	 */
	CourseScheduler getCourseSchedulerById(int id);
	/**
	 * Returns list of course schedulers
	 * @return list of course schedulers
	 */
	List<CourseScheduler> getAllCourseScheduleres();
	/**
	 * Return course schedulers for some subject
	 * @param id unique subject identifier
	 * @return list of course schedulers
	 */
	List<CourseScheduler> getCourseScheduleresBySubjectId(int id);
	/**
	 * Return course schedulers which is active at the moment, for user
	 * @param id unique user identifier
	 * @return list of course schedulers
	 */
	List<CourseScheduler> getActiveSubscribedCoursesByUserId(int id);
	/**
	 * Return course schedulers which is will active in future, for user
	 * @param id unique user identifier
	 * @return list of course schedulers
	 */
	
	List<CourseScheduler> getFutureSubscribedCoursesByUserId(int id);
	/**
	 * Return course schedulers which is finished at the moment, for user
	 * @param id unique user identifier
	 * @return list of course schedulers
	 */
	
	List<CourseScheduler> getFinishedSubscribedCoursesByUserId(int id);
	/**
	 * Return course schedulers for user that is author for them
	 * @param id unique user identifier
	 * @return list of course schedulers
	 */
	List<CourseScheduler> getCourseSchedulersBySubjectUserId(int id);
	
}
