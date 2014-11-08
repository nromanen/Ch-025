package com.softserve.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.CourseSchedulerDao;
import com.softserve.entity.CourseScheduler;
import com.softserve.service.StudentCabinetSevice;

@Service
public class StudentCabinetServiceImp implements StudentCabinetSevice{
	private List<CourseScheduler> subscribedCourses;
	@Autowired
	private CourseSchedulerDao courseSchedulerDao;
	/**
	 * Constructor by default
	 */
	public StudentCabinetServiceImp(CourseSchedulerDao instance) {
		courseSchedulerDao = instance;
	}
	
	@Transactional
	@Override
	public void initSubscribedList(int userId) {
		subscribedCourses = courseSchedulerDao.getSubscribedCoursesByUserId(userId);		
	}

	@Override
	public List<CourseScheduler> getFutureCourses(Date currentDate) {
		ArrayList<CourseScheduler> cs = new ArrayList<>();
		for (CourseScheduler item : subscribedCourses) {
			if (currentDate.before(item.getStart())) { //check if current date less than course start time
				cs.add(item);
			}
		}
		return cs;
	}

	@Override
	public List<CourseScheduler> getActiveCourses(Date currentDate) {
		ArrayList<CourseScheduler> cs = new ArrayList<>();
		for (CourseScheduler item : subscribedCourses) {
			if (currentDate.after(item.getStart()) && currentDate.before(item.getEnd())) { //check if course is started
				cs.add(item);
			}
		}
		return cs;
	}

	@Override
	public List<CourseScheduler> getFinishedCourses(Date currentDate) {
		ArrayList<CourseScheduler> cs = new ArrayList<>();
		for (CourseScheduler item : subscribedCourses) {
			if (currentDate.after(item.getEnd())) { //check if course is finished
				cs.add(item);
			}
		}
		return cs;
	}
	
	@Override
	public List<CourseScheduler> getSubscribedCourseList() {
		return subscribedCourses;
	}
}
