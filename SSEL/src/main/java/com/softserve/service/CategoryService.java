package com.softserve.service;

import java.util.List;

import com.softserve.entity.Category;
/**
 * Specify category service functionality
 * @author Roma Khomyshyn
 *
 */
public interface CategoryService {
	/**
	 * Add new category
	 * @param category new category
	 * @return added category
	 */
	Category addCategory(Category category);
	/**
	 * Update category
	 * @param category updated category
	 * @return updated category
	 */
	Category updateCategory(Category category);
	/**
	 * Mark category as deleted
	 * @param category category which will marked
	 */
	void deleteCategory(Category category);
	/**
	 * Return all categories which is not marked as deleted
	 * @return list of categories
	 */
	List<Category> getAllCategories();
	/**
	 * Return category by id
	 * @param id unique category identifier
	 * @return category
	 */
	Category getCategoryById(int id);
	/**
	 * Restore category which was marked as deleted
	 * @param category category which was marked as deleted
	 */
	void restoreCategory(Category category);
	/**
	 * Return categories marked as deleted
	 * @return list of categories
	 */
	List<Category> getDeletedCategories();
}
