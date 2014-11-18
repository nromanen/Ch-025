package com.softserve.service.impl;

import java.util.List;

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
	/**
	 * @see com.softserve.service.CategoryService#addCategory(Category)
	 */
	@Override
	@Transactional
	public Category addCategory(Category category) {
		return categoryDao.addCategory(category);
	}
	/**
	 * @see com.softserve.service.CategoryService#updateCategory(Category)
	 */
	@Override
	@Transactional
	public Category updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}
	/**
	 * @see com.softserve.service.CategoryService#deleteCategory(Category)
	 */
	@Override
	@Transactional
	public void deleteCategory(Category category) {
		categoryDao.setCategoryDeleted(category, true);
	}
	/**
	 * @see com.softserve.service.CategoryService#getAllCategories()
	 */
	@Override
	@Transactional
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}
	/**
	 * @see com.softserve.service.CategoryService#getCategoryById(int)
	 */
	@Override
	@Transactional
	public Category getCategoryById(int id) {
		return categoryDao.getCategoryById(id);
	}
	/**
	 * @see com.softserve.service.CategoryService#restoreCategory(Category)
	 */
	@Transactional
	@Override
	public void restoreCategory(Category category) {
		categoryDao.setCategoryDeleted(category, false);		
	}
	/**
	 * @see com.softserve.service.CategoryService#getDeletedCategories()
	 */
	@Transactional
	@Override
	public List<Category> getDeletedCategories() {
		return categoryDao.getAllDeletedCategories();
	}
}
