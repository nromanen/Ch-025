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
import com.softserve.entity.CourseScheduler;
import com.softserve.entity.Log;

@Repository
public class LogDaoImpl implements LogDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(TopicDaoImpl.class);

	public static final int MAX_QUERY_LIMIT = 50;

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
	public List<Log> getLogsSinceDate(Date date, int pageNumb) {
		LOG.debug("Get all logs sinse {}", date);
		Query query = entityManager.createQuery("FROM Log l "
				+ "WHERE l.eventDate > :date");
		query.setParameter("date", date);
		query.setFirstResult(MAX_QUERY_LIMIT * pageNumb);
		query.setMaxResults(MAX_QUERY_LIMIT);
		List<Log> logList = query.getResultList();
		return logList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getAllLogs(int pageNumb) {
		LOG.debug("Get all Logs");
		Query query = entityManager.createQuery("FROM Log");
		query.setFirstResult(MAX_QUERY_LIMIT * pageNumb);
		query.setMaxResults(MAX_QUERY_LIMIT);
		List<Log> logList = (List<Log>) query.getResultList();
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
			LOG.debug("Tried to delete logs older than = {} ", date);
		}
	}

	@Override
	public Log getLogById(int id) {
		LOG.debug("Get Log with id = {}", id);
		return entityManager.find(Log.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getPartOfLogs(int firstLine, int numberOfLines) {
		LOG.debug("Get part of Logs");
		Query query = entityManager.createQuery("FROM Log");
		query.setFirstResult(firstLine);
		query.setMaxResults(numberOfLines);
		List<Log> logList = (List<Log>) query.getResultList();
		return logList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getRageofLogs(Date startDate, Date endDate, int logsPerPage, int pageNumb) {
		LOG.debug("Get range of Logs");
		Query query = entityManager
				.createQuery("FROM Log l WHERE l.eventDate > :startDate AND l.eventDate <= :endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setFirstResult(logsPerPage * pageNumb);
		query.setMaxResults(logsPerPage);
		List<Log> logList = (List<Log>) query.getResultList();
		return logList;
	}

	
	@Override
	public Long countLogsInQuery(Date startDate, Date endDate) {
		Query query = entityManager
				.createQuery("SELECT COUNT(*) FROM Log l WHERE l.eventDate > :startDate AND l.eventDate <= :endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return (Long) query.getSingleResult();
	}

}
