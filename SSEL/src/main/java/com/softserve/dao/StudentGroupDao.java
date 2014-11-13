package com.softserve.dao;

import java.util.List;

import com.softserve.entity.StudentGroup;

public interface StudentGroupDao {

	StudentGroup addStudentGroup(StudentGroup studentGroup);

	StudentGroup updateStudentGroup(StudentGroup studentGroup);

	void deleteStudentGroup(StudentGroup studentGroup);

	StudentGroup getStudentGroupById(int id);

	List<StudentGroup> getStudentGroupsByGroupNumber(int number);
	
	StudentGroup getStudentGroupByGroupAndUser(int groupId, int userId);

	List<StudentGroup> getAllStudentGroups();

}

