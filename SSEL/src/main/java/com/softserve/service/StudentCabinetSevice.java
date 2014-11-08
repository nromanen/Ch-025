package com.softserve.service;

import java.util.List;

import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudentGroup;
/**
 * Specify Student cabinet functionality
 * 
 *
 */
public interface StudentCabinetSevice {

	public void initSubscribedList(int user_id);
	
	public List<CourseScheduler> getFutureCourses();
	
	public List<CourseScheduler> getActiveCourses();
	
	public List<CourseScheduler> getFinishedCourses();
	
	public StudentGroup getStudentGroupByUserAndGroupId(int userId, int groupId);
	
	
}
