package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Subject;

public interface SubjectDao {

	public Subject addSubject(Subject subject);

	public void deleteSubject(Subject subject);

	public Subject updateSubject(Subject subject);

	public Subject getSubjectById(int id);

	public List<Subject> getAllSubjects();

	public List<Subject> getSubjectsByCategoryId(int id);

	List<Subject> getSubjectsByUserId(int id);

	List<Subject> getSubjectsByNamePart(String namePart, int pageNumber, int pageSize);

	public List<Subject> getSubjectsVsLimit(int startPosition, int limitLength, String sortBy, String sortMethod);

	public List<Subject> getSubjectsByNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	public List<Subject> getSubjectsByCategoryVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	public List<Subject> getSubjectsByTextVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod);

	public long getSubjectsCount();

	public long getSubjectsByNameCount(String searchName);

	public long getSubjectsByCategoryCount(String searchCategory);

	public long getSubjectsByTextCount(String searchText);

	
	Long getSubjectsQuantityByNamePart(String namePart);
}
