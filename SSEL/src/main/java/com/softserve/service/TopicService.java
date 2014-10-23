package com.softserve.service;

import java.util.List;

import com.softserve.entity.Topic;

public interface TopicService {

	public Topic getTopicById(int id);

	public void addTopic(Topic topic);

	public void updateTopic(Topic topic);

	public void deleteTopic(Topic topic);
	
	public void changeOrderUp(Topic topic);
	
	public void changeOrderDown(Topic topic);

	public List<Topic> getAllTopics();

	public List<Topic> getTopicsByBlockId(int id);
	
	public List<Topic> getTopicsBySubjectId(int id);
}
