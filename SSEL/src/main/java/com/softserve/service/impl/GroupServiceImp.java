package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.GroupDao;
import com.softserve.entity.Group;
import com.softserve.service.GroupService;

/**
 * Implements functionality specified in GroupService
 * @author Anatoliy
 *
 */
@Service
public class GroupServiceImp implements GroupService{

	@Autowired
	private GroupDao groupDao;
	/**
	 * @see com.softserve.service.GroupService#addGroup(Group)
	 */
	@Transactional
	@Override
	public Group addGroup(Group group) {
		return groupDao.addGroup(group);
	}
	/**
	 * @see com.softserve.service.GroupService#getGroupByScheduler(int)
	 */
	@Transactional
	@Override
	public Group getGroupByScheduler(int schedulerId) {
		return groupDao.getGroupByScheduler(schedulerId);
	}
	/**
	 * @see com.softserve.service.GroupService#getGroupsByStudent(int)
	 */
	@Transactional
	@Override
	public List<Group> getGroupsByStudent(int userId) {
		return groupDao.getGroupsByStudent(userId);
	}
	/**
	 * @see com.softserve.service.GroupService#deleteGroup(Group)
	 */
	@Transactional
	@Override
	public void deleteGroup(Group group) {
		groupDao.setGroupDeleted(group, true);		
	}
	/**
	 * @see com.softserve.service.GroupService#restoreGroup(Group)
	 */
	@Transactional
	@Override
	public void restoreGroup(Group group) {
		groupDao.setGroupDeleted(group, false);		
	}
	/**
	 * @see com.softserve.service.GroupService#getDeletedGroups()
	 */
	@Transactional
	@Override
	public List<Group> getDeletedGroups() {
		return groupDao.getAllDeletedGroups();
	}
	/**
	 * @see com.softserve.service.GroupService#getGroupById(int)
	 */
	@Transactional
	@Override
	public Group getGroupById(int groupId) {
		return groupDao.getGroupById(groupId);
	}
	/**
	 * @see com.softserve.service.GroupService#updateGroup(Group)
	 */
	@Transactional
	@Override
	public Group updateGroup(Group group) {
		return groupDao.updateGroup(group);
	}

}
