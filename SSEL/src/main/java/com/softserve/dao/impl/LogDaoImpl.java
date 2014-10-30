package com.softserve.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.softserve.dao.LogDao;
import com.softserve.entity.Log;

@Repository
public class LogDaoImpl implements LogDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(TopicDaoImpl.class);

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Override
	public Log addLog(Log log) {
		LOG.debug("Add log with time = {}", log.getEventDate());
		entityManager.persist(log);
		return log;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getLogsSinceDate(Date date) {
		LOG.debug("Get all logs sinse {}", date);
		Query query = entityManager.createQuery("FROM Log l "
				+ "WHERE l.eventDate > :date");
		query.setParameter("date", date);
		List<Log> logList = query.getResultList();
		return logList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getAllLogs() {
		LOG.debug("Get all Logs");
		List<Log> logList = (List<Log>) entityManager.createQuery("FROM Log")
				.getResultList();
		return logList;
	}

	@Override
	public void deleteLogsDueDate(Date date) {
		Query query = entityManager
				.createQuery("DELETE FROM Log l WHERE l.eventDate < :date");
		query.setParameter("date", date);
		if (query.executeUpdate() != 0) {
			LOG.debug("Deleted logs older than = {} ", date);
		} else {
			LOG.warn("Tried to delete logs older than = {} ", date);
		}
	}

	
	
}
