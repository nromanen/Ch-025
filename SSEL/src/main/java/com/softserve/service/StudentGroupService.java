package com.softserve.service;

import java.util.List;

import com.softserve.entity.StudentGroup;

public interface StudentGroupService {
	public void addStudentGroup(StudentGroup studentGroup);

	public void updateStudentGroup(StudentGroup studentGroup);

	public void deleteStudentGroup(StudentGroup studentGroup);

	public StudentGroup getStudentGroupById(int id);

	public List<StudentGroup> getStudentGroupsByGroupNumber(int number);

	public List<StudentGroup> getAllStudentGroups();
	
	public int getGroupNumberByCourse(int courseId);
	
	public int getNextGroupNumber();

}


