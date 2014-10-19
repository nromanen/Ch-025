package com.softserve.dao.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.CategoryDao;
import com.softserve.entity.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(CategoryDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public void addCategory(Category category) {
		entityManager.persist(category);
		LOG.debug("Add category {}", category.getName());
	}

	@Override
	public void updateCategory(Category category) {
		entityManager.merge(category);
		LOG.debug("Update category = {}", category.getName());
	}

	@Override
	public void deleteCategory(Category category) {
		entityManager.remove(category);
		LOG.debug("Deleted category = {}", category.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Category> getAllCategories() {
		LOG.debug("Get all categories");
		Set<Category> categories = new HashSet<>();
		categories.addAll(entityManager.createQuery("FROM Category")
				.getResultList());
		return categories;
	}

	@Override
	public Category getCategoryById(int id) {
		LOG.debug("Get category with id = {}", id);
		return entityManager.find(Category.class, id);
	}

}
