package com.softserve.dao.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public Category addCategory(Category category) {
		LOG.debug("Add category {}", category.getName());
		entityManager.persist(category);
		return category;
	}

	@Override
	public Category updateCategory(Category category) {
		LOG.debug("Update category = {}", category.getName());
		entityManager.merge(category);
		return category;
	}

	@Override
	public void deleteCategory(Category category) {
		Query query = entityManager
				.createQuery("DELETE FROM Category c WHERE c.id = :id");
		query.setParameter("id", category.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted category(id = {})", category.getId());
		} else {
			LOG.warn("Tried to delete category(id = {})", category.getId());
		}
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
