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
	Category addCategory(Category category);

	/**
	 * Update category information
	 * 
	 * @param category
	 *            updated into database
	 * @return updated category
	 */
	Category updateCategory(Category category);

	/**
	 * Manage category delete
	 * 
	 * @param category
	 *            some category
	 * @param deleted
	 *            true - mark as deleted, false - restore
	 */
	void setCategoryDeleted(Category category, boolean deleted);

	/**
	 * Return all categories from database
	 * 
	 * @return list of category object if exists or empty list
	 */
	List<Category> getAllCategories();

	/**
	 * Return all deleted categories
	 * 
	 * @return list of categories
	 */
	List<Category> getAllDeletedCategories();

	/**
	 * Return category record by category id
	 * 
	 * @param id
	 *            is a id of record in database
	 * @return category object if exists or null otherwise
	 */
	Category getCategoryById(int id);

	/**
	 * Return all categories from database, where category name like namePart
	 * 
	 * @param namePart
	 *            is a name part of record in database
	 * @return list of category object if exists or empty list
	 */
	List<Category> getCategoriesByNamePart(String namePart);

	Long getCategoriesQuantityByNamePart(String namePart);
}
