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

	@Override
	@Transactional
	public Topic getTopicById(int id) {
		return topicDao.getTopicById(id);
	}

	@Override
	@Transactional
	public Topic addTopic(Topic topic) {
		return topicDao.addTopic(topic);
	}

	@Override
	@Transactional
	public Topic updateTopic(Topic topic) {
		return topicDao.updateTopic(topic);
	}

	@Override
	@Transactional
	public void deleteTopic(Topic topic) {
		topicDao.deleteTopic(topic);
	}

	@Override
	@Transactional
	public List<Topic> getAllTopics() {
		return topicDao.getAllTopics();
	}

	@Override
	@Transactional
	public List<Topic> getTopicsByBlockId(int id) {
		return topicDao.getTopicsByBlockId(id);
	}

	@Override
	@Transactional
	public List<Topic> getTopicsBySubjectId(int id) {
		return topicDao.getTopicsBySubjectId(id);
	}

	@Override
	@Transactional
	public void changeOrderUp(Topic topic) {
		List<Topic> topics = topicDao.getTopicsByBlockId(topic.getBlock().getId());

		/*
		 * for(int i = topics.size()-1 ; i > 0 ; i--) { for(int j = 0 ; j < i ;
		 * j++) { if (topics.get(j).getOrder() > topics.get(j + 1).getOrder()) {
		 * Topic temp = topics.get(j); topics.set(j, topics.get(j + 1));
		 * topics.set(j + 1, temp); } } }
		 */
		int tempOrder = 0;
		int tempIndex = topics.indexOf(topic);
		for (Topic t : topics) {
			if (t.getOrder() < topic.getOrder() && t.getOrder() > tempOrder) {
				tempOrder = t.getOrder();
				tempIndex = topics.indexOf(t);

			}
		}
		if (tempIndex >= 0 && tempIndex < topics.size()) {
			Topic t1 = topics.get(tempIndex);
			t1.setOrder(topic.getOrder());
			topic.setOrder(tempOrder);
			updateTopic(topic);
			updateTopic(t1);

		}

		/*
		 * int tempIndex = topics.indexOf(topic); if (tempIndex > 0) { int
		 * tempOrder = topics.get(tempIndex - 1).getOrder();
		 * topics.get(tempIndex - 1).setOrder(topic.getOrder());
		 * topics.get(tempIndex).setOrder(tempOrder);
		 * updateTopic(topics.get(tempIndex - 1));
		 * updateTopic(topics.get(tempIndex)); }
		 */
	}

	@Override
	@Transactional
	public void changeOrderDown(Topic topic) {
		List<Topic> topics = topicDao.getTopicsByBlockId(topic.getBlock().getId());

		int tempOrder = 9999999;
		int tempIndex = topics.indexOf(topic);
		for (Topic t : topics) {
			if (t.getOrder() > topic.getOrder() && t.getOrder() < tempOrder) {
				tempOrder = t.getOrder();
				tempIndex = topics.indexOf(t);

			}
		}

		if (tempIndex >= 0 && tempIndex < topics.size()) {
			Topic t1 = topics.get(tempIndex);
			t1.setOrder(topic.getOrder());
			topic.setOrder(tempOrder);
			updateTopic(topic);
			updateTopic(t1);
		}
		/*
		 * for(int i = topics.size()-1 ; i > 0 ; i--) { for(int j = 0 ; j < i ;
		 * j++) { if (topics.get(j).getOrder() > topics.get(j + 1).getOrder()) {
		 * Topic temp = topics.get(j); topics.set(j, topics.get(j + 1));
		 * topics.set(j + 1, temp); } } }
		 */

		/*
		 * Collections.sort(topics, new Comparator<Topic>() { public int
		 * compare(Topic o1, Topic o2) { return o1.getOrder() - o2.getOrder(); }
		 * });
		 */

		/*
		 * int tempIndex = topics.indexOf(topic); if (tempIndex < topics.size()
		 * - 1) { int tempOrder = topics.get(tempIndex + 1).getOrder();
		 * topics.get(tempIndex + 1).setOrder(topic.getOrder());
		 * topics.get(tempIndex).setOrder(tempOrder);
		 * updateTopic(topics.get(tempIndex + 1));
		 * updateTopic(topics.get(tempIndex)); }
		 */
	}

}