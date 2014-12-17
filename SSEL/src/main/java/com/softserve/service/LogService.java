package com.softserve.service;

import java.util.GregorianCalendar;
import java.util.List;

import com.softserve.entity.Log;

public interface LogService {

	/**
	 * Gets Log object by its id from database.
	 */
	Log getLogById(int id);

	/**
	 * This method gets range of logs and it also implement pagination on
	 * database level (for improving performance).
	 * 
	 * @param startCalendar
	 *            - start date of query (in GregorianCalendar type)
	 * @param endCalendar
	 *            - end date of query (in GregorianCalendar type)
	 * @param logsPerPage
	 *            - quantity of logs per one query
	 * @param pageNumb
	 *            - number of page
	 * @param orderBy
	 *            - provides ability of sorting logs
	 * @return List of Logs
	 */
	List<Log> getRangeOfLogs(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar, int logsPerPage, int pageNumb,
			String orderBy);

	/**
	 * Deletes all logs older than inputed date. (Not including it)
	 */
	void deleteLogsDueDate(GregorianCalendar calendar);

	/**
	 * Counts how many logs in certain query. Can be used in jsp-pages for
	 * pagination.
	 * 
	 * @param startCalendar
	 *            - start date of query (in GregorianCalendar type)
	 * @param endCalendar
	 *            - end date of query (in GregorianCalendar type)
	 * @return number of logs between 2 dates.
	 */
	Long countLogsInQuery(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar);

	/**
	 * Depending on number logs per page counts how many pages will be needed to
	 * show all logs from certain query.
	 * 
	 * @return number of pages.
	 */
	int getNumberOfPages(Long logsInQuery, int logsPerPage);

	/**
	 * Converts inputed string in date field into GregorianCalendar object with
	 * equal date in it. If data in string incorrect, returns null.
	 */
	GregorianCalendar parseDate(String dateString);

	/**
	 * Convert string with how-to-sort information into part of hql query.
	 * 
	 * @param orderByParameter
	 *            - string that consist information about type of sorting data
	 * @return string that can be appended to query.
	 */
	String createOrderByPart(String orderByParameter);

}
