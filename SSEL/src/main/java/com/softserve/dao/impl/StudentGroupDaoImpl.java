package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.StudentGroupDao;
import com.softserve.entity.StudentGroup;

@Repository
public class StudentGroupDaoImpl implements StudentGroupDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(StudentGroupDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public void addStudentGroup(StudentGroup studentGroup) {
		entityManager.persist(studentGroup);
		LOG.debug("Add studentGroup (number = {})",
				studentGroup.getGroupNumber());
	}

	@Override
	public void updateStudentGroup(StudentGroup studentGroup) {
		entityManager.merge(studentGroup);
		LOG.debug("Update studentGroup (number = {})",
				studentGroup.getGroupNumber());
	}

	@Override
	public void deleteStudentGroup(StudentGroup studentGroup) {
		Query query = entityManager
				.createQuery("DELETE FROM StudentGroup s WHERE s.id = :id");
		query.setParameter("id", studentGroup.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted studentGroup(id = {})", studentGroup.getId());
		} else {
			LOG.warn("Tried to delete studentGroup(id = {})",
					studentGroup.getId());
		}
	}

	@Override
	public StudentGroup getStudentGroupById(int id) {
		LOG.debug("Get StudentGroup with id = {}", id);
		return entityManager.find(StudentGroup.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentGroup> getStudentGroupsByGroupNumber(int number) {
		Query query = entityManager.createQuery("FROM StudentGroup s "
				+ "WHERE s.groupNumber = :number");
		query.setParameter("number", number);

		LOG.debug("Get all topics by group number = {}", number);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentGroup> getAllStudentGroups() {
		LOG.debug("Get all studentGroups");
		return entityManager.createQuery("FROM StudentGroup").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public StudentGroup getStudentGroupByUserAndCourseId(int userId, int courseScheduler) {
		LOG.debug("Get StudentGroup by user");
		Query query = entityManager.createQuery("select sg from StudentGroup sg "
				+ "where sg.user.id=:user and sg.courseScheduler.id=:course");
		query.setParameter("user", userId);
		query.setParameter("course", courseScheduler);
		List<StudentGroup> results = query.getResultList();
		return (results.size() == 0) ? null : results.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getStudentGroupNumberByCourse(int courseScheduler) {
		LOG.debug("Get StudentGroup by course");
		Query query = entityManager.createQuery("select distinct sg from StudentGroup sg where sg.courseScheduler.id = :id");
		query.setParameter("id", courseScheduler);
		List<StudentGroup> results = query.getResultList();
		return (results.size() == 0) ? -1 : results.get(0).getGroupNumber();
	}

	@Override
	public int getNextGroupNumber() {
		LOG.debug("Get StudentGroup by course");
		Query query = entityManager.createQuery("select max(sg.groupNumber) from StudentGroup sg");
		Integer res = (Integer) query.getSingleResult();
		return (res != null) ? res+1 : -1;
	}


}


