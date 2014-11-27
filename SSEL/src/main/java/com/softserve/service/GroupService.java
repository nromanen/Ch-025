package com.softserve.service;

import java.util.List;


import com.softserve.entity.Group;
/**
 * Specify service for group
 * @author
 *
 */
public interface GroupService {
	/**
	 * Add new group
	 * @param group new group
	 * @return added group
	 */
	Group addGroup(Group group);
	/**
	 * Mark group as deleted
	 * @param group group which is marked
	 */
	void deleteGroup(Group group);
	/**
	 * Restore group which was marked as deleted
	 * @param group group which will restored
	 */
	void restoreGroup(Group group);
	/**
	 * Return group for scheduler
	 * @param schedulerId  unique course scheduler id
	 * @return group
	 */
	Group getGroupByScheduler(int schedulerId);
	/**
	 * Return groups for student
	 * @param userId unique student identifier 
	 * @return list of groups
	 */
	List<Group> getGroupsByStudent(int userId);
	/**
	 * Return groups which is marked as deleted
	 * @return list of groups
	 */
	List<Group> getDeletedGroups();
	/**
	 * Return group by id
	 * @param groupId unique group identifier
	 * @return group
	 */
	Group getGroupById(int groupId);
	/**
	 * Update group
	 * @param group updated group
	 * @return updated group
	 */
	Group updateGroup(Group group);
}
