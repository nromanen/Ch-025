package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Group;
/**
 * Specify group data access object
 * @author 
 *
 */
public interface GroupDao {
	/**
	 * Add new group into groups list
	 * @param newGroup new group
	 * @return added group
	 */
	Group addGroup(Group newGroup);
	
	/**
	 * Update information about some group
	 * @param updatedGroup updated group information
	 * @return updated group
	 */
	Group updateGroup(Group updatedGroup);
	
	/**
	 * Delete group from groups list
	 * @param group group that'll be deleted
	 * @param deleted true - mark as deleted, false - restore
	 */
	void setGroupDeleted(Group group, boolean deleted);
	
	/**
	 * Return group from groups list by id
	 * @param groupId unique group identifier
	 * @return group if exists, and null otherwise
	 */
	Group getGroupById(int groupId);
	
	/**
	 * Return group from groups list by course 
	 * @param schedulerId unique course identifier 
	 * @return
	 */
	Group getGroupByScheduler(int schedulerId);
	
	/**
	 * Return groups from some student
	 * @param userId unique user identifier with STUDENT role 
	 * @return
	 */
	List<Group> getGroupsByStudent(int userId);
	/**
	 * Return all groups which is not deleted
	 * @return list of groups
	 */
	List<Group> getAllGroups();
	/**
	 * Return all deleted groups
	 * @return list of groups
	 */
	List<Group> getAllDeletedGroups();
}
