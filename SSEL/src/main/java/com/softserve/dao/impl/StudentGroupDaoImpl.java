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
		entityManager.remove(studentGroup);
		LOG.debug("Deleted studentGroup");
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

}