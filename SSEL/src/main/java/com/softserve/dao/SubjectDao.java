package com.softserve.dao;

import java.util.List;
import java.util.Set;

import com.softserve.entity.Subject;

public interface SubjectDao {

	public Subject addSubject(Subject subject);

	public void deleteSubject(Subject subject);

	public Subject updateSubject(Subject subject);

	public Subject getSubjectById(int id);

	public Set<Subject> getAllSubjects();
	
	public List<Subject> getSubjectsByCategoryId(int id);

	List<Subject> getSubjectsByUserId(int id);
}
