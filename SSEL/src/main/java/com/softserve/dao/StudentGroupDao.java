package com.softserve.dao;

import java.util.List;

import com.softserve.entity.StudentGroup;

public interface StudentGroupDao {

	public StudentGroup addStudentGroup(StudentGroup studentGroup);

	public StudentGroup updateStudentGroup(StudentGroup studentGroup);

	public void deleteStudentGroup(StudentGroup studentGroup);

	public StudentGroup getStudentGroupById(int id);

	public List<StudentGroup> getStudentGroupsByGroupNumber(int number);

	public List<StudentGroup> getAllStudentGroups();
	
	public StudentGroup getStudentGroupByUserAndCourseId(int userId, int courseScheduler);
	
	public int getStudentGroupNumberByCourse(int courseScheduler);
	
	public int getNextGroupNumber();
}

