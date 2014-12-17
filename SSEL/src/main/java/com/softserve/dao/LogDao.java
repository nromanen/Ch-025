package com.softserve.dao;

import java.util.Date;
import java.util.List;

import com.softserve.entity.Log;

public interface LogDao {

	/**
	 * Gets Log object by its id from database.
	 */
	Log getLogById(int id);

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
	List<Log> getRangeOfLogs(Date startDate, Date endDate, int logsPerPage,
			int pageNumb, String orderBy);

	/**
	 * Deletes all logs older than inputed date. (Not including it)
	 */
	void deleteLogsDueDate(Date date);

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
	Long countLogsInQuery(Date startDate, Date endDate);

}
