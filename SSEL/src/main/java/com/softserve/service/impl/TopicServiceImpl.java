package com.softserve.service.impl;

import java.util.Collections;
import java.util.Comparator;
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
		List<Topic> topics = topicDao.getTopicsByBlockId(topic.getBlock().getId());

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

		/*
		 * for(int i = topics.size()-1 ; i > 0 ; i--) { for(int j = 0 ; j < i ;
		 * j++) { if (topics.get(j).getOrder() > topics.get(j + 1).getOrder()) {
		 * Topic temp = topics.get(j); topics.set(j, topics.get(j + 1));
		 * topics.set(j + 1, temp); } } }
		 */
		/*
		 * int tempOrder = 0; int tempIndex = topics.indexOf(topic); for (Topic
		 * t : topics) { if (t.getOrder() < topic.getOrder() && t.getOrder() >
		 * tempOrder) { tempOrder = t.getOrder(); tempIndex = topics.indexOf(t);
		 * 
		 * } } if (tempIndex >= 0 && tempIndex < topics.size()) { Topic t1 =
		 * topics.get(tempIndex); t1.setOrder(topic.getOrder());
		 * topic.setOrder(tempOrder); updateTopic(topic); updateTopic(t1);
		 * 
		 * }
		 */

		/*
		 * int tempIndex = topics.indexOf(topic); if (tempIndex > 0) { int
		 * tempOrder = topics.get(tempIndex - 1).getOrder();
		 * topics.get(tempIndex - 1).setOrder(topic.getOrder());
		 * topics.get(tempIndex).setOrder(tempOrder);
		 * updateTopic(topics.get(tempIndex - 1));
		 * updateTopic(topics.get(tempIndex)); }
		 */
	}
	/**
	 * @see com.softserve.service.TopicService#changeOrderDown(Topic)
	 */
	@Override
	@Transactional
	public void changeOrderDown(Topic topic) {
		List<Topic> topics = topicDao.getTopicsByBlockId(topic.getBlock().getId());

		// *********************************************************
		/*
		 * int tempOrder = 9999999; int tempIndex = topics.indexOf(topic); for
		 * (Topic t : topics) { if (t.getOrder() > topic.getOrder() &&
		 * t.getOrder() < tempOrder) { tempOrder = t.getOrder(); tempIndex =
		 * topics.indexOf(t);
		 * 
		 * } }
		 * 
		 * if (tempIndex >= 0 && tempIndex < topics.size()) { Topic t1 =
		 * topics.get(tempIndex); t1.setOrder(topic.getOrder());
		 * topic.setOrder(tempOrder); updateTopic(topic); updateTopic(t1); }
		 */
		// **********************************************************

		/*
		 * for(int i = topics.size()-1 ; i > 0 ; i--) { for(int j = 0 ; j < i ;
		 * j++) { if (topics.get(j).getOrder() > topics.get(j + 1).getOrder()) {
		 * Topic temp = topics.get(j); topics.set(j, topics.get(j + 1));
		 * topics.set(j + 1, temp); } } }
		 */

		/*
		 * Collections.sort(topics, new Comparator<Topic>() { public int
		 * compare(Topic topic1, Topic topic2) { return topic1.getOrder() -
		 * topic2.getOrder(); } });
		 */

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

		/*
		 * int tempIndex = topics.indexOf(topic); if (tempIndex < topics.size()
		 * - 1) { int tempOrder = topics.get(tempIndex + 1).getOrder();
		 * topics.get(tempIndex + 1).setOrder(topic.getOrder());
		 * topics.get(tempIndex).setOrder(tempOrder);
		 * updateTopic(topics.get(tempIndex + 1));
		 * updateTopic(topics.get(tempIndex)); }
		 */
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