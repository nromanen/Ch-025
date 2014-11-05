package com.softserve.service.impl;

import java.util.Date;
import java.util.GregorianCalendar;
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
	public List<Log> getLogsSinceDate(GregorianCalendar calendar, int pageNumb) {
		Date date = calendar.getTime();
		return logDao.getLogsSinceDate(date, pageNumb);
	}

	@Override
	@Transactional
	public List<Log> getAllLogs(int pageNumb) {
		return logDao.getAllLogs(pageNumb);
	}

	@Override
	@Transactional
	public void deleteLogsDueDate(GregorianCalendar calendar) {
		Date date = calendar.getTime();
		logDao.deleteLogsDueDate(date);
	}

	@Override
	public Log getLogById(int id) {
		return logDao.getLogById(id);
	}

	@Override
	public GregorianCalendar parseDate(String dateString) {
		String[] dateParts = splitDateString(dateString);
		if (dateParts != null) {
			GregorianCalendar calendar = new GregorianCalendar(
					(Integer.parseInt(dateParts[2])),
					(Integer.parseInt(dateParts[1]) - 1),
					Integer.parseInt(dateParts[0]));
			return calendar;
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

	@Override
	public List<Log> getPartOfLogs(int firstLine, int numberOfLines) {
		return logDao.getPartOfLogs(firstLine, numberOfLines);
	}

	@Override
	public List<Log> getRageofLogs(GregorianCalendar startCalendar,
			GregorianCalendar endCalendar, int pageNumb) {
		endCalendar.set(endCalendar.HOUR_OF_DAY, 23);
		endCalendar.set(endCalendar.MINUTE, 59);
		endCalendar.set(endCalendar.SECOND, 50);
		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();
		return logDao.getRageofLogs(startDate, endDate, pageNumb);
	}

}
