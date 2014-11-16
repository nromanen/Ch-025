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
	/**
	 * @see com.softserve.dao.TopicDao#getTopicById(int)
	 */
	@Override
	public Topic getTopicById(int id) {
		LOG.debug("Get topic with id = {}", id);
		return entityManager.find(Topic.class, id);
	}
	/**
	 * @see com.softserve.dao.TopicDao#addTopic(Topic)
	 */
	@Override
	public Topic addTopic(Topic topic) {
		LOG.debug("Add topic (name = {})", topic.getName());
		entityManager.persist(topic);
		return topic;
	}
	/**
	 * @see com.softserve.dao.TopicDao#updateTopic(Topic)
	 */
	@Override
	public Topic updateTopic(Topic topic) {
		LOG.debug("Update topic (name = {})", topic.getName());
		entityManager.merge(topic);
		return topic;
	}
	/**
	 * @see com.softserve.dao.TopicDao#setTopicDeleted(Topic, boolean)
	 */
	@Override
	public void setTopicDeleted(Topic topic, boolean deleted) {
		Query query = entityManager
				.createQuery("UPDATE Topic t SET t.isDeleted = :del WHERE t.id = :id");
		query.setParameter("id", topic.getId());
		query.setParameter("del", deleted);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted = {} topic(name = {})", deleted, topic.getName());
		} else {
			LOG.warn("Tried to delete topic(name = {})", topic.getName());
		}
	}
	/**
	 * @see com.softserve.dao.TopicDao#getAllTopics()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllTopics() {
		LOG.debug("Get all topics");
		return entityManager.createQuery("FROM Topic t WHERE t.isDeleted = :val")
				.setParameter("val", true)
				.getResultList();
	}
	/**
	 * @see com.softserve.dao.TopicDao#getTopicsByBlockId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicsByBlockId(int id) {
		LOG.debug("Get all topics by block id = {}", id);
		Query query = entityManager.createQuery("FROM Topic t "
				+ "WHERE t.block.id = :id and t.isDeleted = :val " + "ORDER BY t.order");
		query.setParameter("id", id);
		query.setParameter("val", false);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.TopicDao#getTopicsBySubjectId(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicsBySubjectId(int id) {
		LOG.debug("Get all topics by block id = {}", id);
		Query query = entityManager.createQuery("FROM Topic t "
				+ "WHERE t.block.subject.id = :id and t.isDeleted = :val" + " ORDER BY t.order");
		query.setParameter("id", id);
		query.setParameter("val", false);
		return query.getResultList();
	}
	/**
	 * @see com.softserve.dao.TopicDao#getAllDeletedTopics()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getAllDeletedTopics() {
		return entityManager.createQuery("FROM Topic t WHERE t.isDeleted = :val")
				.setParameter("val", true)
				.getResultList();
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
