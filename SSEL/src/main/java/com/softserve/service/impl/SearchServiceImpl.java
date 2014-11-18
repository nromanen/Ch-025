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
	public List<Category> getCategoriesByNamePart(String namePart, int pageNumber, int pageSize) {
		return categoryDao.getCategoriesByNamePart(namePart, pageNumber, pageSize);
	}
	

	@Override
	@Transactional
	public List<Subject> getSubjectsByNamePart(String namePart, int pageNumber, int pageSize, 
			String sortBy, boolean isReverse) {
		return subjectDao.getSubjectsByNamePart(namePart, pageNumber, pageSize, sortBy, isReverse);
	}


	@Override
	public Long getSubjectsQuantityByNamePart(String namePart) {
		return subjectDao.getSubjectsQuantityByNamePart(namePart);
	}


	@Override
	public Long getCategoriesQuantityByNamePart(String namePart) {
		return categoryDao.getCategoriesQuantityByNamePart(namePart);
	}


	@Override
	@Transactional
	public List<Subject> getSubjectsByCategoryIdWithLimit(int categoryId, int pageNumber, int pageSize,
			String sortBy, boolean isReverse) {
		return subjectDao.getSubjectsByCategoryIdWithLimit(categoryId, pageNumber, pageSize, sortBy, isReverse);
	}

}
