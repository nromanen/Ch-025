package com.softserve.service;

import java.util.List;

import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Subject;

public interface AdministratorService {
	
	public List<CourseScheduler> getActiveCourses();	
	
	public boolean addCategory(String name);
	
	public List<Subject> searchSubjectsByName(String searchText);
	
	public List<Subject> searchSubjectsByCategory(String searchText);

}
