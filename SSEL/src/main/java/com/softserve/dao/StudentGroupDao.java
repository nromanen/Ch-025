package com.softserve.dao;

import java.util.List;

import com.softserve.entity.StudentGroup;

public interface StudentGroupDao {

	public StudentGroup addStudentGroup(StudentGroup studentGroup);

	public StudentGroup updateStudentGroup(StudentGroup studentGroup);

	public void deleteStudentGroup(StudentGroup studentGroup);

	public StudentGroup getStudentGroupById(int id);

	public List<StudentGroup> getStudentGroupsByGroupNumber(int number);
	
	public StudentGroup getStudentGroupByGroupAndUser(int groupId, int userId);

	public List<StudentGroup> getAllStudentGroups();
	
	public List<StudentGroup> getStudentGroupsByCourseSheduledId(int id);
}

