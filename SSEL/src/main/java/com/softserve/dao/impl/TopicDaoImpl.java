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
	public void addTopic(Topic topic) {
		entityManager.persist(topic);
		LOG.debug("Add topic (name = {})", topic.getName());
	}

	@Override
	public void updateTopic(Topic topic) {
		entityManager.merge(topic);
		LOG.debug("Update topic (name = {})", topic.getName());
	}

	@Override
	public void deleteTopic(Topic topic) {
		entityManager.remove(topic);
		LOG.debug("Deleted topic (topic = {})", topic.getName());
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
		Query query = entityManager.createQuery("FROM Topic t "
				+ "WHERE t.block.id = :id " + "ORDER BY t.order");
		query.setParameter("id", id);

		LOG.debug("Get all topics by block id = {}", id);
		return query.getResultList();
	}

}
