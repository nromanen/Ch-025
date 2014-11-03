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

	@Override
	public Log getLogById(int id) {
		return logDao.getLogById(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Date parseDate(String dateString) {
		String[] dateParts = splitDateString(dateString);
		if (dateParts != null) {
			Date date = new Date((Integer.parseInt(dateParts[2]) - 1900),
					(Integer.parseInt(dateParts[1]) - 1),
					Integer.parseInt(dateParts[0]));
			return date;
		}
		return null;
	}

	private String[] splitDateString(String dateString) {
		if (!(dateString.isEmpty())) {
			String preparedDateString = (((dateString.replace(".", "/"))
					.replace(",", "/")).replace("-", "/")).replace("\\", "/");
			String[] dateParts = preparedDateString.split("/");
			try {
				if (dateParts.length == 3 & dateParts[2].length() == 4) {
					return dateParts;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return null;
	}
}
