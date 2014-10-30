package com.softserve.service;

import java.util.List;

import com.softserve.entity.Subject;

public interface SubjectService {

	public Subject addSubject(Subject subject);

	public void deleteSubject(Subject subject);

	public Subject updateSubject(Subject subject);

	public Subject getSubjectById(int id);

	public List<Subject> getAllSubjects();

	public List<Subject> getSubjectsByCategoryId(int id);
	
	public List<Subject> getSubjectsByUserId(int id);
}
