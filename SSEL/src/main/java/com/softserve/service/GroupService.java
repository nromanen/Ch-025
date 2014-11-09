package com.softserve.service;

import java.util.List;


import com.softserve.entity.Group;
/**
 * Specify service for group
 * @author
 *
 */
public interface GroupService {
	
	public Group addGroup(Group group);
	
	public void deleteGroup(Group group);
	
	public Group getGroupByScheduler(int schedulerId);
	
	public List<Group> getGroupsByStudent(int userId);
}
