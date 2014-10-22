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
	public void addTopic(Topic topic) {
		topicDao.addTopic(topic);
	}

	@Override 
	@Transactional 
	public void updateTopic(Topic topic) {
		topicDao.updateTopic(topic);
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
	public List<Topic> getTopicsBySubjectId(int id) {
		return topicDao.getTopicsByBlockId(id);
	}

}
