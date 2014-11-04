package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Group;
/**
 * Specify group data access object
 * @author Анатолій
 *
 */
public interface GroupDao {
	/**
	 * Add new group into groups list
	 * @param newGroup new group
	 * @return added group
	 */
	public Group addGroup(Group newGroup);
	
	/**
	 * Update information about some group
	 * @param updatedGroup updated group information
	 * @return updated group
	 */
	public Group updateGroup(Group updatedGroup);
	
	/**
	 * Delete group from groups list
	 * @param group group that'll be deleted
	 */
	public void deleteGroup(Group group);
	
	/**
	 * Return group from groups list by id
	 * @param groupId unique group identifier
	 * @return group if exists, and null otherwise
	 */
	public Group getGroupById(int groupId);
	
	/**
	 * Return group from groups list by course 
	 * @param schedulerId unique course identifier 
	 * @return
	 */
	public Group getGroupByScheduler(int schedulerId);
	
	/**
	 * Return groups from some student
	 * @param userId unique user identifier with STUDENT role 
	 * @return
	 */
	public List<Group> getGroupsByStudent(int userId);
	
}
