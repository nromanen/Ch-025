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

	List<Subject> getSubjectsVsLimit(int startPosition, int limitLength, String sortBy, String sortMethod);

	List<Subject> getSubjectsByNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	List<Subject> getSubjectsByCategoryVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);
	
	List<Subject> getSubjectsByTextVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	long getSubjectsCount();

	long getSubjectsByNameCount(String searchName);

	long getSubjectsByCategoryCount(String searchCategory);
	
	long getSubjectsByTextCount(String searchText);
}
