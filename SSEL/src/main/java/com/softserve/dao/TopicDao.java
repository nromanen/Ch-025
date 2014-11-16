package com.softserve.dao;

import java.util.List;

import com.softserve.entity.Topic;
/**
 * Specify topic data access object funtionality
 * @author 
 *
 */
public interface TopicDao {
	/**
	 * Return topic by id
	 * @param id unique topic identifier
	 * @return topic
	 */
	Topic getTopicById(int id);
	/**
	 * Add new topic into topic list
	 * @param topic new topic
	 * @return added topic
	 */
	Topic addTopic(Topic topic);
	/**
	 * Update topic
	 * @param topic updated topic
	 * @return updated topic
	 */
	Topic updateTopic(Topic topic);
	/**
	 * Manage delete topic
	 * @param topic topic to manage
	 * @param deleted true - mark as delete, false - restore
	 */
	void setTopicDeleted(Topic topic, boolean deleted);
	/**
	 * Return all topics which is not deleted
	 * @return list of topics
	 */
	List<Topic> getAllTopics();
	/**
	 * Return all topics which is marked as deleted
	 * @return list of topics
	 */
	List<Topic> getAllDeletedTopics();
	/**
	 * Return all topics which is contain in block
	 * @param id unique block identifier
	 * @return list of topics
	 */
	List<Topic> getTopicsByBlockId(int id);
	/**
	 * Return all topics for subject
	 * @param id unique subject identifier
	 * @return list of topics
	 */
	List<Topic> getTopicsBySubjectId(int id);
}

	List<Topic> getTopicsBySubjectId(int id);
}
