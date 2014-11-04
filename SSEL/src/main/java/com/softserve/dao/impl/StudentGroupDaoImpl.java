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
	public StudentGroup addStudentGroup(StudentGroup studentGroup) {
		LOG.debug("Add studentGroup (number = {})",
				studentGroup.getGroupNumber());
		entityManager.persist(studentGroup);
		return studentGroup;
	}

	@Override
	public StudentGroup updateStudentGroup(StudentGroup studentGroup) {
		LOG.debug("Update studentGroup (number = {})",
				studentGroup.getGroupNumber());
		entityManager.merge(studentGroup);
		return studentGroup;
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
		LOG.debug("Get all topics by group number = {}", number);
		Query query = entityManager.createQuery("FROM StudentGroup s "
				+ "WHERE s.group.groupId = :number");
		query.setParameter("number", number);
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
	public StudentGroup getStudentGroupByGroupAndUser(int groupId, int userId) {
		LOG.debug("Get student group by group id and user id");
		Query query = entityManager.createQuery("SELECT s FROM StudentGroup s WHERE s.group.groupId = :gid AND "
				+ "s.user.id = :uid");
		query.setParameter("gid", groupId);
		query.setParameter("uid", userId);
		List<StudentGroup> groups = query.getResultList();
		return (groups.size() == 0) ? null : groups.get(0);
	}

}