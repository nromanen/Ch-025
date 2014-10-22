package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	@Override
	public void addCourseScheduler(CourseScheduler courseScheduler) {
		entityManager.persist(courseScheduler);
		LOG.debug("Add CourseScheduler");
	}

	@Override
	public void deleteCourseScheduler(CourseScheduler courseScheduler) {
		Query query = entityManager
				.createQuery("DELETE FROM CourseScheduler c WHERE c.id = :id");
		query.setParameter("id", courseScheduler.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted courseScheduler(id = {})",
					courseScheduler.getId());
		} else {
			LOG.warn("Tried to delete courseScheduler(id = {})",
					courseScheduler.getId());
		}
	}

	@Override
	public void updateCourseScheduler(CourseScheduler courseScheduler) {
		entityManager.merge(courseScheduler);
		LOG.debug("Update category");
	}

	@Override
	public CourseScheduler getCourseSchedulerById(int id) {
		LOG.debug("Get courseScheduler with id = {}", id);
		return entityManager.find(CourseScheduler.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getAllCourseScheduleres() {
		LOG.debug("Get all courseSchedulers");
		return entityManager.createQuery("FROM CourseScheduler")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getCourseScheduleresBySubjectId(int id) {
		Query query = entityManager.createQuery("FROM CourseScheduler c "
				+ "WHERE c.subject.id = :id");
		query.setParameter("id", id);

		LOG.debug("Get all topics by block id = {}", id);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseScheduler> getSubscribedCoursesByUserId(int id) {
		Query query = entityManager.createQuery("select distinct sg.courseScheduler from StudentGroup sg "
				+ "inner join sg.courseScheduler where sg.user.id = :id");
		query.setParameter("id", id);
		LOG.debug("Get all course schedulers by user id = {}", id);
		return query.getResultList();
	}

}
