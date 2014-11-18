package com.softserve.service;

import java.util.List;

import com.softserve.entity.Topic;
/**
 * Specify topic service functionality
 * @author 
 *
 */
public interface TopicService {
	/**
	 * Return topic by id
	 * @param id unique topic identifier
	 * @return topic
	 */
	Topic getTopicById(int id);
	/**
	 * Add new topic into topics list
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
	 * Mark topic as deleted
	 * @param topic topic which will marked
	 */
	void deleteTopic(Topic topic);
	/**
	 * Restore topic which was marked as deleted
	 * @param topic topic that'll be restored
	 */
	void restoreTopic(Topic topic);
	/**
	 * Lift topic up in the list
	 * @param topic topic that'll lifted
	 */
	void changeOrderUp(Topic topic);
	/**
	 * Lift topic down in the list
	 * @param topic topic that'll lifted
	 */
	void changeOrderDown(Topic topic);
	/**
	 * Get all topics which is not marked as deleted
	 * @return list of topics
	 */
	List<Topic> getAllTopics();
	/**
	 * Return topic which is marked as deleted
	 * @return list of topics
	 */
	List<Topic> getDeletedTopics();
	/**
	 * Return topics for block
	 * @param id unique block identifier
	 * @return list of topics
	 */
	List<Topic> getTopicsByBlockId(int id);
	/**
	 * Return topics for subject
	 * @param id unique subject identifier
	 * @return list of topics
	 */
	public List<Topic> getTopicsBySubjectId(int id);
}
