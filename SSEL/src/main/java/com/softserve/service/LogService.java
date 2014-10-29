package com.softserve.service;

import java.util.Date;
import java.util.List;

import com.softserve.entity.Log;

public interface LogService {

	public Log addLog(Log log);

	public List<Log> getLogsSinceDate(Date date);

	public List<Log> getAllLogs();

	public void deleteLogsDueDate(Date date);

}
