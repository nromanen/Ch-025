package com.softserve.service;

import java.util.GregorianCalendar;
import java.util.List;

import com.softserve.entity.Log;

public interface LogService {

	Log getLogById(int id);

	List<Log> getRangeOfLogs(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar, int logsPerPage, int pageNumb,
			String orderBy);

	void deleteLogsDueDate(GregorianCalendar calendar);

	Long countLogsInQuery(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar);

	int getNumberOfPages(Long logsInQuery, int logsPerPage);

	GregorianCalendar parseDate(String dateString);

	String createOrderByPart(String orderByParameter);

}
