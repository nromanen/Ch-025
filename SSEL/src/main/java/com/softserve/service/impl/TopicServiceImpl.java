package com.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.TopicDao;
import com.softserve.entity.Topic;
import com.softserve.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDao topicDao;

	/**
	 * @see com.softserve.service.TopicService#getTopicById(int)
	 */
	@Override
	@Transactional
	public Topic getTopicById(int id) {
		return topicDao.getTopicById(id);
	}

	/**
	 * @see com.softserve.service.TopicService#addTopic(Topic)
	 */
	@Override
	@Transactional
	public Topic addTopic(Topic topic) {
		return topicDao.addTopic(topic);
	}

	/**
	 * @see com.softserve.service.TopicService#updateTopic(Topic)
	 */
	@Override
	@Transactional
	public Topic updateTopic(Topic topic) {
		return topicDao.updateTopic(topic);
	}

	/**
	 * @see com.softserve.service.TopicService#deleteTopic(Topic)
	 */
	@Override
	@Transactional
	public void deleteTopic(Topic topic) {
		topicDao.setTopicDeleted(topic, true);
	}

	/**
	 * @see com.softserve.service.TopicService#getAllTopics()
	 */
	@Override
	@Transactional
	public List<Topic> getAllTopics() {
		return topicDao.getAllTopics();
	}

	/**
	 * @see com.softserve.service.TopicService#getTopicsByBlockId(int)
	 */
	@Override
	@Transactional
	public List<Topic> getTopicsByBlockId(int id) {
		return topicDao.getTopicsByBlockId(id);
	}

	/**
	 * @see com.softserve.service.TopicService#getTopicsBySubjectId(int)
	 */
	@Override
	@Transactional
	public List<Topic> getTopicsBySubjectId(int id) {
		return topicDao.getTopicsBySubjectId(id);
	}

	/**
	 * @see com.softserve.service.TopicService#changeOrderUp(Topic)
	 */
	@Override
	@Transactional
	public void changeOrderUp(Topic topic) {
		List<Topic> topics = topicDao.getTopicsByBlockId(topic.getBlock()
				.getId());

		int indexOfTopic = 0;

		for (int i = 0; i < topics.size(); i++) {
			if (topics.get(i).getOrder() == topic.getOrder()) {
				indexOfTopic = i;
			}

		}

		if (indexOfTopic >= 1) {
			int tmpOrder = topic.getOrder();
			Topic topic1 = topics.get(indexOfTopic - 1);
			topic.setOrder(topic1.getOrder());
			topic1.setOrder(tmpOrder);
			updateTopic(topic);
			updateTopic(topic1);
		}
	}

	/**
	 * @see com.softserve.service.TopicService#changeOrderDown(Topic)
	 */
	@Override
	@Transactional
	public void changeOrderDown(Topic topic) {
		List<Topic> topics = topicDao.getTopicsByBlockId(topic.getBlock()
				.getId());

		int indexOfTopic = 0;

		for (int i = 0; i < topics.size(); i++) {
			if (topics.get(i).getOrder() == topic.getOrder()) {
				indexOfTopic = i;
			}
		}

		if (indexOfTopic < topics.size() - 1) {
			int tmpOrder = topic.getOrder();
			Topic topic1 = topics.get(indexOfTopic + 1);
			topic.setOrder(topic1.getOrder());
			topic1.setOrder(tmpOrder);
			updateTopic(topic);
			updateTopic(topic1);
		}
	}

	/**
	 * @see com.softserve.service.TopicService#restoreTopic(Topic)
	 */
	@Override
	public void restoreTopic(Topic topic) {
		topicDao.setTopicDeleted(topic, false);
	}

	/**
	 * @see com.softserve.service.TopicService#getDeletedTopics()
	 */
	@Override
	public List<Topic> getDeletedTopics() {
		return topicDao.getAllDeletedTopics();
	}

}