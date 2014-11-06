package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.RatingDao;
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Rating;

@Repository
public class RatingDaoImpl implements RatingDao {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(StudentGroupDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public Rating addRating(Rating newRating) {
		LOG.debug("Add rating (number = {})", newRating.getRatingId());
		entityManager.persist(newRating);
		return newRating;
	}

	@Override
	public Rating updateRating(Rating updated) {
		LOG.debug("Update rating (number = {})", updated.getRatingId());
		entityManager.merge(updated);
		return updated;
	}

	@Override
	public void deleteRating(Rating rating) {
		LOG.debug("Delete studentGroup (number = {})", rating.getRatingId());
		Query query = entityManager.createQuery("DELETE FROM Rating g WHERE g.ratingId = :id");
		query.setParameter("id", rating.getRatingId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted studentGroup(id = {})", rating.getRatingId());
		} else {
			LOG.warn("Tried to delete studentGroup(id = {})",
					rating.getRatingId());
		}
	}

	@Override
	public Rating getRatingById(int ratingId) {
		Query query = entityManager.createQuery("SELECT r FROM Rating r WHERE r.ratingId = :id");
		query.setParameter("id", ratingId);
		return (query.getResultList().size() == 0) ? null : (Rating) query.getSingleResult(); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rating> getRatingsByGroupAndUser(int groupId, int userId) {
		Query query = entityManager.createQuery("SELECT r FROM Rating r WHERE r.group.groupId = :gi AND r.user.id = :ui ");
		query.setParameter("gi", groupId);
		query.setParameter("ui", userId);
		return query.getResultList();
	}

	@Override
	public double getAverageRatingByGroupAndUser(int groupId, int userId) {
		Query query = entityManager.createQuery("SELECT avg(r.mark) FROM Rating r WHERE r.group.groupId = :gi AND "
				+ "r.user.id = :ui ");
		query.setParameter("gi", groupId);
		query.setParameter("ui", userId);
		return (Double) query.getSingleResult();
	}

	@Override
	public double getProgressByGroupAndUser(int groupId, int userId) {
		CourseScheduler cs = (CourseScheduler) entityManager.createQuery("SELECT gr.course FROM Group gr WHERE gr.groupId = :id")
				.setParameter("id", groupId).getSingleResult();
		Query query = entityManager.createQuery("SELECT count(bl.id) FROM Block bl WHERE bl.subject.id = :id")
				.setParameter("id", cs.getSubject().getId());
		Long blocksCount = (Long) query.getSingleResult();
		Long ratingsCount = (Long) entityManager.createQuery("SELECT count(rt.id) FROM Rating rt WHERE "
				+ "rt.group.groupId = :gid AND rt.user.id = :uid")
		.setParameter("gid", groupId)
		.setParameter("uid", userId)
		.getSingleResult();
		ratingsCount *= 100L;
		return (double)ratingsCount/blocksCount;
	}
	
}