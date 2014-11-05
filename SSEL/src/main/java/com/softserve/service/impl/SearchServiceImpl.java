package com.softserve.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.dao.CategoryDao;
import com.softserve.dao.SubjectDao;
import com.softserve.entity.Category;
import com.softserve.entity.Subject;
import com.softserve.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private SubjectDao subjectDao;

	@Override
	@Transactional
	public List<Category> getCategoriesByNamePart(String namePart) {
		return categoryDao.getCategoriesByNamePart(namePart);
	}
	

	@Override
	public List<Subject> getSubjectsByNamePart(String namePart) {
		return subjectDao.getSubjectsByNamePart(namePart);
	}

}
