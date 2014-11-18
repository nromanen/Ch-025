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
 * @author 
 *
 */
@Repository
public class GroupDaoImp implements GroupDao{

	private static final Logger LOG = LoggerFactory
			.getLogger(StudentGroupDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;
	/**
	 * @see com.softserve.dao.GroupDao#addGroup(Group)
	 */
	@Override
	public Group addGroup(Group newGroup) {
		LOG.debug("Add studentGroup (number = {})", newGroup.getGroupId());
		entityManager.persist(newGroup);
		return newGroup;
	}
	/**
	 * @see com.softserve.dao.GroupDao#updateGroup(Group)
	 */
	@Override
	public Group updateGroup(Group updatedGroup) {
		LOG.debug("Update studentGroup (number = {})", updatedGroup.getGroupId());
		entityManager.merge(updatedGroup);
		return updatedGroup;
	}
	/**
	 * @see com.softserve.dao.GroupDao#setGroupDeleted(Group, boolean)
	 */
	@Override
	public void setGroupDeleted(Group group, boolean deleted) {
		LOG.debug("Delete = {} group (number = {})", deleted, group.getGroupId());
		Query query = entityManager.createQuery("UPDATE Group g SET g.isDeleted = :del WHERE g.groupId = :id");
		query.setParameter("id", group.getGroupId());
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted = {} studentGroup(id = {})", deleted,group.getGroupId());
		} else {
			LOG.warn("Tried to delete studentGroup(id = {})",
					group.getGroupId());
		}
	}
	/**
	 * @see com.softserve.dao.GroupDao#getGroupById(int)
	 */
	@Override
	public Group getGroupById(int groupId) {
		Query query = entityManager.createQuery("SELECT g FROM Group g WHERE g.groupId = :id");
		query.setParameter("id", groupId);
		return (query.getResultList().size() == 0) ? null : (Group) query.getSingleResult();
	}
	/**
	 * @see com.softserve.dao.GroupDao#getGroupByScheduler(int)
	 */
	@Override
	public Group getGroupByScheduler(int schedulerId) {
		Query query = entityManager.createQuery("SELECT g FROM Group g WHERE g.course.id = :id and g.isDeleted = :val");
		query.setParameter("id", schedulerId);
		query.setParameter("val",false);
		return (query.getResultList().size() == 0) ? null : (Group) query.getSingleResult();
	}
	/**
	 * @see com.softserve.dao.GroupDao#getGroupsByStudent(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroupsByStudent(int userId) {
		Query query = entityManager.createQuery("SELECT g FROM Group g, StudentGroup sg WHERE g.groupId = sg.group.groupId"
				+ " and sg.user.id = :id and g.isDeleted = :val");
		query.setParameter("id", userId);
		query.setParameter("val",false);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.GroupDao#getAllGroups()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAllGroups() {
		LOG.debug("Get all groups");
		return entityManager.createQuery("FROM Group g WHERE g.isDeleted = :val")
				.setParameter("val",false)
				.getResultList();
	}
	/**
	 * @see com.softserve.dao.GroupDao#getAllDeletedGroups()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAllDeletedGroups() {
		LOG.debug("Get all groups mark as deleted");
		return entityManager.createQuery("FROM Group g WHERE g.deleted = :val")
				.setParameter("val",true)
				.getResultList();
	}

}
