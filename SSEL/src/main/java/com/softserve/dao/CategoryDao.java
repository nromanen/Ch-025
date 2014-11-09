package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Category;

/**
 * Basic Data Access Object interface. Provides CRUD operations for
 * {@link Category} objects and others operations.
 * 
 * @author Roma Khomyshyn
 * @author Andriy Shutka
 *
 */
public interface CategoryDao {
	/**
	 * Add new category into database
	 * 
	 * @param category
	 *            new category
	 * @return added category
	 */
	public Category addCategory(Category category);

	/**
	 * Update category information
	 * 
	 * @param category
	 *            updated into database
	 * @return updated category
	 */
	public Category updateCategory(Category category);

	/**
	 * Delete category record from database
	 * 
	 * @param category
	 *            record to delete
	 */
	public void deleteCategory(Category category);

	/**
	 * Return all categories from database
	 * 
	 * @return list of category object if exists or empty list
	 */
	public List<Category> getAllCategories();

	/**
	 * Return category record by category id
	 * 
	 * @param id
	 *            is a id of record in database
	 * @return category object if exists or null otherwise
	 */
	public Category getCategoryById(int id);

	/**
	 * Return all categories from database, where category name like namePart
	 * 
	 * @param namePart
	 *            is a name part of record in database
	 * @return list of category object if exists or empty list
	 */
	List<Category> getCategoriesByNamePart(String namePart);
}
