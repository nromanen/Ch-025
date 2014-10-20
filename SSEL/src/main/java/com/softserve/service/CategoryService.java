package com.softserve.service;

import java.util.Set;

import com.softserve.entity.Category;

public interface CategoryService {

	public void addCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(Category category);

	public Set<Category> getAllCategories();

	public Category getCategoryById(int id);
}
