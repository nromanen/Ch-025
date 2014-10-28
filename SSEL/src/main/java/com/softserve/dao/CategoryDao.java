package com.softserve.dao;

import java.util.Set;

import com.softserve.entity.Category;

public interface CategoryDao {

	public Category addCategory(Category category);

	public Category updateCategory(Category category);

	public void deleteCategory(Category category);

	public Set<Category> getAllCategories();

	public Category getCategoryById(int id);
}
