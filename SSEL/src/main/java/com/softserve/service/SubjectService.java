package com.softserve.service;

import java.util.List;

import com.softserve.entity.Subject;
/**
 * Specify subject functionality
 * @author Anatoliy
 *
 */
public interface SubjectService {

	Subject addSubject(Subject subject);

	void deleteSubject(Subject subject);
	
	void restoreSubject(Subject subject); 

	Subject updateSubject(Subject subject);

	Subject getSubjectById(int id);

	List<Subject> getAllSubjects();

	List<Subject> getSubjectsByCategoryId(int id);

	List<Subject> getSubjectsByUserId(int id);

	List<Subject> getSubjectsByPage(int startPosition, int limitLength, String sortBy, String sortMethod);

	List<Subject> getSubjectsByNameByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	List<Subject> getSubjectsByCategoryByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);
	
	List<Subject> getSubjectsByTextByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	long getCountOfSubjects();

	long getCountOfSubjectsByName(String searchName);

	long getCountOfSubjectsByCategory(String searchCategory);
	
	long getCountOfSubjectsByText(String searchText);
	
	List<Subject> getAllSubjectsWithSchedulers();
	
	Subject getSubjectByIdWithScheduler(int id);
}
