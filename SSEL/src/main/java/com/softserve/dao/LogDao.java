package com.softserve.dao;

import java.util.Date;
import java.util.List;

import com.softserve.entity.Log;

public interface LogDao {

	public Log addLog(Log log);

	public List<Log> getLogsSinceDate(Date date, int pageNumb);

	public List<Log> getAllLogs(int pageNumb);

	public void deleteLogsDueDate(Date date);

	public Log getLogById(int id);

	public List<Log> getPartOfLogs(int firstLine, int numberOfLines);

	public List<Log> getRageofLogs(Date startDate, Date endDate, int logsPerPage, int pageNumb);
	
	public Long countLogsInQuery (Date startDate, Date endDate);

}
