package com.softserve.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
	
	/**
	 * Deletes all logs older than inputed date. (Not including it)
	 */
	@Override
	@Transactional // temporary
	public void deleteLogsDueDate(Date date) {
		Query query = entityManager
				.createQuery("DELETE FROM Log l WHERE l.eventDate < :date");
		query.setParameter("date", date);
		if (query.executeUpdate() != 0) {
			LOG.info("Deleted logs older than = {} ", date);
		} else {
			LOG.info("Tried to delete logs older than = {} ", date);
		}
	}

	/**
	 * Gets Log object by its id from database.
	 */
	@Override
	public Log getLogById(int id) {
		LOG.debug("Get Log with id = {}", id);
		return entityManager.find(Log.class, id);
	}

	/**
	 * This method gets range of logs and it also implement pagination on
	 * database level (for improving performance).
	 * 
	 * @param startDate
	 *            - start date of query
	 * @param endDate
	 *            - end date of query
	 * @param logsPerPage
	 *            - quantity of logs per one query
	 * @param pageNumb
	 *            - number of page
	 * @param orderBy
	 *            - provides ability of sorting logs
	 * @return List of Logs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getRangeOfLogs(Date startDate, Date endDate, int logsPerPage, int pageNumb, String orderBy) {
		LOG.debug("Get range of Logs");
		String stringQuery = "FROM Log l WHERE l.eventDate > :startDate "
				+ "AND l.eventDate <= :endDate ORDER BY l." + orderBy;
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setFirstResult(logsPerPage * pageNumb);
		query.setMaxResults(logsPerPage);
		List<Log> logList = (List<Log>) query.getResultList();
		return logList;
	}

	/**
	 * Counts how many logs in certain query. Can be used in jsp-pages for
	 * pagination.
	 * 
	 * @param startCalendar
	 *            - start date of query
	 * @param endCalendar
	 *            - end date of query
	 * @return number of logs between 2 dates.
	 */
	@Override
	public Long countLogsInQuery(Date startDate, Date endDate) {
		LOG.debug("Counted logs in query");
		Query query = entityManager
				.createQuery("SELECT COUNT(*) FROM Log l WHERE l.eventDate > :startDate AND l.eventDate <= :endDate");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return (Long) query.getSingleResult();
	}

}
