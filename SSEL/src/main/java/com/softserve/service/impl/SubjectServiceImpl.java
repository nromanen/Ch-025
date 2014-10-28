package com.softserve.service.impl;

import java.util.List;
import java.util.Set;

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
		subjectDao.deleteSubject(subject);
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
	public Set<Subject> getAllSubjects() {
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

}
