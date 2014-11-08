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
 * @author Анатолій
 *
 */
@Service
public class GroupServiceImp implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Transactional
	@Override
	public Group addGroup(Group group) {
		return groupDao.addGroup(group);
	}
	
	@Transactional
	@Override
	public Group getGroupByScheduler(int schedulerId) {
		return groupDao.getGroupByScheduler(schedulerId);
	}
	
	@Transactional
	@Override
	public List<Group> getGroupsByStudent(int userId) {
		return groupDao.getGroupsByStudent(userId);
	}

	@Transactional
	@Override
	public void deleteGroup(Group group) {
		groupDao.deleteGroup(group);		
	}

}
