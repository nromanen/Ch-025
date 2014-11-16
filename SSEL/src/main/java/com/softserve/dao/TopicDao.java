package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Topic;

public interface TopicDao {

	Topic getTopicById(int id);

	Topic addTopic(Topic topic);

	Topic updateTopic(Topic topic);

	void deleteTopic(Topic topic);

	List<Topic> getAllTopics();

	List<Topic> getTopicsByBlockId(int id);
	
	Integer getTopicsCountByBlockId(int id);

	List<Topic> getTopicsBySubjectId(int id);
}
