package com.softserve.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.softserve.entity.CourseScheduler;
import com.softserve.service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
	private List<CourseScheduler> subscribedCourses;

	/**
	 * Constructor by default
	 */
	public AdministratorServiceImpl() {

	}
	
	public AdministratorServiceImpl(List<CourseScheduler> courseSchedulers) {
		this.subscribedCourses = courseSchedulers;
	}
	
	@Override
	public List<CourseScheduler> getActiveCourses() {
		Date currentDate = new Date();
		List<CourseScheduler> cs = new ArrayList<>();
		for (CourseScheduler item : subscribedCourses) {
			// TODO add end date course check
			if (!currentDate.before(item.getStart()) 
					&& currentDate.before(item.getEnd())) { //check if course is started
				cs.add(item);
			}
		}
	return cs;
	}

}
