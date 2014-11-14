package com.softserve.service;

import java.util.List;

import com.softserve.entity.StudentGroup;

public interface StudentGroupService {
	StudentGroup addStudentGroup(StudentGroup studentGroup);

	StudentGroup updateStudentGroup(StudentGroup studentGroup);

	void deleteStudentGroup(StudentGroup studentGroup);

	StudentGroup getStudentGroupById(int id);

	List<StudentGroup> getStudentGroupsByGroupNumber(int number);

	List<StudentGroup> getAllStudentGroups();

	StudentGroup getStudentGroupByUserAndGroupId(int userId, int groupId);

	List<StudentGroup> getStudentGroupsByCourseSheduledId(int id);
}
