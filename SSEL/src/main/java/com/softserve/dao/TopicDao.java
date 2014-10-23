package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Topic;

public interface TopicDao {

	public Topic getTopicById(int id);

	public void addTopic(Topic topic);

	public void updateTopic(Topic topic);

	public void deleteTopic(Topic topic);

	public List<Topic> getAllTopics();

	public List<Topic> getTopicsByBlockId(int id);
}
