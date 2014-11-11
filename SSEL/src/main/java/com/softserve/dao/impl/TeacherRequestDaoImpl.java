package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.TeacherRequestDao;
import com.softserve.entity.TeacherRequest;

@Repository
public class TeacherRequestDaoImpl implements TeacherRequestDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(TeacherRequestDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public TeacherRequest addTeacherRequest(TeacherRequest request) {
		LOG.debug("Add teacher request {}", request);
		entityManager.persist(request);
		return request;
	}

	@Override
	public TeacherRequest updateTeacherRequest(TeacherRequest request) {
		LOG.debug("Update teacher request = {}", request);
		entityManager.merge(request);
		return request;
	}

	@Override
	public void deleteTeacherRequest(TeacherRequest request) {
		Query query = entityManager
				.createQuery("DELETE FROM TeacherRequest t WHERE t.id = :id");
		query.setParameter("id", request.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted teacher request(id = {})", request.getId());
		} else {
			LOG.warn("Tried to delete teacher request(id = {})",
					request.getId());
		}
	}

	@Override
	public TeacherRequest getTeacherRequestById(int id) {
		LOG.debug("Get teacher request with id = {}", id);
		return entityManager.find(TeacherRequest.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherRequest> getAllTeacherRequests() {
		LOG.debug("Get teacher requests");
		return entityManager.createQuery("FROM TeacherRequest").getResultList();
	}

	@Override
	public TeacherRequest getTeacherRequestByUserId(int userId) {
		LOG.debug("Get teacher request by user id = {}", userId);
		Query query = entityManager.createQuery("FROM TeacherRequest t "
				+ "WHERE t.user.id = :id");
		query.setParameter("id", userId);
		try {
		return (TeacherRequest) query.getSingleResult();
		} catch (Exception e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherRequest> getAllActiveTeacherRequests() {
		LOG.debug("Get all active teacher requests");
		return entityManager.createQuery(
				"FROM TeacherRequest t WHERE t.active = 1").getResultList();
	}

	@Override
	public long getAllActiveTeacherRequestsCount() {
		LOG.debug("Get all active teacher requests count");
		Query query = entityManager
				.createQuery("SELECT COUNT (*) FROM TeacherRequest t WHERE t.active = 1");
		return (Long) query.getSingleResult();
	}

}
