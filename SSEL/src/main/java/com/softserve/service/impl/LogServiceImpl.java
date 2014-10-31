package com.softserve.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.dao.LogDao;
import com.softserve.entity.Log;
import com.softserve.service.LogService;
@Service("LogService")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;

	@Override
	@Transactional
	public Log addLog(Log log) {
		return logDao.addLog(log);
	}

	@Override
	@Transactional
	public List<Log> getLogsSinceDate(Date date) {
		return logDao.getLogsSinceDate(date);
	}

	@Override
	@Transactional
	public List<Log> getAllLogs() {
		return logDao.getAllLogs();
	}

	@Override
	@Transactional
	public void deleteLogsDueDate(Date date) {
		logDao.deleteLogsDueDate(date);
	}

}
