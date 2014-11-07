package com.softserve.service;

import java.util.GregorianCalendar;
import java.util.List;

import com.softserve.entity.Log;

public interface LogService {

	public Log getLogById(int id);
	
	public List<Log> getRangeOfLogs(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar, int logsPerPage, int pageNumb, String orderBy);
	
	public void deleteLogsDueDate(GregorianCalendar calendar);

	public Long countLogsInQuery(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar);
	
	public int getNumberOfPages(Long logsInQuery, int logsPerPage);
	
	public GregorianCalendar parseDate(String dateString);
	
	public String createOrderByPart(String orderByParameter);

}
