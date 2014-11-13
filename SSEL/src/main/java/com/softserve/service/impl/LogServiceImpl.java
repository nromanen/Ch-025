package com.softserve.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.LogDao;
import com.softserve.dao.impl.TopicDaoImpl;
import com.softserve.entity.Log;
import com.softserve.service.LogService;

@Service("LogService")
public class LogServiceImpl implements LogService {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(TopicDaoImpl.class);

	@Autowired
	private LogDao logDao;

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	/**
	 * Deletes all logs older than inputed date. (Not including it)
	 */

	@Override
	@Transactional
	public void deleteLogsDueDate(GregorianCalendar calendar) {
		// calendar = makeEndOfDay(calendar); //uncomment this and inputed date
		// will be included
		Date date = calendar.getTime();
		logDao.deleteLogsDueDate(date);
	}

	/**
	 * Gets Log object by its id from database.
	 */
	@Override
	public Log getLogById(int id) {
		return logDao.getLogById(id);
	}

	/**
	 * Converts inputed string in date field into GregorianCalendar object with
	 * equal date in it. If data in string incorrect, returns null.
	 */
	@Override
	public GregorianCalendar parseDate(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = dateFormat.parse(dateString);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e1) {
			LOG.info(e1.toString());
		}
		return null;
	}

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
	@Override
	public List<Log> getRangeOfLogs(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar, int logsPerPage, int pageNumb,
			String orderBy) {
		endCalendar = makeEndOfDay(endCalendar);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		return logDao.getRangeOfLogs(startDate, endDate, logsPerPage, pageNumb,
				orderBy);
	}

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
	@Override
	public Long countLogsInQuery(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar) {
		endCalendar = makeEndOfDay(endCalendar);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		return logDao.countLogsInQuery(startDate, endDate);
	}

	/**
	 * Depending on number logs per page counts how many pages will be needed to
	 * show all logs from certain query.
	 * 
	 * @return number of pages.
	 */
	@Override
	public int getNumberOfPages(Long logsInQuery, int logsPerPage) {
		if (logsPerPage > 0) {
			int numberOfPages = (int) (logsInQuery / logsPerPage);
			if ((logsInQuery % logsPerPage) != 0) {
				// you'll need one more page to show last few results
				++numberOfPages;
			}
			return numberOfPages;
		}
		return 1;
	}

	/**
	 * Convert string with how-to-sort information into part of hql query.
	 * 
	 * @param orderByParameter
	 *            - string that consist information about type of sorting data
	 * @return string that can be appended to query.
	 */
	@Override
	public String createOrderByPart(String orderByParameter) {
		String resultString;
		if (orderByParameter != null) {
			String[] resultParts = splitOrderByParameter(orderByParameter);
			resultString = resultParts[0] + " " + resultParts[1];
		} else {
			resultString = "eventDate DESC";
		}
		return resultString;
	}

	// splits string with how-to-sort information into 2 strings
	// includes default parameters
	private String[] splitOrderByParameter(String orderByParameter) {
		String[] partsOfParam = orderByParameter.split("-");
		String columnName;
		String upOrDown;
		switch (partsOfParam[0]) {
		case "date":
			columnName = "eventDate";
			break;
		case "level":
			columnName = "level";
			break;
		case "exception":
			columnName = "exception";
			break;
		default:
			columnName = "eventDate";
			break;
		}
		try {
			switch (partsOfParam[1]) {
			case "asc":
				upOrDown = "ASC";
				break;
			case "desc":
				upOrDown = "DESC";
				break;
			default:
				upOrDown = "DESC";
				break;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			upOrDown = "DESC";
			LOG.info(e.toString() + " Error in createOrderByPart method");
		}
		String[] resultParts = { columnName, upOrDown };
		return resultParts;
	}

	// When you add endDate to range query - it is not includes
	// this method helps him being included
	private GregorianCalendar makeEndOfDay(GregorianCalendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar;
	}
}
