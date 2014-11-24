package com.softserve.dao;

import java.util.Date;
import java.util.List;

import com.softserve.entity.Log;

public interface LogDao {
	
	Log getLogById(int id);

	List<Log> getRangeOfLogs(Date startDate, Date endDate, int logsPerPage, int pageNumb, String orderBy);
	
	void deleteLogsDueDate(Date date);

	Long countLogsInQuery (Date startDate, Date endDate);

}
