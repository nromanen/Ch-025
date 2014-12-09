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
 * @author Anatoliy Vacaliuck
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
	 * @see com.softserve.dao.CategoryDao#getAllCategories()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		LOG.debug("Get all categories");
		List<Category> categories = new ArrayList<>();
		categories.addAll(entityManager.createQuery("FROM Category c WHERE c.isDeleted = :val")
		.setParameter("val", false)
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
	public List<Category> getCategoriesByNamePart(String namePart) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder
				.createQuery(Category.class);
		Root<Category> root = criteriaQuery.from(Category.class);
		criteriaQuery.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate predicate = criteriaBuilder.like(
				criteriaBuilder.upper(root.<String> get("name")), "%"
						+ namePart.toUpperCase() + "%");
		Predicate predicateDeleted = criteriaBuilder.equal(root.get("isDeleted"), false);
		predicates.add(predicate);
		predicates.add(predicateDeleted);
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.CategoryDao#getCategoriesQuantityByNamePart(String)
	 */
	public Long getCategoriesQuantityByNamePart(String namePart) {
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM Category s WHERE name LIKE :namepart AND s.isDeleted = :val");
		query.setParameter("namepart", "%" + namePart + "%");
		query.setParameter("val", false);
		return (Long) query.getSingleResult();
	}
	/**
	 * @see com.softserve.dao.CategoryDao#setCategoryDeleted(Category, boolean)
	 */
	@Override
	public void setCategoryDeleted(Category category, boolean deleted) {
		Query query = entityManager
				.createQuery("UPDATE Category c SET c.isDeleted = :del WHERE c.id = :id");
		query.setParameter("id", category.getId());
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted = {} category(id = {})", deleted,category.getId());
		} else {
			LOG.warn("Tried to delete category(id = {})", category.getId());
		}
	}
	/**
	 * @see com.softserve.dao.CategoryDao#getAllDeletedCategories()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllDeletedCategories() {
		LOG.debug("Get all deleted categories");
		Query query = entityManager.createQuery("SELECT c FROM Category c WHERE c.isDeleted = :val");
		query.setParameter("val", true);
		return query.getResultList();
	}

}
