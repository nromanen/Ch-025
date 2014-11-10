package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.CategoryDao;
import com.softserve.entity.Category;

/**
 * The Class CategoryDaoImpl. This class implement interface {@link CategoryDao}
 * 
 * @author Roma Khomyshyn
 * @author Andriy Shutka
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory
			.getLogger(CategoryDaoImpl.class);

	/** The entity manager. */
	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	/**
	 * @see com.softserve.dao.CategoryDao#addCategory(com.softserve.entity.Category)
	 */
	@Override
	public Category addCategory(Category category) {
		LOG.debug("Add category {}", category.getName());
		entityManager.persist(category);
		return category;
	}

	/**
	 * @see com.softserve.dao.CategoryDao#updateCategory(com.softserve.entity.Category
	 *      )
	 */
	@Override
	public Category updateCategory(Category category) {
		LOG.debug("Update category = {}", category.getName());
		entityManager.merge(category);
		return category;
	}

	/**
	 * @see com.softserve.dao.CategoryDao#deleteCategory(com.softserve.entity.Category
	 *      )
	 */
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

	/**
	 * @see com.softserve.dao.CategoryDao#getAllCategories()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		LOG.debug("Get all categories");
		List<Category> categories = new ArrayList<>();
		categories.addAll(entityManager.createQuery("FROM Category")
				.getResultList());
		return categories;
	}

	/**
	 * @see com.softserve.dao.CategoryDao#getCategoryById(int)
	 */
	@Override
	public Category getCategoryById(int id) {
		LOG.debug("Get category with id = {}", id);
		return entityManager.find(Category.class, id);
	}

	/**
	 * @see com.softserve.dao.CategoryDao#getCategoriesByNamePart(java.lang.String)
	 */
@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoriesByNamePart(String namePart, int pageNumber, int pageSize) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder
				.createQuery(Category.class);
		Root<Category> root = criteriaQuery.from(Category.class);
		criteriaQuery.select(root);
		Predicate predicate = criteriaBuilder.like(
				criteriaBuilder.upper(root.<String> get("name")), "%"
						+ namePart.toUpperCase() + "%");

		criteriaQuery.where(predicate);
		Query query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public Long getCategoriesQuantityByNamePart(String namePart) {
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM Category s WHERE name LIKE :namepart");
		query.setParameter("namepart", "%" + namePart + "%");
		return (Long) query.getSingleResult();
	}

}
