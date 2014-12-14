package com.softserve.service;

import java.util.List;
import java.util.Map;

import com.softserve.entity.ConfigurationProperty;
import com.softserve.entity.StudyDocument;

/**
 * Implements QuestionDao
 * @author Ivan
 *
 */
public interface AdministratorService {

	/**
	 * Create category
	 * @param name - category name
	 * @return true if category was add, false if category already exist
	 */
	public boolean addCategory(String name);

	/**
	 * Get support email
	 * @return support email
	 */
	public String getSupportEmail();

	/**
	 * Change support email
	 * @param email new email
	 * @return object with configuration properties
	 */
	public ConfigurationProperty setSupportEmail(String email);

	/**
	 * Get count of registered users
	 * @param lastDays - count of last days for statistic
	 * @return
	 */
	public Map<String, Long> getCountRegistredUsersByLastDays(int lastDays);

	public long getDocumentsForInactiveTopicsSize();
}
