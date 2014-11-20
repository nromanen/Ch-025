package com.softserve.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.CourseSchedulerDao;
import com.softserve.entity.CourseScheduler;

@Repository
public class CourseSchedulerDaoImpl implements CourseSchedulerDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(CourseSchedulerDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#addCourseScheduler(CourseScheduler)
	 */
	
	@Override
	public CourseScheduler addCourseScheduler(CourseScheduler courseScheduler) {
		LOG.debug("Add CourseScheduler");
		entityManager.persist(courseScheduler);
		return courseScheduler;
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#setCourseSchedulerDeleted(CourseScheduler, boolean)
	 */
	
	@Override
	public void setCourseSchedulerDeleted(CourseScheduler courseScheduler, boolean deleted) {
		Query query = entityManager
				.createQuery("UPDATE CourseScheduler c SET c.isDeleted = :del WHERE c.id = :id");
		query.setParameter("id", courseScheduler.getId());
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted = {} courseScheduler(id = {})", deleted,
					courseScheduler.getId());
		} else {
			LOG.warn("Tried to delete courseScheduler(id = {})",
					courseScheduler.getId());
		}
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#updateCourseScheduler(CourseScheduler)
	 */
	
	@Override
	public CourseScheduler updateCourseScheduler(CourseScheduler courseScheduler) {
		LOG.debug("Update category");
		entityManager.merge(courseScheduler);
		return courseScheduler;
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getCourseSchedulerById(int)
	 */
	
	@Override
	public CourseScheduler getCourseSchedulerById(int id) {
		LOG.debug("Get courseScheduler with id = {}", id);
		return entityManager.find(CourseScheduler.class, id);
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getAllCourseScheduleres()
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getAllCourseScheduleres() {
		LOG.debug("Get all courseSchedulers");
		return entityManager.createQuery("FROM CourseScheduler c WHERE c.isDeleted = :val")
				.setParameter("val",false)
				.getResultList();
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getCourseScheduleresBySubjectId(int)
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getCourseScheduleresBySubjectId(int id) {
		Query query = entityManager.createQuery("FROM CourseScheduler c "
				+ "WHERE c.subject.id = :id AND c.isDeleted = :val");
		query.setParameter("id", id);
		query.setParameter("val", false);
		LOG.debug("Get all topics by block id = {}", id);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getActiveSubscribedCoursesByUserId(int)
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getActiveSubscribedCoursesByUserId(int id) {
		LOG.debug("Get all course schedulers by user id = {}", id);
		Query query = entityManager
				.createQuery("select gr.course from Group gr, StudentGroup sgr "
						+ "where sgr.group.id = gr.id and sgr.user.id = :id and "
						+ "(current_timestamp() between gr.course.start and gr.course.end) and gr.course.isDeleted = :val");
		query.setParameter("id", id);
		query.setParameter("val",false);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getCourseSchedulersBySubjectUserId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getCourseSchedulersBySubjectUserId(int id) {
		LOG.debug("Get all course schedulers by user id = {}", id);
		Query query = entityManager.createQuery("FROM CourseScheduler c "
				+ "WHERE c.subject.user.id = :id and c.isDeleted = :val");
		query.setParameter("id", id);
		query.setParameter("val",false);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getFutureSubscribedCoursesByUserId(int)
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getFutureSubscribedCoursesByUserId(int id) {
		LOG.debug("Get all course schedulers by user id = {}", id);
		Query query = entityManager
				.createQuery("select gr.course from Group gr, StudentGroup sgr "
						+ "where sgr.group.id = gr.id and sgr.user.id = :id and "
						+ "gr.course.start > current_timestamp() and gr.course.isDeleted = :val");
		query.setParameter("id", id);
		query.setParameter("val",false);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getFinishedSubscribedCoursesByUserId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getFinishedSubscribedCoursesByUserId(int id) {
		LOG.debug("Get all course schedulers by user id = {}", id);
		Query query = entityManager
				.createQuery("select gr.course from Group gr, StudentGroup sgr "
						+ "where sgr.group.id = gr.id and sgr.user.id = :id and "
						+ "gr.course.end < current_timestamp() and gr.course.isDeleted = :val");
		query.setParameter("id", id);
		query.setParameter("val",false);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.CourseSchedulerDao#getAllDeletedCourseSchedulers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getAllDeletedCourseSchedulers() {
		LOG.debug("Get all deleted course scheduler");
		Query query = entityManager.createQuery("FROM CourseScheduler c WHERE c.isDeleted = :val");
		query.setParameter("val",true);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getCourseSchedulersByStartDate(Date date) {
		LOG.debug("Get all course schedulers by start date = {}", date);
		Query query = entityManager
				.createQuery("FROM CourseScheduler c WHERE c.start = :date");
		query.setParameter("date", date, TemporalType.DATE);
		return query.getResultList();
	}

}
