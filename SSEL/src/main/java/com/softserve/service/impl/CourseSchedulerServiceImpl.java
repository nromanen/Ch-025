package com.softserve.service.impl;

import java.util.Date;
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

	/**
	 * @see com.softserve.service.CourseSchedulerService#addCourseScheduler(CourseScheduler)
	 */
	@Override
	@Transactional
	public CourseScheduler addCourseScheduler(CourseScheduler courseScheduler) {
		return courseSchedulerDao.addCourseScheduler(courseScheduler);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#deleteCourseScheduler(CourseScheduler)
	 */
	@Override
	@Transactional
	public void deleteCourseScheduler(CourseScheduler courseScheduler) {
		courseSchedulerDao.setCourseSchedulerDeleted(courseScheduler, true);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#updateCourseScheduler(CourseScheduler)
	 */
	@Override
	@Transactional
	public CourseScheduler updateCourseScheduler(CourseScheduler courseScheduler) {
		return courseSchedulerDao.updateCourseScheduler(courseScheduler);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getCourseSchedulerById(int)
	 */
	@Override
	@Transactional
	public CourseScheduler getCourseSchedulerById(int id) {
		return courseSchedulerDao.getCourseSchedulerById(id);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getAllCourseScheduleres()
	 */
	@Override
	@Transactional
	public List<CourseScheduler> getAllCourseScheduleres() {
		return courseSchedulerDao.getAllCourseScheduleres();
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getCourseScheduleresBySubjectId(int)
	 */
	@Override
	@Transactional
	public List<CourseScheduler> getCourseScheduleresBySubjectId(int id) {
		return courseSchedulerDao.getCourseScheduleresBySubjectId(id);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getCourseSchedulersBySubjectUserId(int)
	 */
	@Override
	@Transactional
	public List<CourseScheduler> getCourseSchedulersBySubjectUserId(int id) {
		return courseSchedulerDao.getCourseSchedulersBySubjectUserId(id);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getActiveSubscribedCoursesByUserId(int)
	 */
	@Transactional
	@Override
	public List<CourseScheduler> getActiveSubscribedCoursesByUserId(int id) {
		return courseSchedulerDao.getActiveSubscribedCoursesByUserId(id);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getFutureSubscribedCoursesByUserId(int)
	 */
	@Transactional
	@Override
	public List<CourseScheduler> getFutureSubscribedCoursesByUserId(int id) {
		return courseSchedulerDao.getFutureSubscribedCoursesByUserId(id);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getFinishedSubscribedCoursesByUserId(int)
	 */
	@Transactional
	@Override
	public List<CourseScheduler> getFinishedSubscribedCoursesByUserId(int id) {
		return courseSchedulerDao.getFinishedSubscribedCoursesByUserId(id);
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#getDeletedCourses()
	 */
	@Transactional
	@Override
	public List<CourseScheduler> getDeletedCourses() {
		return courseSchedulerDao.getAllDeletedCourseSchedulers();
	}

	/**
	 * @see com.softserve.service.CourseSchedulerService#restoreCourseScheduler(CourseScheduler)
	 */
	@Transactional
	@Override
	public void restoreCourseScheduler(CourseScheduler course) {
		courseSchedulerDao.setCourseSchedulerDeleted(course, false);
	}

	@Override
	@Transactional
	public List<CourseScheduler> getCourseSchedulersByStartDate(Date date) {
		return courseSchedulerDao.getCourseSchedulersByStartDate(date);
	}

}
