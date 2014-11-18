package com.softserve.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.SubjectDao;
import com.softserve.entity.Category;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(SubjectDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public Subject getSubjectById(int id) {
		LOG.debug("Get subject with id = {}", id);
		return entityManager.find(Subject.class, id);
	}

	@Override
	public Subject addSubject(Subject subject) {
		LOG.debug("Add subject {}", subject);
		entityManager.persist(subject);
		return subject;
	}

	@Override
	public Subject updateSubject(Subject subject) {
		LOG.debug("Update subject = {}", subject.getName());
		entityManager.merge(subject);
		return subject;
	}

	@Override
	public void setSubjectDeleted(Subject subject, boolean deleted) {
		Query query = entityManager
				.createQuery("UPDATE Subject s SET s.isDeleted = :del WHERE s.id = :id");
		query.setParameter("id", subject.getId());
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted subject(id = {})", subject.getId());
		} else {
			LOG.warn("Tried to delete subject(id = {})", subject.getId());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getAllSubjects() {
		LOG.debug("Get all subjects");
		List<Subject> subjects = new ArrayList<>();
		subjects.addAll(entityManager.createQuery("FROM Subject s WHERE s.isDeleted = :val")
				.setParameter("val", false)
				.getResultList());
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByCategoryId(int id) {
		LOG.debug("Get all subjects by category id = {}", id);
		Query query = entityManager.createQuery("FROM Subject s "
				+ "WHERE s.category.id = :id and s.isDeleted = :val");
		query.setParameter("val", false);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByUserId(int id) {
		LOG.debug("Get all subjects by user id = {}", id);
		Query query = entityManager.createQuery("FROM Subject s "
				+ "WHERE s.user.id = :id and s.isDeleted = :val");
		query.setParameter("val", false);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByNamePart(String namePart, int pageNumber, int pageSize, 
			String sortBy, boolean isReverse) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
		Root<Subject> root = criteriaQuery.from(Subject.class);
		Root<Category> category = criteriaQuery.from(Category.class);
		Join<Subject, CourseScheduler> scheduler = root.join("schedulers");
		criteriaQuery.select(root).distinct(true);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate predicate = 
				criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("name")), "%" + namePart.toUpperCase() + "%");
		Predicate predicateJoin = criteriaBuilder.equal(scheduler.get("subject"), root.get("id"));
		Predicate predicateDeleted = criteriaBuilder.equal(root.get("isDeleted"), false); 
		predicates.add(predicate);
		predicates.add(predicateJoin);
		predicates.add(predicateDeleted);
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		Expression<?> sort = root.<String>get("id");
		if (sortBy.equals("name")) {
			sort = root.<String>get(sortBy);
		} else if (sortBy.equals("category")) {
			sort = category.<String>get("name");
		} else if (sortBy.equals("date")) {
			sort = scheduler.<Date>get("start");
		}
		if (isReverse) {
			criteriaQuery.orderBy(criteriaBuilder.desc(sort));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.asc(sort));
		}
		Query query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public Long getSubjectsQuantityByNamePart(String namePart) {
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM Subject s WHERE name LIKE :namepart");
		query.setParameter("namepart", "%" + namePart + "%");
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByNameVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		LOG.debug("Get all subjects vs limit searchText = {}", searchText);
		String textQuery = "FROM Subject s WHERE s.name = '" + searchText + "'";
		Query query = setQueryParameters(textQuery, startPosition, limitLength, sortBy,
				sortMethod);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByCategoryVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		LOG.debug("Get all subjects vs limit searchText = {}", searchText);

		String textQuery = "FROM Subject s WHERE s.category.name = '" + searchText + "'";
		Query query = setQueryParameters(textQuery, startPosition, limitLength, sortBy,
				sortMethod);
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsVsLimit(int startPosition, int limitLength,
			String sortBy, String sortMethod) {
		LOG.debug("Get subjects from - to = {} {}", startPosition, limitLength);
		String textQuery = "FROM Subject s";
		Query query = setQueryParameters(textQuery, startPosition, limitLength, sortBy,
				sortMethod);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByTextVsLimit(String searchText,
			int startPosition, int limitLength, String sortBy, String sortMethod) {
		LOG.debug("Get all subjects by searchText = {}", searchText);
		String textQuery = "FROM Subject s WHERE s.name = '" + searchText
				+ "' or s.category.name = '" + searchText + "'";
		Query query = setQueryParameters(textQuery, startPosition, limitLength, sortBy,
				sortMethod);
		return query.getResultList();
	}

	private Query setQueryParameters(String textQuery, int startPosition,
			int limitLength, String sortBy, String sortMethod) {
		Query query;
		if (sortBy != null && sortMethod != null) {
			if (sortBy.equals("subject")) {
				sortBy = "ORDER BY s.name";
			} else {
				sortBy = "ORDER BY s.category.name";
			}

			if (sortMethod.equals("asc")) {
				sortMethod = "ASC";
			} else {
				sortMethod = "DESC";
			}
			sortBy += " " + sortMethod;
			query = entityManager.createQuery(textQuery + " " + sortBy);
		} else {
			query = entityManager.createQuery(textQuery);
		}

		query.setFirstResult(startPosition);
		query.setMaxResults(limitLength);

		return query;
	}

	@Override
	public long getSubjectsCount() {
		LOG.debug("Get all subjects count");
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM Subject s ");
		return (Long) query.getSingleResult();
	}

	@Override
	public long getSubjectsByNameCount(String searchName) {
		LOG.debug("Get subjects by name count");
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM Subject s "
						+ "WHERE s.name = :name");
		query.setParameter("name", searchName);
		return (Long) query.getSingleResult();
	}

	@Override
	public long getSubjectsByCategoryCount(String searchCategory) {
		LOG.debug("Get subjects by category count");
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM Subject s "
						+ "WHERE s.category.name = :name");
		query.setParameter("name", searchCategory);
		return (Long) query.getSingleResult();
	}

	@Override
	public long getSubjectsByTextCount(String searchText) {
		LOG.debug("Get subjects count");
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM Subject s "
						+ "WHERE s.name = :searchText or s.category.name = :searchText");
		query.setParameter("searchText", searchText);
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByCategoryIdWithLimit(int categoryId, int pageNumber, int pageSize,
			String sortBy, boolean isReverse) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
		Root<Subject> root = criteriaQuery.from(Subject.class);
		Root<Category> category = criteriaQuery.from(Category.class);
		Join<Subject, CourseScheduler> scheduler = root.join("schedulers");
		criteriaQuery.select(root).distinct(true);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		Predicate predicate = criteriaBuilder.equal(root.<Integer>get("category"), categoryId);
		criteriaQuery.where(predicate);
		Predicate predicateJoin = criteriaBuilder.equal(scheduler.get("subject"), root.get("id"));
		Predicate predicateDeleted = criteriaBuilder.equal(root.get("isDeleted"), false); 
		predicates.add(predicate);
		predicates.add(predicateJoin);
		predicates.add(predicateDeleted);
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		Expression<?> sort = root.<String>get("id");
		if (sortBy.equals("name")) {
			sort = root.<String>get(sortBy);
		} else if (sortBy.equals("category")) {
			sort = category.<String>get("name");
		} else if (sortBy.equals("date")) {
			sort = scheduler.<Date>get("start");
		}
		if (isReverse) {
			criteriaQuery.orderBy(criteriaBuilder.desc(sort));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.asc(sort));
		}
		Query query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getDeletedSubjects() {
		LOG.debug("Get deleted");
		return entityManager.createQuery("FROM Subject s WHERE s.isDeleted = :val")
				.setParameter("val", false)
				.getResultList();
	}

}
