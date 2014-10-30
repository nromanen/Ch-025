package com.softserve.service;

import java.util.List;

import com.softserve.entity.Category;

public interface CategoryService {

	public Category addCategory(Category category);

	public Category updateCategory(Category category);

	public void deleteCategory(Category category);

	public List<Category> getAllCategories();

	public Category getCategoryById(int id);
}
