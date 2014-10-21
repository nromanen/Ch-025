package com.softserve.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.SubjectDao;
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
	public void addSubject(Subject subject) {
		entityManager.persist(subject);
		LOG.debug("Add subject {}", subject);
	}

	@Override
	public void updateSubject(Subject subject) {
		entityManager.merge(subject);
		LOG.debug("Update subject = {}", subject.getName());
	}

	@Override
	public void deleteSubject(Subject subject) {
		Query query = entityManager
				.createQuery("DELETE FROM Subject s WHERE s.id = :id");
		query.setParameter("id", subject.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted subject(id = {})", subject.getId());
		} else {
			LOG.warn("Tried to delete subject(id = {})", subject.getId());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Subject> getAllSubjects() {
		LOG.debug("Get all subjects");
		Set<Subject> subjects = new HashSet<>();
		subjects.addAll(entityManager.createQuery("FROM Subject")
				.getResultList());
		return subjects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> getSubjectsByCategoryId(int id) {
		Query query = entityManager.createQuery("FROM Subject s "
				+ "WHERE s.category.id = :id");
		query.setParameter("id", id);

		LOG.debug("Get all subjects by category id = {}", id);
		return query.getResultList();
	}

}
