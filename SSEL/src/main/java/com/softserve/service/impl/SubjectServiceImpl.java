package com.softserve.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.dao.SubjectDao;
import com.softserve.entity.Subject;
import com.softserve.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectDao subjectDao;

	@Override
	@Transactional
	public Subject addSubject(Subject subject) {
		return subjectDao.addSubject(subject);
	}

	@Override
	@Transactional
	public void deleteSubject(Subject subject) {
		subjectDao.setSubjectDeleted(subject, true);
	}

	@Override
	@Transactional
	public Subject updateSubject(Subject subject) {
		return subjectDao.updateSubject(subject);
	}

	@Override
	@Transactional
	public Subject getSubjectById(int id) {
		return subjectDao.getSubjectById(id);
	}

	@Override
	@Transactional
	public List<Subject> getAllSubjects() {
		return subjectDao.getAllSubjects();
	}

	@Override
	@Transactional
	public List<Subject> getSubjectsByCategoryId(int id) {
		return subjectDao.getSubjectsByCategoryId(id);
	}

	@Override
	public List<Subject> getSubjectsByUserId(int id) {
		return subjectDao.getSubjectsByUserId(id);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectsByPage(int startPosition, int limitLength, String sortBy, String sortMethod) {
		return subjectDao.getSubjectsByPage(startPosition, limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectsByNameByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return subjectDao.getSubjectsByNameByPage(searchText, startPosition,
				limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectsByCategoryByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return subjectDao.getSubjectsByCategoryByPage(searchText,
				startPosition, limitLength, sortBy, sortMethod);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectsByTextByPage(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		return subjectDao.getSubjectsByTextByPage(searchText, startPosition, limitLength, sortBy, sortMethod);
	}

	@Override
	public long getCountOfSubjects() {
		return subjectDao.getCountOfSubjects();
	}

	@Override
	public long getCountOfSubjectsByName(String searchName) {
		return subjectDao.getCountOfSubjectsByName(searchName);
	}

	@Override
	public long getCountOfSubjectsByCategory(String searchCategory) {
		return subjectDao.getCountOfSubjectsByCategory(searchCategory);
	}

	@Override
	public long getCountOfSubjectsByText(String searchText) {
		return subjectDao.getCountOfSubjectsByText(searchText);
	}

	@Override
	@Transactional
	public void restoreSubject(Subject subject) {
		subjectDao.setSubjectDeleted(subject, false);
	}

	@Override
	public List<Subject> getAllSubjectsWithSchedulers() {
		return subjectDao.getAllSubjectsWithSchedulers();
	}

	@Override
	public Subject getSubjectByIdWithScheduler(int id) {
		return subjectDao.getSubjectByIdWithScheduler(id);
	}

}
