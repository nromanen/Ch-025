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

	@Override
	@Transactional
	public void deleteLogsDueDate(GregorianCalendar calendar) {
		// calendar = makeEndOfDay(calendar); //uncomment this and inputed date
		// will be included
		Date date = calendar.getTime();
		logDao.deleteLogsDueDate(date);
	}

	@Override
	public Log getLogById(int id) {
		return logDao.getLogById(id);
	}

	@Override
	public GregorianCalendar parseDate(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = dateFormat.parse(dateString);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e1) {
			LOG.debug(e1.toString());
		}
		return null;
	}

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

	@Override
	public Long countLogsInQuery(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar) {
		endCalendar = makeEndOfDay(endCalendar);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		return logDao.countLogsInQuery(startDate, endDate);
	}

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
			LOG.debug(e.toString());
			LOG.debug("Error in createOrderByPart method");
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
