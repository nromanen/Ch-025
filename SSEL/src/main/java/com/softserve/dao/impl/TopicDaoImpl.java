package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.TopicDao;
import com.softserve.entity.Topic;

@Repository
public class TopicDaoImpl implements TopicDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(TopicDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public Topic getTopicById(int id) {
		LOG.debug("Get topic with id = {}", id);
		return entityManager.find(Topic.class, id);
	}

	@Override
	public Topic addTopic(Topic topic) {
		LOG.debug("Add topic (name = {})", topic.getName());
		entityManager.persist(topic);
		return topic;
	}

	@Override
	public Topic updateTopic(Topic topic) {
		LOG.debug("Update topic (name = {})", topic.getName());
		entityManager.merge(topic);
		return topic;
	}

	@Override
	public void deleteTopic(Topic topic) {
		Query query = entityManager
				.createQuery("DELETE FROM Topic t WHERE t.id = :id");
		query.setParameter("id", topic.getId());
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted topic(name = {})", topic.getName());
		} else {
			LOG.warn("Tried to delete topic(name = {})", topic.getName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopics() {
		LOG.debug("Get all topics");
		return entityManager.createQuery("FROM Topic").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicsByBlockId(int id) {
		LOG.debug("Get all topics by block id = {}", id);
		Query query = entityManager.createQuery("FROM Topic t "
				+ "WHERE t.block.id = :id " + "ORDER BY t.order");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicsBySubjectId(int id) {
		LOG.debug("Get all topics by block id = {}", id);
		Query query = entityManager.createQuery("FROM Topic t "
				+ "WHERE t.block.subject.id = :id " + "ORDER BY t.order");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getTopicsCountByBlockId(int id) {
		LOG.debug("Get", id);
		Query query = entityManager.createQuery("SELECT count(t) FROM Topic t "
				+ "WHERE t.block.id = :id ");
		query.setParameter("id", id);
		return Integer.parseInt((String) query.getResultList().get(0));
	}

}
