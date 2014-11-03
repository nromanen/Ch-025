package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.GroupDao;
import com.softserve.entity.Group;
/**
 * Implement functionality, specified in GroupDao
 * @author Анатолій
 *
 */
@Repository
public class GroupDaoImp implements GroupDao{

	private static final Logger LOG = LoggerFactory
			.getLogger(StudentGroupDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;
	
	@Override
	public Group addGroup(Group newGroup) {
		LOG.debug("Add studentGroup (number = {})", newGroup.getGroupId());
		entityManager.persist(newGroup);
		return newGroup;
	}

	@Override
	public Group updateGroup(Group updatedGroup) {
		LOG.debug("Update studentGroup (number = {})", updatedGroup.getGroupId());
		entityManager.merge(updatedGroup);
		return updatedGroup;
	}

	@Override
	public void deleteGroup(Group group) {
		LOG.debug("Delete studentGroup (number = {})", group.getGroupId());
		Query query = entityManager.createQuery("DELETE FROM Group g WHERE g.groupId = :id");
		query.setParameter("id", group.getGroupId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted studentGroup(id = {})", group.getGroupId());
		} else {
			LOG.warn("Tried to delete studentGroup(id = {})",
					group.getGroupId());
		}
	}

	@Override
	public Group getGroupById(int groupId) {
		Query query = entityManager.createQuery("SELECT g FROM Group g WHERE g.groupId = :id");
		query.setParameter("id", groupId);
		return (query.getResultList().size() == 0) ? null : (Group) query.getSingleResult();
	}

	@Override
	public Group getGroupByScheduler(int schedulerId) {
		Query query = entityManager.createQuery("SELECT g FROM Group g WHERE g.course.id = :id");
		query.setParameter("id", schedulerId);
		return (query.getResultList().size() == 0) ? null : (Group) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroupsByStudent(int userId) {
		Query query = entityManager.createQuery("SELECT g FROM Group g, StudentGroup sg WHERE g.groupId = sg.group.groupId"
				+ " and sg.user.id = :id");
		query.setParameter("id", userId);
		return query.getResultList();
	}

}
