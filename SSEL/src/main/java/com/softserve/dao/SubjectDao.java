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

	List<Subject> getSubjectsByNamePart(String namePart, int pageNumber, int pageSize, 
			String sortBy, boolean isReverse);

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

	Long getSubjectsQuantityByNamePart(String namePart);
	
	List<Subject> getSubjectsByCategoryIdWithLimit(int categoryId, int pageNumber, int pageSize,
			String sortBy, boolean isReverse);
	
	List<Subject> getDeletedSubjects();
	
	List<Subject> getAllSubjectsWithSchedulers();
	
	Subject getSubjectByIdWithScheduler(int id);
	
}
