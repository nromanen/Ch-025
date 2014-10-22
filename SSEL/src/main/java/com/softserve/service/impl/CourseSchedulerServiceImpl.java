package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.CourseSchedulerDao;
import com.softserve.entity.CourseScheduler;
import com.softserve.service.CourseSchedulerService;

@Service
public class CourseSchedulerServiceImpl implements CourseSchedulerService {

	@Autowired
	private CourseSchedulerDao courseSchedulerDao;

	@Override
	@Transactional
	public void addCourseScheduler(CourseScheduler courseScheduler) {
		courseSchedulerDao.addCourseScheduler(courseScheduler);
	}

	@Override
	@Transactional
	public void deleteCourseScheduler(CourseScheduler courseScheduler) {
		courseSchedulerDao.deleteCourseScheduler(courseScheduler);
	}

	@Override
	@Transactional
	public void updateCourseScheduler(CourseScheduler courseScheduler) {
		courseSchedulerDao.updateCourseScheduler(courseScheduler);
	}

	@Override
	@Transactional
	public CourseScheduler getCourseSchedulerById(int id) {
		return courseSchedulerDao.getCourseSchedulerById(id);
	}

	@Override
	@Transactional
	public List<CourseScheduler> getAllCourseScheduleres() {
		return courseSchedulerDao.getAllCourseScheduleres();
	}

	@Override
	@Transactional
	public List<CourseScheduler> getCourseScheduleresBySubjectId(int id) {
		return courseSchedulerDao.getCourseScheduleresBySubjectId(id);
	}
}