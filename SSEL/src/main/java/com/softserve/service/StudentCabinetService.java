package com.softserve.service;

import java.util.List;

import com.softserve.entity.CourseScheduler;
import com.softserve.entity.StudyDocument;
import com.softserve.entity.User;

/**
 * Specify student cabinet funtionality
 * @author Anatoliy
 *
 */
public interface StudentCabinetService {
	/**
	 * Subscribe/unsubscribe user from course
	 * @param course course to s/u
	 * @param user user which is s/u
	 * @param operation true - subscribe, false - usnsubscribe
	 */
	void subscribe(CourseScheduler course, User user, boolean operation);
	/**
	 * Update/Create attached files for topic
	 * @param directoryName path to directory for files
	 * @param topicId unique topic identifier
	 * @return list of attached files for topic
	 */
	List<StudyDocument> updateTopicFilesOnServer(String directoryName, int topicId);
}
