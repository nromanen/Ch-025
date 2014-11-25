package com.softserve.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.softserve.dao.LogDao;
import com.softserve.entity.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/fortest/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/fortest/data.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class LogDaoTest {

	@Autowired
	private LogDao logDao;

	@Test
	@DatabaseSetup("classpath:logsData.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:logsData.xml")
	public void testGetLogById() {
		Log log = logDao.getLogById(1);
		assertTrue((log.getId() == 1) && (log.getLevel().equals("ERROR1")));
	}

	@Test
	@DatabaseSetup("classpath:logsData.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:logsData.xml")
	public void testCountLogsInQuery() {
		Date startDate = (new GregorianCalendar(2013, 0, 1)).getTime();
		Date endDate = (new GregorianCalendar(2015, 0, 1)).getTime();
		long allLogsNumb = logDao.countLogsInQuery(startDate, endDate);

		startDate = (new GregorianCalendar(2014, 0, 2)).getTime();

		endDate = (new GregorianCalendar(2014, 3, 1)).getTime();
		long partOfLogsNumb = logDao.countLogsInQuery(startDate, endDate);

		assertTrue((allLogsNumb == 10) && (partOfLogsNumb == 5));

	}

	@Test
	@DatabaseSetup("classpath:logsData.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:logsData.xml")
	public void testdeleteLogsDueDate() {
		GregorianCalendar calendar = new GregorianCalendar(2014, 6, 1);
		logDao.deleteLogsDueDate((calendar.getTime()));
		int firstResult = countAllLogsInDatabase();
		calendar = new GregorianCalendar(2014, 10, 2);
		logDao.deleteLogsDueDate((calendar.getTime()));
		int secondResult = countAllLogsInDatabase();
		assertTrue((firstResult == 4) && (secondResult == 1));
	}

	@Test
	@DatabaseSetup("classpath:logsData.xml")
	@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:logsData.xml")
	public void testGetRangeOfLogs() {
		Date startDate = (new GregorianCalendar(2013, 0, 1)).getTime();
		Date endDate = (new GregorianCalendar(2015, 0, 1)).getTime();
		int logsPerPage = 10;
		int pageNumb = 0;
		String orderBy = "eventDate ASC";
		ArrayList<Log> logList1 = (ArrayList<Log>) logDao.getRangeOfLogs(
				startDate, endDate, logsPerPage, pageNumb, orderBy);
		logsPerPage = 3;
		pageNumb = 3;
		orderBy = "eventDate DESC";
		ArrayList<Log> logList2 = (ArrayList<Log>) logDao.getRangeOfLogs(
				startDate, endDate, logsPerPage, pageNumb, orderBy);

		startDate = (new GregorianCalendar(2014, 1, 1)).getTime();
		endDate = (new GregorianCalendar(2014, 10, 2)).getTime();
		logsPerPage = 7;
		pageNumb = 0;
		ArrayList<Log> logList3 = (ArrayList<Log>) logDao.getRangeOfLogs(
				startDate, endDate, logsPerPage, pageNumb, orderBy);

		assertTrue(logList1.get(0).getLevel().equals("ERROR1")
				&& (logList1.size() == 10)
				&& logList2.get(0).getLevel().equals("ERROR1")
				&& (logList2.size() == 1)
				&& logList3.get(0).getLevel().equals("WARN1")
				&& (logList3.size() == 6));
	}

	private int countAllLogsInDatabase() {
		Date startDate = (new GregorianCalendar(2013, 0, 1)).getTime();
		Date endDate = (new GregorianCalendar(2015, 0, 1)).getTime();
		long allLogsNumb = logDao.countLogsInQuery(startDate, endDate);
		return (int) allLogsNumb;
	}

}
