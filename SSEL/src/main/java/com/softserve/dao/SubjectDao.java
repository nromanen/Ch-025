package com.softserve.dao;

import java.util.List;
import java.util.Set;

import com.softserve.entity.Subject;

public interface SubjectDao {

	public void addSubject(Subject subject);

	public void deleteSubject(Subject subject);

	public void updateSubject(Subject subject);

	public Subject getSubjectById(int id);

	public Set<Subject> getAllSubjects();
	
	public List<Subject> getSubjectsByCategoryId(int id);
}
