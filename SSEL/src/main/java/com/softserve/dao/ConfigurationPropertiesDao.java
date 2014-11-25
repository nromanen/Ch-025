package com.softserve.dao;

import com.softserve.entity.ConfigurationProperty;

public interface ConfigurationPropertiesDao {
	/**
	 * Add new property into properties table
	 * @param newProperty new property
	 * @return added property
	 */
	ConfigurationProperty addproperty(ConfigurationProperty newProperty);
	/**
	 * Update property 
	 * @param updatedProperty updated property
	 * @return updated property
	 */
	ConfigurationProperty updateProperty(ConfigurationProperty updatedProperty);
	/**
	 * Return property by id
	 * @param id unique property identifier
	 * @return property object
	 */
	ConfigurationProperty getPropertyById(int id);
	/**
	 * Return property by key
	 * @param key unique key for property
	 * @return property object
	 */
	ConfigurationProperty getPropertyByKey(String key);
	
}
