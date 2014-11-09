package com.softserve.dao;

import java.util.Date;
import java.util.List;

import com.softserve.entity.Log;

public interface LogDao {
	
	public Log getLogById(int id);

	public List<Log> getRangeOfLogs(Date startDate, Date endDate, int logsPerPage, int pageNumb, String orderBy);
	
	public void deleteLogsDueDate(Date date);

	public Long countLogsInQuery (Date startDate, Date endDate);

}
