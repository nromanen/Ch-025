package com.softserve.service;

import java.util.GregorianCalendar;
import java.util.List;

import com.softserve.entity.Log;

public interface LogService {

	public Log addLog(Log log);

	public List<Log> getLogsSinceDate(GregorianCalendar calendar, int pageNumb);

	public List<Log> getAllLogs(int pageNumb);

	public void deleteLogsDueDate(GregorianCalendar calendar);

	public Log getLogById(int id);

	public GregorianCalendar parseDate(String dateString);

	public List<Log> getPartOfLogs(int firstLine, int numberOfLines);

	public List<Log> getRageofLogs(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar, int pageNumb);

}
