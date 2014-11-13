package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Subject;

public interface SubjectDao {
	/**
	 * Add new subject
	 * @param subject new subject
	 * @return added subject
	 */
	Subject addSubject(Subject subject);
	/**
	 * Manage delete
	 * @param subject subject as marked
	 * @param deleted true - mark as deleted, false - restore
	 */
	void setSubjectDeleted(Subject subject,boolean deleted);
	/**
	 * Update subject 
	 * @param subject updated subject
	 * @return updated 
	 */
	Subject updateSubject(Subject subject);
	/**
	 * Return subject by id
	 * @param id unique subject identifier
	 * @return
	 */
	Subject getSubjectById(int id);
	/**
	 * Return subjects which is marked 
	 * @return list of subject
	 */
	List<Subject> getAllSubjects();

	List<Subject> getSubjectsByCategoryId(int id);

	List<Subject> getSubjectsByUserId(int id);

	List<Subject> getSubjectsByNamePart(String namePart, int pageNumber, int pageSize);

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

	Long getSubjectsQuantityByNamePart(String namePart);
	
	List<Subject> getSubjectsByCategoryIdWithLimit(int categoryId, int pageNumber, int pageSize);
	
	List<Subject> getDeletedSubjects();
	
}
