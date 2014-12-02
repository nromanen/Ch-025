package com.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.ConfigurationPropertiesDao;
import com.softserve.entity.ConfigurationProperty;

@Repository
public class ConfigurationPropertiesDaoImpl implements ConfigurationPropertiesDao{
	private static Logger LOG = LoggerFactory.getLogger(ConfigurationPropertiesDaoImpl.class);
	@PersistenceContext (unitName="entityManager")
	private EntityManager entityManager;

	/**
	 * @see com.softserve.dao.ConfigurationPropertiesDao#getPropertyById(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConfigurationProperty getPropertyById(int id) {
		LOG.debug("get property by id {}", id);
		Query query = entityManager.createQuery("FROM ConfigurationProperty cp WHERE cp.id = :id")
				.setParameter("id", id);
		List<ConfigurationProperty> list = query.getResultList();
		return (list.size() > 0) ? list.get(0) : null;
	}
	/**
	 * @see com.softserve.dao.ConfigurationPropertiesDao#getPropertyByKey(String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ConfigurationProperty getPropertyByKey(String key) {
		LOG.debug("get property by key {}", key);
		Query query = entityManager.createQuery("FROM ConfigurationProperty cp WHERE cp.key = :key")
				.setParameter("key", key);
		List<ConfigurationProperty> list = query.getResultList();
		return (list.size() >0) ? list.get(0) : null;
	}

	/**
	 * @see com.softserve.dao.ConfigurationPropertiesDao#addproperty(com.softserve.entity.ConfigurationProperty)
	 */
	@Override
	public ConfigurationProperty addproperty(ConfigurationProperty newProperty) {
		LOG.debug("add property with id {}", newProperty.getId());
		entityManager.merge(newProperty);
		return newProperty;
	}
	/**
	 * @see com.softserve.dao.ConfigurationPropertiesDao#updateProperty(com.softserve.entity.ConfigurationProperty)
	 */
	@Override
	public ConfigurationProperty updateProperty(ConfigurationProperty updatedProperty) {
		LOG.debug("update property with id {}", updatedProperty.getId());
		entityManager.merge(updatedProperty);
		return updatedProperty;
	}

}
