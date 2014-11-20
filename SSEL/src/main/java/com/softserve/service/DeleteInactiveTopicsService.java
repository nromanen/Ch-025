package com.softserve.service;
/**
 * Specify delete inactive topics service
 * @author Anatoliy
 *
 */
public interface DeleteInactiveTopicsService {
	/**
	 * Remove inactive topics from disk
	 */
	void deleteInactiveTopics();

}
