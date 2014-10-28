package com.softserve.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.CategoryDao;
import com.softserve.entity.Category;
import com.softserve.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	@Transactional
	public Category addCategory(Category category) {
		return categoryDao.addCategory(category);
	}

	@Override
	@Transactional
	public Category updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}

	@Override
	@Transactional
	public void deleteCategory(Category category) {
		categoryDao.deleteCategory(category);
	}

	@Override
	@Transactional
	public Set<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	@Transactional
	public Category getCategoryById(int id) {
		return categoryDao.getCategoryById(id);
	}
}
